package com.ankur.exceptions;

public class DatabaseOperationException extends ServiceException
{
	private static final long serialVersionUID = 1L;

	public DatabaseOperationException(String msg, Throwable cause)
	{
		super(msg, cause);
	}
}
