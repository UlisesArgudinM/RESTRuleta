package com.ibm.academia.restapi.ruleta.excepcion;

public class NotFoundException extends RuntimeException 
{
	public NotFoundException(String mensaje)
	{
		super(mensaje);
	}
	
	private static final long serialVersionUID = -421608164286101375L;
}