package com.ankur.exceptions;

public class ValidationException extends ServiceException
{
	private static final long serialVersionUID = 1L;

	public ValidationException(String msg)
	{
		super(msg);
	}
}
