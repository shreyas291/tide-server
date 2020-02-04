package com.sensei.app.service.dto;

import java.io.Serializable;
import java.util.List;

public class TeachersDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer version;

	private String uid;

	private int age;

	private String caste;

	private String firstName;

	private String gender;

	private String lastName;

	private String password;

	private String photo;

	private String qualification;

	private String userid;

	private String tSchool;

	private List<Integer> gradeIds;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastname) {
		this.lastName = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTSchool() {
		return tSchool;
	}

	public void setTSchool(String tSchool) {
		this.tSchool = tSchool;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String gettSchool() {
		return tSchool;
	}

	public void settSchool(String tSchool) {
		this.tSchool = tSchool;
	}

	public List<Integer> getGradeIds() {
		return gradeIds;
	}

	public void setGradeIds(List<Integer> gradeIds) {
		this.gradeIds = gradeIds;
	}

	@Override
	public String toString() {
		return "TeachersDTO [id=" + id + ", version=" + version + ", uid=" + uid + ", age=" + age + ", caste=" + caste
				+ ", firstName=" + firstName + ", gender=" + gender + ", lastName=" + lastName + ", password="
				+ password + ", photo=" + photo + ", qualification=" + qualification + ", userid=" + userid
				+ ", tSchool=" + tSchool + ", gradeIds=" + gradeIds + "]";
	}

}
