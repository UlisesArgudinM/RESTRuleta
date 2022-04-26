package com.ibm.academia.restapi.ruleta.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.restapi.ruleta.entidad.Ruleta;
import com.ibm.academia.restapi.ruleta.excepcion.NotFoundException;
import com.ibm.academia.restapi.ruleta.modelo.Id;
import com.ibm.academia.restapi.ruleta.modelo.RuletaRequest;
import com.ibm.academia.restapi.ruleta.modelo.dto.RuletaDTO;
import com.ibm.academia.restapi.ruleta.modelo.mapper.RuletaMapper;
import com.ibm.academia.restapi.ruleta.service.RuletaService;

import lombok.extern.slf4j.Slf4j;

@RestController 
@RequestMapping("/casino")
@CrossOrigin(origins = "*")
@Slf4j
public class RuletaController 
{
	@Autowired
	private RuletaService ruletaService;
	
	
	/**Enpoint que crear una ruleta 
	 * @author Usuario 24/04/22**/
	
	@PostMapping("/crear")
	public ResponseEntity<?>save()
	{
		ruletaService.crearRuleta();
		return new ResponseEntity<>("Se creo una ruleta",HttpStatus.OK);
	}
	
	/** Endpoint que cambia el estado de la ruleta a true
	 * @param Id Parametro que ingresa la ip en un body
	 * @return retorna un mensaje en donde dice si el cambio fue efectuado o no la ruleta
	 * @author Usuario 24/04/22*/
	
	@PutMapping("/apertura")
	public ResponseEntity<?>apertura(@RequestBody Id id)
	{
		
		return ruletaService.apertura(id.getId());
		
	}
	
	/** Endpoint que cambia el estado de la ruleta a false
	 * @param Id Parametro que ingresa la ip en un body
	 * @return retorna la informacion de la ruleta 
	 * @author Usuario 24/04/22*/
	
	@PutMapping("/cerrar")
	public ResponseEntity<?>clausura(@RequestBody Id id)
	{
		return ruletaService.cerrar(id.getId());
		
		
	}
	
	/** Endpoind que lista todas la ruletas en la base de datos
	 * @return retorna una lista de tipo Ruleta
	 * @author Usuario 24/04/22 **/
	
	
	@PostMapping("/listar")
	public ResponseEntity<?>listarTodo()
	{
		List<Ruleta> ruletas = ruletaService.list();
		List<RuletaDTO>ruletaDTO = ruletas.stream().map(RuletaMapper::mapRuleta).collect(Collectors.toList());
		
		return new ResponseEntity<List<RuletaDTO>>(ruletaDTO,HttpStatus.OK);
	}
	
	
	/** Endpoind que lista todas la ruletas en la base de datos
	 * @return retorna una lista de tipo Ruleta
	 * @author Usuario 24/04/22 **/
	
	@PutMapping("/apostar")
	public ResponseEntity<?>apostar(@RequestBody @Valid RuletaRequest ruletaRequest,BindingResult resultado)
	{
		log.info("Por favor de ingresar el tipo de apuesta e id de la ruleta en sus respectivos espacios");
		log.info("Puede solo apostar a un numero o a un color de igual manera debe ser una apuesta menor de 10000 Dolares");
		
		if(resultado.hasFieldErrors()) {
		throw new NotFoundException("El cuerpo de la peticion no es valido");
		}
		Optional<Ruleta> ruOp = ruletaService.findById(ruletaRequest.getId());
		Ruleta ruleta = ruOp.get();
		return ruletaService.jugar(ruleta, ruletaRequest.getApuesta(), ruletaRequest.getColor(), ruletaRequest.getNumero());
		
	}
	
	
	
}
