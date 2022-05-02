package com.ibm.academia.restapi.ruleta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.ibm.academia.restapi.ruleta.entidad.Ruleta;

public interface IRuletaService 
{
	void crearRuleta();
	ResponseEntity<?> apertura(long id);
	ResponseEntity<?> cerrar(long id);
	ResponseEntity<?>jugar (Ruleta ruleta,Double apuesta,String color,Integer numero);
	ResponseEntity<?> apuestaColor(Ruleta ru,String color,Double apuesta);
	ResponseEntity<?> apuestaNumero(Ruleta ru, Integer numero, Double apuesta);
	List<Ruleta> list();
	Optional<Ruleta>findById(long id);
}
