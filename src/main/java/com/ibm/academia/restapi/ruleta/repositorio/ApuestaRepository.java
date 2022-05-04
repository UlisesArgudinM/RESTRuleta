package com.ibm.academia.restapi.ruleta.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.restapi.ruleta.entidad.Apuesta;

@Repository
public interface ApuestaRepository extends JpaRepository<Apuesta, Long>
{
	
}
