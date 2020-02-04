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
@Table(name="buildlog")
public class Buildlog extends AbstractAuditingEntity implements Serializable {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Long id;
	 
	 @Version
	 private Integer version;
	 
	 @NotNull
	 @Column(name="build")
	 private double build;
	 
	 @NotNull
	 @Column(name="type")
	 private String type;

	 @NotNull
	 @Column(name="upload_date")
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

	public static long getSerialversionuid() {
		return serialVersionUID;
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
        Buildlog buildlog = (Buildlog) o;
        if (buildlog.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), buildlog.getId());
    }
}
