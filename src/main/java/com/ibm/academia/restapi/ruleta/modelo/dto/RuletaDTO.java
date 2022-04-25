package com.ibm.academia.restapi.ruleta.modelo.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RuletaDTO implements Serializable
{

	private long id;
	private Boolean estado;
	private Double ganancia;
	private static final long serialVersionUID = 4162996624678939934L;
	
	
}
