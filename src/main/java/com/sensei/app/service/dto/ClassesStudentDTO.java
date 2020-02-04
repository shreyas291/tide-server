package com.sensei.app.service.dto;

public class ClassesStudentDTO{
	
	   private Long id;
	  
       private Integer version;
   
	    private int classId;

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

	    
}
