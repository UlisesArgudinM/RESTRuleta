package com.ibm.academia.restapi.ruleta.modelo.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApuestaDTO implements Serializable
{
	
    private Date fechaApuesta;
    private String ganador;
    private Double premio;
	private static final long serialVersionUID = -2154719951806968690L;

}
