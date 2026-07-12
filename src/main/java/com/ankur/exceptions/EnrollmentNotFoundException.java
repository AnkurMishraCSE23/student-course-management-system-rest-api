package com.ankur.exceptions;

public class EnrollmentNotFoundException extends ServiceException
{
	private static final long serialVersionUID = 1L;

	public EnrollmentNotFoundException(String msg)
	{
		super(msg);
	}
}
