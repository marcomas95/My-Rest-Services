package com.robot;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.robot.utils.ApplicationProperties;

import robot.RobotPosition;
import robot.RobotStatics;

@RestController
@SpringBootApplication
public class RobotController {
	
	@Autowired
	private ApplicationProperties properties;

	@GetMapping("/toyRobot/place")
	Response<RobotPosition> place (@RequestParam String column, @RequestParam String row, @RequestParam String facing, HttpServletResponse httpResponse) {
		Response<RobotPosition> resp = new Response<>();
		if (!properties.getAcceptedFacings().contains(facing)) {
			Esito esito = new Esito(properties.getResponse().get("facingError"), properties.getResponse().get("facingErrorDescription"));
			resp.setEsito(esito);
			httpResponse.setStatus(500);
			return resp;
		}
		
		RobotPosition robot = new RobotPosition(row, column, facing);	
		Esito esito = new Esito(properties.getResponse().get("successCode"), properties.getResponse().get("succesDescription"));
		List<RobotPosition> list = new ArrayList<RobotPosition>();
		list.add(robot);
		resp.setEsito(esito);
		resp.setContent(list);
		return resp;
		
		
	}
	
	@PostMapping("/toyRobot/right")
	Response<RobotPosition> right (@RequestBody RobotPosition robot) {
		Response<RobotPosition> resp = new Response<>();
		Esito esito = new Esito(properties.getResponse().get("successCode"), properties.getResponse().get("succesDescription"));
		List<RobotPosition> list = new ArrayList<RobotPosition>();
		RobotStatics.rotateRight(robot);
		list.add(robot);
		resp.setEsito(esito);
		resp.setContent(list);
		return resp;
		
	}
	
	@PostMapping("/toyRobot/left")
	Response<RobotPosition> left (@RequestBody RobotPosition robot) {
		return null;
		
	}

	public static void main(String[] args) {
		SpringApplication.run(RobotController.class, args);
	}
}
