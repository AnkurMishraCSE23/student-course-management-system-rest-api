package com.ankur.dtos;

public class EnrollmentResponseDTO
{
	private Integer studentId;
	private Integer courseId;
	
	public EnrollmentResponseDTO() {}
	
	public EnrollmentResponseDTO(Integer studentId, Integer courseId)
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
