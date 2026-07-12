package com.ankur.models;

public class CourseModel
{
	private Integer id = null;
	private String name;
	private int durationInMonths;
	private double fees;
	
	public CourseModel() {}
	
	public CourseModel(String name, int durationInMonths, double fees)
	{
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

	public int getDurationInMonths() {
		return durationInMonths;
	}

	public void setDurationInMonths(int durationInMonths) {
		this.durationInMonths = durationInMonths;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}
}
