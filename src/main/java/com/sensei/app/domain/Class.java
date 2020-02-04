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
@Table(name="classes")

public class Class extends AbstractAuditingEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Long id;
	 
	 @Version
	 private int version;
	 
	 @Column(name="current")
	 private int current;
	 
	 @Column(name="section")
	 private String section;
	 
	 @NotNull
	 @Column(name="year")
	 private int year;
	 
	 @NotNull
	 @Column(name="grade_id")
	 private int gradeId;
	 
	 @Column(name="session_end")
	 private int sessionEnd;

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

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public int getSessionEnd() {
		return sessionEnd;
	}

	public void setSessionEnd(int sessionEnd) {
		this.sessionEnd = sessionEnd;
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
        Class clas = (Class) o;
        if (clas.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), clas.getId());
    }

}
