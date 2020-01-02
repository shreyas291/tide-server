package com.sensei.app.service.dto;

public class NameValueDto {
	private String code;
	private String name;

	public NameValueDto() {
	}

	public NameValueDto(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	// @JsonIgnore
	public String getDisplayName() {
		return name + " " + code;
	}

	public void setName(String name) {
		this.name = name;
	}
}
