package com.robot.utils;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "robot")
public class ApplicationProperties {
	
	private List<String> acceptedFacings;
	private Map<String, String> response;

	public List<String> getAcceptedFacings() {
		return acceptedFacings;
	}

	public void setAcceptedFacings(List<String> acceptedFacings) {
		this.acceptedFacings = acceptedFacings;
	}

	public Map<String, String> getResponse() {
		return response;
	}

	public void setResponse(Map<String, String> response) {
		this.response = response;
	}
	
	
}
