package com.ibm.academia.restapi.ruleta.modelo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonProperty;

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
	@JsonProperty
	@NotNull(message = "No puede ser nulo")
	@Positive(message = "No puede ser id negativa")
	private Long id;
	
	@JsonProperty
	@NotNull(message = "No puede ser nulo")
	@NotEmpty(message = "No puede estar vacio")
	private String  color;
	
	@JsonProperty
	@NotNull(message = "No puede ser nulo")
	@NotEmpty(message = "No puede estar vacio")
	private Double apuesta;
	
	@JsonProperty
	@NotNull(message = "No puede ser nulo")
	@Positive(message = "No puede ser numeros negativos")
	private Integer numero;
	

}
