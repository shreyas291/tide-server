package com.sensei.app.service.dto;

public class TopicDTO {

	private Long id;
	
	private int version;
	
	private String code;
	
	private String name;
	
	private String description;
	
	private String nameLanguage1;
	
	private String nameLanguage2;
	
	private String descriptionLanguage1;
	
	private String descriptionLanguage2;
	
	private Long subjectId;
	
	private Long parentTopicId;
	
	private int expectedDuration;
	
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNameLanguage1() {
		return nameLanguage1;
	}

	public void setNameLanguage1(String nameLanguage1) {
		this.nameLanguage1 = nameLanguage1;
	}

	public String getNameLanguage2() {
		return nameLanguage2;
	}

	public void setNameLanguage2(String nameLanguage2) {
		this.nameLanguage2 = nameLanguage2;
	}

	public String getDescriptionLanguage1() {
		return descriptionLanguage1;
	}

	public void setDescriptionLanguage1(String descriptionLanguage1) {
		this.descriptionLanguage1 = descriptionLanguage1;
	}

	public String getDescriptionLanguage2() {
		return descriptionLanguage2;
	}

	public void setDescriptionLanguage2(String descriptionLanguage2) {
		this.descriptionLanguage2 = descriptionLanguage2;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public Long getParentTopicId() {
		return parentTopicId;
	}

	public void setParentTopicId(Long parentTopicId) {
		this.parentTopicId = parentTopicId;
	}

	public int getExpectedDuration() {
		return expectedDuration;
	}

	public void setExpectedDuration(int expectedDuration) {
		this.expectedDuration = expectedDuration;
	}


}
