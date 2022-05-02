package com.ibm.academia.restapi.ruleta.excepcion;

public class BadRequestException extends RuntimeException
{
	public BadRequestException(String mensaje)
	{
		super(mensaje);
	}
	
	private static final long serialVersionUID = 214050905794308139L;
}
