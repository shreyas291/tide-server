package com.sensei.app.service.dto;

import java.time.LocalDate;

public class BuildlogDTO {
	
	 private Long id;
	 
	 private Integer version;
	 
	 private double build;
	 
	 private String type;

	 private LocalDate uploadDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public double getBuild() {
		return build;
	}

	public void setBuild(double build) {
		this.build = build;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDate getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(LocalDate uploadDate) {
		this.uploadDate = uploadDate;
	}


}
