package com.ankur.dtos;

public class CourseResponseDTO
{
	private Integer id;
	private String name;
	private Integer durationInMonths;
	private Double fees;
	
	public CourseResponseDTO() {}
	
	public CourseResponseDTO(Integer id, String name, Integer durationInMonths, Double fees)
	{
		this.id = id;
		this.name = name;
		this.durationInMonths = durationInMonths;
		this.fees = fees;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
