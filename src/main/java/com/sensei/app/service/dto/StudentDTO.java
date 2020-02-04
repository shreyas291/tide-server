package com.sensei.app.service.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class StudentDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer version;

	private String aadhaarId;

	private String bloodGroup;

	private int childHeight;
	
	private String childInsurance;

	private int childWeight;

	private String community;

	private LocalDate dateOfBirth;

	private String disability;

	private String fatherName;

	private String firstLanguage;

	private String firstName;

	private String gender;

	private LocalDate joiningDate;
	
	private LocalDate createdDate;

	private String lastName;

	private String medium;

	private String middayMeal;

	private Long mobileNumber;


	private String motherName;

	private String motherTongue;

	private String parentOccupation;

	private String password;

	private String photo;

	private String religion;

	private String section;

	private String status;

	private LocalDate updatedDate;

	private String completeSetOfFreeTextbook;

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	
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

	public String getAadhaarId() {
		return aadhaarId;
	}

	public void setAadhaarId(String aadhaarId) {
		this.aadhaarId = aadhaarId;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}


	public int getChildHeight() {
		return childHeight;
	}

	public void setChildHeight(int childHeight) {
		this.childHeight = childHeight;
	}

	public String getChildInsurance() {
		return childInsurance;
	}

	public void setChildInsurance(String childInsurance) {
		this.childInsurance = childInsurance;
	}

	public int getChildWeight() {
		return childWeight;
	}

	public void setChildWeight(int childWeight) {
		this.childWeight = childWeight;
	}


	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getCompleteSetOfFreeTextbook() {
		return completeSetOfFreeTextbook;
	}

	public void setCompleteSetOfFreeTextbook(String completeSetOfFreeTextbook) {
		this.completeSetOfFreeTextbook = completeSetOfFreeTextbook;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getDisability() {
		return disability;
	}

	public void setDisability(String disability) {
		this.disability = disability;
	}

	public String getFirstLanguage() {
		return firstLanguage;
	}

	public void setFirstLanguage(String firstLanguage) {
		this.firstLanguage = firstLanguage;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}


	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMedium() {
		return medium;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getParentOccupation() {
		return parentOccupation;
	}

	public void setParentOccupation(String parentOccupation) {
		this.parentOccupation = parentOccupation;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public String getMiddayMeal() {
		return middayMeal;
	}

	public void setMiddayMeal(String middayMeal) {
		this.middayMeal = middayMeal;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getMotherTongue() {
		return motherTongue;
	}

	public void setMotherTongue(String motherTongue) {
		this.motherTongue = motherTongue;
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

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}


	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public LocalDate getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}

	
}
