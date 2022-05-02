package com.ibm.academia.restapi.ruleta.excepcion;

public class NotFoundExcepcion extends RuntimeException 
{
	public NotFoundExcepcion(String mensaje)
	{
		super(mensaje);
	}
	
	private static final long serialVersionUID = -421608164286101375L;
}