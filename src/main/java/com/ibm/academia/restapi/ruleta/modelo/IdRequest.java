package com.ibm.academia.restapi.ruleta.modelo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IdRequest 
{
	@NotNull(message = "No puede ser nulo")
	@Positive(message = "No puede ser numero negativo")
	private Long id;

}
