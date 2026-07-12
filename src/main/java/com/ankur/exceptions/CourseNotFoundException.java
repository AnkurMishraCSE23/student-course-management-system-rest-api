package com.ankur.exceptions;

public class CourseNotFoundException extends ServiceException
{
	private static final long serialVersionUID = 1L;

	public CourseNotFoundException(String msg)
	{
		super(msg);
	}
}
