package com.robot;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.robot.utils.ApplicationProperties;

import robot.RobotPosition;
import robot.RobotStatics;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@SpringBootApplication
public class RobotController {

	@Autowired
	private ApplicationProperties properties;

	@GetMapping("/toyRobot/place")
	Response<RobotPosition> place(@RequestParam String column, @RequestParam String row, @RequestParam String facing,
			HttpServletResponse httpResponse) {
		RobotPosition robot = new RobotPosition(row, column, facing);
		Response<RobotPosition> resp = new Response<>();
		resp = checkFacing(httpResponse, robot);
		if (resp.getEsito()!= null && resp.getEsito().getCode().equals(properties.getResponse().get("facingError"))) return resp;		
		if (RobotStatics.validate(robot)) {
			Esito esito = new Esito(properties.getResponse().get("successCode"),
					properties.getResponse().get("succesDescription"));
			resp.setEsito(esito);
			resp.setContent(robot);
			return resp;
		} else {
			Esito esito = new Esito(properties.getResponse().get("lostCode"),
					properties.getResponse().get("lostDescription"));
			resp.setEsito(esito);
			resp.setContent(null);
			return resp;
		}

	}

	@PostMapping("/toyRobot/right")
	Response<RobotPosition> right(@RequestBody RobotPosition robot, HttpServletResponse httpResponse) {
		Response<RobotPosition> resp = new Response<>();
		resp = checkFacing(httpResponse, robot);
		if (resp.getEsito()!= null && resp.getEsito().getCode().equals(properties.getResponse().get("facingError"))) return resp;
		Esito esito = new Esito(properties.getResponse().get("successCode"),
				properties.getResponse().get("succesDescription"));
		RobotStatics.rotateRight(robot);
		resp.setEsito(esito);
		resp.setContent(robot);
		return resp;

	}

	@PostMapping("/toyRobot/left")
	Response<RobotPosition> left(@RequestBody RobotPosition robot, HttpServletResponse httpResponse) {
		Response<RobotPosition> resp = new Response<>();
		resp = checkFacing(httpResponse, robot);
		if (resp.getEsito()!= null && resp.getEsito().getCode().equals(properties.getResponse().get("facingError"))) return resp;
		Esito esito = new Esito(properties.getResponse().get("successCode"),
				properties.getResponse().get("succesDescription"));
		RobotStatics.rotateLeft(robot);
		resp.setEsito(esito);
		resp.setContent(robot);
		return resp;
	}

	@PostMapping("/toyRobot/move")
	Response<RobotPosition> move(@RequestBody RobotPosition robot, HttpServletResponse httpResponse) {
		Response<RobotPosition> resp = new Response<>();
		resp = checkFacing(httpResponse, robot);
		if (resp.getEsito()!= null && resp.getEsito().getCode().equals(properties.getResponse().get("facingError"))) return resp;
		RobotStatics.move(robot);
		if (RobotStatics.validate(robot)) {
			Esito esito = new Esito(properties.getResponse().get("successCode"),
					properties.getResponse().get("succesDescription"));
			resp.setEsito(esito);
			resp.setContent(robot);
			return resp;
		} else {
			Esito esito = new Esito(properties.getResponse().get("lostCode"),
					properties.getResponse().get("lostDescription"));
			resp.setEsito(esito);
			resp.setContent(null);
			return resp;
		}
		
	}

	public static void main(String[] args) {
		SpringApplication.run(RobotController.class, args);
	}

	private Response<RobotPosition> checkFacing(HttpServletResponse httpResponse, RobotPosition robot) {
		Response<RobotPosition> resp = new Response<>();
		if (!properties.getAcceptedFacings().contains(robot.getFacing())) {
			Esito esito = new Esito(properties.getResponse().get("facingError"),
					properties.getResponse().get("facingErrorDescription"));
			resp.setEsito(esito);
			httpResponse.setStatus(500);
			return resp;
		} else
			return resp;
	}
}
