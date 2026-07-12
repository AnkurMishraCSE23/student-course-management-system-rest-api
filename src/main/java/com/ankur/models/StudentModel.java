package com.ankur.models;

public class StudentModel
{
	private Integer id = null;
	private String name;
	private String email;
	
	public StudentModel() {}
	
	public StudentModel(String name, String email)
	{
		this.name = name;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
