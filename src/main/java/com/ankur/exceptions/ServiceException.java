package com.ankur.exceptions;

public abstract class ServiceException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	public ServiceException(String msg)
	{
		super(msg);
	}
	public ServiceException(String msg, Throwable e)
	{
		super(msg, e);
	}
}