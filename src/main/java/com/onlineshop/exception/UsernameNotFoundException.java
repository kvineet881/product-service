package com.onlineshop.exception;

public class UsernameNotFoundException extends RuntimeException{
 
	private static final long serialVersionUID = 1348771109171435607L;

	public UsernameNotFoundException(String message)
	{
		super(message);
	}
}
