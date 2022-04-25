package com.ibm.academia.restapi.ruleta.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.academia.restapi.ruleta.entidad.Ruleta;

public interface RuletaRepository extends JpaRepository<Ruleta, Long>
{
		Optional<Ruleta> findById(long id);
}
