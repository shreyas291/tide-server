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
@Table(name="classes_student")

public class ClassesStudent extends AbstractAuditingEntity implements Serializable {
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
 
	  @Version
   private Integer version;

		@NotNull
	    @Column(name = "class_id")
	    private int classId;

	    @NotNull
	    @Column(name = "student_id")
	    private int studentId;

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

		public int getClassId() {
			return classId;
		}

		public void setClassId(int classId) {
			this.classId = classId;
		}

		public int getStudentId() {
			return studentId;
		}

		public void setStudentId(int studentId) {
			this.studentId = studentId;
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
	        ClassesStudent classesStudent = (ClassesStudent) o;
	        if (classesStudent.getId() == null || getId() == null) {
	            return false;
	        }
	        return Objects.equals(getId(), classesStudent.getId());
	    }

		@Override
		public String toString() {
			return "ClassesStudent [id=" + id + ", version=" + version + ", classId=" + classId + ", studentId="
					+ studentId + "]";
		}
}
