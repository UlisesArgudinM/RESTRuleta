package com.ibm.academia.restapi.ruleta.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RuletaRequest 
{
	private Long id;
	private String  color;
	private Double apuesta;
	private Integer numero;
	

}
