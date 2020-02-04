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
@Table(name="schools")
public class Schools  extends AbstractAuditingEntity implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Long id;
	 
	 @Version
	 private Integer version;
	
	 @Column(name="address")
	 private String address;
	 
	 @NotNull
	 @Column(name="category", nullable=false)
	 private String category;
	 
	 @NotNull
	 @Column(name="managment", nullable=false)
	 private String managment;
	 
	 @NotNull
	 @Column(name="name", nullable=false)
	 private String name;
	 
	
	 @Column(name="phone_number")
	 private Long phoneNumber;
	 
	 @Column(name="cluster")
	 private String cluster;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getManagment() {
		return managment;
	}

	public void setManagment(String managment) {
		this.managment = managment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCluster() {
		return cluster;
	}

	public void setCluster(String cluster) {
		this.cluster = cluster;
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
	        Schools schools = (Schools) o;
	        if (schools.getId() == null || getId() == null) {
	            return false;
	        }
	        return Objects.equals(getId(), schools.getId());
	    }

		@Override
		public String toString() {
			return "Schools [id=" + id + ", version=" + version + ", address=" + address + ", category=" + category
					+ ", managment=" + managment + ", name=" + name + ", phoneNumber=" + phoneNumber + ", cluster="
					+ cluster + "]";
		}
}
