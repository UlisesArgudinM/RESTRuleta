package com.ibm.academia.restapi.ruleta.modelo;


import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RuletaResponse 
{
	@NotNull
	private Long id;
	
	@NotNull
	private Double ganancias;
	
	@NotNull
	private Boolean estado;
	

}
