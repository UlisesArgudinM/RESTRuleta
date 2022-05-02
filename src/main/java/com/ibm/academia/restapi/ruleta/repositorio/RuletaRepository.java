package com.ibm.academia.restapi.ruleta.repositorio;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.restapi.ruleta.entidad.Ruleta;

@Repository
public interface RuletaRepository extends JpaRepository<Ruleta, Long>
{  
	
}
