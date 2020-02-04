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
@Table(name="ladder")
public class Ladder extends AbstractAuditingEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Long id;
	 
	 @Version
	 private int version;
	 
	 @NotNull
	 @Column(name="class_id")
	 private int classId;
	 
	 @NotNull
	 @Column(name="course_id")
	 private int courseId;
	 
	 @NotNull
	 @Column(name="student_id")
	 private int studentId;
	 
	 @NotNull
	 @Column(name="student")
	 private String student;

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

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
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
        Ladder ladder = (Ladder) o;
        if (ladder.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ladder.getId());
    }
	
}
