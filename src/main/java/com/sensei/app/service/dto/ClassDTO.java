package com.sensei.app.service.dto;

public class ClassDTO {
	
	 private Long id;
	 
	 private int version;
	 
	 private int current;
	 
	 private String section;
	
	 private int year;
	
	 private int gradeId;
	 
	 private int sessionEnd;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public int getSessionEnd() {
		return sessionEnd;
	}

	public void setSessionEnd(int sessionEnd) {
		this.sessionEnd = sessionEnd;
	}

}
