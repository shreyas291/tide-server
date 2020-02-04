package com.sensei.app.service.dto;


public class MilestonesDTO {
	
	
	 private Long id;
	 
	 private int version;
	 
	 private int baseline;
	 
	 private String description;
	 
	 private Long end;
	 
	 private Long milestoneId;

	 private Long start;

	 private String status;

	 private int ladderId;
	 

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

	public int getBaseline() {
		return baseline;
	}

	public void setBaseline(int baseline) {
		this.baseline = baseline;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getEnd() {
		return end;
	}

	public void setEnd(Long end) {
		this.end = end;
	}

	public Long getMilestoneId() {
		return milestoneId;
	}

	public void setMilestoneId(Long milestoneId) {
		this.milestoneId = milestoneId;
	}

	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getLadderId() {
		return ladderId;
	}

	public void setLadderId(int ladderId) {
		this.ladderId = ladderId;
	}
	 

}
