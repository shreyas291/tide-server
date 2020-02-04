package com.sensei.app.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="student")
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Version
	private Integer version;

	@NotNull
	@Column(name="adhar_id")
	private String aadhaarId;
   

	@NotNull
	@Column(name="created_date")
	private String createdDate;


	@Column(name="blood_group")
	private String bloodGroup;


	@Column(name="child_height")
	private int childHeight;

	@Column(name="child_insurance")
	private String childInsurance;

	@Column(name="child_weight")
	private int childWeight;

	@NotNull
	@Column(name="community")
	private String community;

	@Column(name="complete_set_of_free_textbook")
	private String completeSetOfFreeTextbook;

	@NotNull
	@Column(name="date_of_birth")
	private LocalDate dateOfBirth;

	@Column(name="disability")
	private String disability;

	@NotNull
	@Column(name="father_name")
	private String fatherName;

	@NotNull
	@Column(name="first_language")
	private String firstLanguage;

	@NotNull
	@Column(name="first_name")
	private String firstName;

	@Column(name="gender")
	private String gender;

	@NotNull
	@Column(name="joining_date")
	private LocalDate joiningDate;

	@NotNull
	@Column(name="last_name")
	private String lastName;

	@NotNull
	@Column(name="medium")
	private String medium;

	@Column(name="midday_meal")
	private String middayMeal;

	@NotNull
	@Column(name="mobile_number")
	private Long mobileNumber;

	@NotNull
	@Column(name="mother_name")
	private String motherName;

	@NotNull
	@Column(name="mother_tongue")
	private String motherTongue;

	@NotNull
	@Column(name="occupation")
	private String parentOccupation;

	@NotNull
	@Column(name="password")
	private String password;

	@NotNull
	@Column(name="photo")
	private String photo;

	@NotNull
	@Column(name="religion")
	private String religion;

	@NotNull
	@Column(name="section")
	private String section;

	@NotNull
	@Column(name="status")
	private String status;

	@NotNull
	@Column(name="updated_date")
	private LocalDate updatedDate;

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
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


	public @NotNull LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(@NotNull LocalDate dateOfBirth) {
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
        Student student = (Student) o;
        if (student.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), student.getId());
    }
    
    @Override
	public String toString() {
		return "Student [id=" + id + ", version=" + version + ", aadhaarId=" + aadhaarId + ", createdDate="
				+ createdDate + ", bloodGroup=" + bloodGroup + ", childHeight=" + childHeight + ", childInsurance="
				+ childInsurance + ", childWeight=" + childWeight + ", community=" + community
				+ ", completeSetOfFreeTextbook=" + completeSetOfFreeTextbook + ", dateOfBirth=" + dateOfBirth
				+ ", disability=" + disability + ", fathername=" + fatherName + ", firstLanguage=" + firstLanguage
				+ ", firstName=" + firstName + ", gender=" + gender + ", joiningDate=" + joiningDate + ", lastName="
				+ lastName + ", medium=" + medium + ", middayMeal=" + middayMeal + ", mobileNumber=" + mobileNumber
				+ ", motherName=" + motherName + ", motherTongue=" + motherTongue + ", occupation=" + parentOccupation
				+ ", password=" + password + ", photo=" + photo + ", religion=" + religion + ", section=" + section
				+ ", status=" + status + ", updatedDate=" + updatedDate + "]";
	}
}
