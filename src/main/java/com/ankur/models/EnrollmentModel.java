package com.ankur.models;

public class EnrollmentModel
{
	private Integer studentId;
	private Integer courseId;
	
	public EnrollmentModel() {}

	public EnrollmentModel(Integer studentId, Integer courseId)
	{
		this.studentId = studentId;
		this.courseId = courseId;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
}
