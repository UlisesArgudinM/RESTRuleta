package com.ibm.academia.restapi.ruleta.repositorio;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.restapi.ruleta.datos.DatosRandom;
import com.ibm.academia.restapi.ruleta.entidad.Ruleta;

@DataJpaTest
public class RuletaRepositoryTest 
{

	@Autowired
	@Qualifier("repositorioRuleta")
	private RuletaRepository ruletaRepository;
	
	@Test
	@DisplayName("Test:Buscar por id")
	void buscarId()
	{
		Ruleta ruleta = ruletaRepository.save(DatosRandom.ruleta01());
		
		Optional<Ruleta> expected = ruletaRepository.findById(1L);
		
		assertThat(expected.get()).isInstanceOf(Ruleta.class);
		assertThat(expected.get()).isEqualTo(ruleta);
	}
}
