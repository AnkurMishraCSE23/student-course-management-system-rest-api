package com.ankur.exceptions;

public class StudentNotFoundException extends ServiceException
{
	private static final long serialVersionUID = 1L;
	
	public StudentNotFoundException(String msg)
	{
		super(msg);
	}
}
