package com.sensei.app.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "topics")
public class Topic extends AbstractAuditingEntity implements Serializable {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Version
	@Column(name="version")
	private int version;

	@Column(name="code")
	private String code;

	@Column(name="name")
	private String name;

	@Column(name="description")
	private String description;

	@Column(name="name_language_1")
	private String nameLanguage1;

	@Column(name="name_language_2")
	private String nameLanguage2;

	@Column(name="description_language_1")
	private String descriptionLanguage1;

	@Column(name="description_language_2")
	private String descriptionLanguage2;

	@Column(name="subject_id")
	private Long subjectId;

	@Column(name="parent_topic_id")
	private Long parentTopicId;

	@Column(name="expected_duration")
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

	@Override
	public int hashCode() {
return Objects.hashCode(getId());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Topic topic = (Topic) o;
		if (topic.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), topic.getId());
	}
	@Override
	public String toString() {
		return "Topic [id=" + id + ", version=" + version + ", code=" + code + ", name=" + name + ", description="
				+ description + ", nameLanguage1=" + nameLanguage1 + ", nameLanguage2=" + nameLanguage2
				+ ", descriptionLanguage1=" + descriptionLanguage1 + ", descriptionLanguage2=" + descriptionLanguage2
				+ ", subjectId=" + subjectId + ", parentTopicId=" + parentTopicId + ", expectedDuration="
				+ expectedDuration + "]";
	}

}
