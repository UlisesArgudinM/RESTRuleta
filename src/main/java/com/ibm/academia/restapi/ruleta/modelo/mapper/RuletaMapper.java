package com.ibm.academia.restapi.ruleta.modelo.mapper;

import com.ibm.academia.restapi.ruleta.entidad.Ruleta;
import com.ibm.academia.restapi.ruleta.modelo.dto.RuletaDTO;

public class RuletaMapper 
{
	public static RuletaDTO mapRuleta(Ruleta ruleta)
	{
		RuletaDTO ruletaDTO = new RuletaDTO();
		ruletaDTO.setId(ruleta.getId());
		ruletaDTO.setEstado(ruleta.getEstado());
		ruletaDTO.setGanancia(ruleta.getGanacia());
		return ruletaDTO;
	}

}
