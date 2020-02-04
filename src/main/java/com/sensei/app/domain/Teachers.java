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
import javax.validation.constraints.NotNull;

@Entity
@Table(name="teachers")
public class Teachers extends AbstractAuditingEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Long id;
	 
	 @Version
	 private Integer version;
	 
	 @NotNull
	 @Column(name="UID", nullable=false)
	 private String uid;
	 
	 @NotNull
	 @Column(name="age", nullable=false)
	 private int age;
	 
	 @Column(name="caste", nullable=false)
	 private String caste;
	 
	 @NotNull
	 @Column(name="first_name", nullable=false)
	 private String firstName;
	 
	 @NotNull
	 @Column(name="gender", nullable=false)
	 private String gender;
	 
	 @NotNull
	 @Column(name="last_name", nullable=false)
	 private String lastName;
	 
	 @NotNull
	 @Column(name="password", nullable=false)
	 private String password;
	 
	 @NotNull
	 @Column(name="photo", nullable=false)
	 private String photo;
	 
	 @NotNull
	 @Column(name="qualification", nullable=false)
	 private String qualification;
	 
	 @NotNull
	 @Column(name="userid", nullable=false)
	 private String userid;
	 
	 @NotNull
	 @Column(name="t_school", nullable=false)
	 private String tSchool;
	

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
        Teachers teachers = (Teachers) o;
        if (teachers.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), teachers.getId());
    }

	@Override
	public String toString() {
		return "Teachers [id=" + id + ", version=" + version + ", uid=" + uid + ", age=" + age + ", caste=" + caste
				+ ", firstname=" + firstName + ", gender=" + gender + ", lastname=" + lastName + ", password="
				+ password + ", photo=" + photo + ", qualification=" + qualification + ", userid=" + userid
				+ ", tSchool=" + tSchool + "]";
	}
	
}
