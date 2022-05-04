package com.ibm.academia.restapi.ruleta.modelo.mapper;

import com.ibm.academia.restapi.ruleta.entidad.Apuesta;
import com.ibm.academia.restapi.ruleta.modelo.dto.ApuestaDTO;

public class ApuestaMapper 
{
	public static ApuestaDTO mapApuesta(Apuesta apuesta)
	{
		ApuestaDTO apuestaDTO = new ApuestaDTO();
		apuestaDTO.setGanador(apuesta.getGanador());
		apuestaDTO.setFechaApuesta(apuesta.getFechaApuesta());
		apuestaDTO.setPremio(apuesta.getPremio());
		return apuestaDTO;
	}

}
