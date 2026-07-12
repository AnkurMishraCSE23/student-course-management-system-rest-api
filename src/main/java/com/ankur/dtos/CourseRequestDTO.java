package com.ankur.dtos;

public class CourseRequestDTO
{
	private String name;
	private Integer durationInMonths;
	private Double fees;
	
	public CourseRequestDTO() {}
	public CourseRequestDTO(String name, Integer durationInMonths, Double fees)
	{
		this.name = name;
		this.durationInMonths = durationInMonths;
		this.fees = fees;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getDurationInMonths() {
		return durationInMonths;
	}
	public void setDurationInMonths(Integer durationInMonths) {
		this.durationInMonths = durationInMonths;
	}
	public Double getFees() {
		return fees;
	}
	public void setFees(Double fees) {
		this.fees = fees;
	}
}
