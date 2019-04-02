package com.robot;

public class Esito {
	private String code;
	private String description;

	public Esito(String code, String description) {
		this.setCode(code);
		this.setDescription(description);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	

}
