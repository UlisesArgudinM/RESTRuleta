package com.ibm.academia.restapi.ruleta.controller;

import java.util.List;
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
import com.ibm.academia.restapi.ruleta.excepcion.BadRequestException;
import com.ibm.academia.restapi.ruleta.excepcion.NotFoundExcepcion;
import com.ibm.academia.restapi.ruleta.modelo.IdRequest;
import com.ibm.academia.restapi.ruleta.modelo.RuletaRequest;
import com.ibm.academia.restapi.ruleta.modelo.dto.RuletaDTO;
import com.ibm.academia.restapi.ruleta.modelo.mapper.RuletaMapper;
import com.ibm.academia.restapi.ruleta.service.IRuletaService;


@RestController 
@RequestMapping("/casino")
@CrossOrigin(origins = "*")

public class RuletaController 
{
	
	@Autowired 
	private IRuletaService ruletaService;
	
	
	/**Enpoint que crear una ruleta 
	 * @author Usuario 24/04/22**/
	
	@PutMapping("/crear")
	public ResponseEntity<?>save()
	{
		ruletaService.crearRuleta();
		return new ResponseEntity<>("Se creo una ruleta",HttpStatus.OK);
	}
	
	/** Endpoint que cambia el estado de la ruleta a true
	 * @param Id Parametro que ingresa la ip en un body
	 * @return retorna un mensaje en donde dice si el cambio fue efectuado o no la ruleta a abierto
	 * @author Usuario 24/04/22*/
	
	@PutMapping("/apertura")
	public ResponseEntity<?>apertura(@RequestBody @Valid IdRequest id, BindingResult resultado)
	{
		if(id.getId()==null) 
		throw new BadRequestException("No puede ser nulo");
		
		
		return ruletaService.apertura(id.getId());
		
	}
	
	/** Endpoint que cambia el estado de la ruleta a false
	 * @param Id Parametro que ingresa la ip en un body
	 * @return retorna la informacion de la ruleta de la cual quiere cerrar
	 * @author Usuario 24/04/22*/
	
	@PutMapping("/cerrar")
	public ResponseEntity<?>clausura(@RequestBody @Valid IdRequest id,BindingResult resultado)
	{
		if(id.getId()==null) 
		throw new BadRequestException("No puede ser nulo");
	
		
		return ruletaService.cerrar(id.getId());
		
		
	}
	
	/** Endpoind que lista todas la ruletas en la base de datos
	 * @return retorna la lista de todas la ruletas
	 * @author Usuario 24/04/22 **/
	
	
	@PostMapping("/listar")
	public ResponseEntity<?>listarTodo()
	{
		List<Ruleta> ruletas = ruletaService.list();
		List<RuletaDTO>ruletaDTO = ruletas.stream().map(RuletaMapper::mapRuleta).collect(Collectors.toList());
		
		return new ResponseEntity<List<RuletaDTO>>(ruletaDTO,HttpStatus.OK);
	}
	
	
	/** Endpoind que lista todas la ruletas en la base de datos
	 * @return retorna si gano o perdio contra la ruleta
	 * @author Usuario 24/04/22 
	 * @throws NotFoundExcepcion **/
	
	@PostMapping("/apostar")
	public ResponseEntity<?>apostar(@RequestBody @Valid RuletaRequest ruletaRequest,BindingResult resultado) 
	{
		if(ruletaRequest.getId()==null || ruletaRequest.getApuesta()==null)
			throw new BadRequestException("No puede ser nulo el id o la apuesta");
			
			
		Ruleta ruOp = ruletaService.findById(ruletaRequest.getId()).orElseThrow(() -> new NotFoundExcepcion("Ruleta no encontrada"));
		
		return ruletaService.jugar(ruOp, ruletaRequest.getApuesta(), ruletaRequest.getColor(), ruletaRequest.getNumero());
		
	}
	
	
	
}
