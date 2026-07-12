package com.ankur.exceptions;

public class DuplicateEnrollmentException extends ServiceException
{
	private static final long serialVersionUID = 1L;

	public DuplicateEnrollmentException(String msg)
	{
		super(msg);
	}
}
