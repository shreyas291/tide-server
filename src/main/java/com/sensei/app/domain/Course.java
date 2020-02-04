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
@Table(name="course")

public class Course extends AbstractAuditingEntity implements Serializable {
	

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
	 @Column(name="name", nullable=false)
	 private String name;
	 
	 @NotNull
	 @Column(name="grade_id", nullable=false)
	 private int gradeId;
	 
	 @Column(name="description")
	 private String description;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
        Course courses = (Course) o;
        if (courses.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), courses.getId());
    }

	@Override
	public String toString() {
		return "Courses [id=" + id + ", version=" + version + ", name=" + name + ", gradeId=" + gradeId
				+ ", description=" + description + "]";
	}
}
