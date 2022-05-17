package com.ibm.academia.restapi.ruleta.service;


import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.restapi.ruleta.entidad.Apuesta;
import com.ibm.academia.restapi.ruleta.entidad.Ruleta;
import com.ibm.academia.restapi.ruleta.excepcion.BadRequestException;
import com.ibm.academia.restapi.ruleta.excepcion.NotFoundExcepcion;
import com.ibm.academia.restapi.ruleta.modelo.dto.ApuestaDTO;
import com.ibm.academia.restapi.ruleta.modelo.mapper.ApuestaMapper;
import com.ibm.academia.restapi.ruleta.repositorio.ApuestaRepository;
import com.ibm.academia.restapi.ruleta.repositorio.RuletaRepository;

@Service
@Transactional
public class RuletaService implements IRuletaService
{
	
	@Autowired(required = true)
	RuletaRepository ruletaRepository;
	
	@Autowired
	ApuestaRepository apuestaRepository;
	
	
@Override
public void crearRuleta()
	{
		Ruleta ruleta = new Ruleta();
		ruleta.setEstado(true);
		ruleta.setAutor("Ulises");
		ruleta.setGanacia(0.0);
		save(ruleta);
		
	}
@Override	
public ResponseEntity<?> apertura(Long id)
	{
		Ruleta ruletaObj = new Ruleta();
		if(existsById(id))
		{
			Optional<Ruleta> ruletaOptinal = findById(id);
			ruletaObj = ruletaOptinal.get();
			if(ruletaObj.getEstado()==false)
			{
				ruletaObj.setEstado(true);
			
			}
			save(ruletaObj);
		} 
		else {
			throw new NotFoundExcepcion("No se encontro la ruleta");
		}
		return new ResponseEntity<>("Se abrio la Ruleta con id: " + id,HttpStatus.OK);
	}
@Override
public ResponseEntity<?> cerrar(Long id) 
	{
		Ruleta ruletaObjeto = new Ruleta();
		if(existsById(id))
		{
			Optional<Ruleta> ruletaOptinal = findById(id);
			ruletaObjeto = ruletaOptinal.get();
			if(ruletaObjeto.getEstado()==true)
			{
				ruletaObjeto.setEstado(false);
				
			}
				
			save(ruletaObjeto);
		} 
		else {
			throw new NotFoundExcepcion("no se encontro la ruleta");
		}
		List<Apuesta> apuestas = ruletaObjeto.getApuestas();
		List<ApuestaDTO> apuestaDTO = apuestas.stream().map(ApuestaMapper::mapApuesta).collect(Collectors.toList());
		return new ResponseEntity<List<ApuestaDTO>>(apuestaDTO,HttpStatus.OK);
	}
@Override
public ResponseEntity<?>jugar (Ruleta ruleta,Double apuesta,String color,Integer numero)
	{
		
        if(ruleta.getEstado()==false) {
			
			throw new BadRequestException("No se puede jugar en una mesa fuera de servicio");
		}
		
		if(!color.isBlank() && numero!=null)
		{
			
			throw new BadRequestException("Solo puede elegir color o Numero");
		}
		
		if(!color.isBlank()&&apuesta<=10000)
		{
		    return apuestaColor(ruleta, color, apuesta);
					
				
		}

				
		if (numero!=null && apuesta<=10000)
		{
			return apuestaNumero(ruleta, numero, apuesta);
		}
					
	    throw new BadRequestException("Datos ingresado invalidos");		
		
	}
	
	
@Override
public ResponseEntity<?> apuestaColor(Ruleta ruleta,String color,Double apuesta)
	{
		Random ran = new Random ();
		Apuesta apuestaObjeto = new Apuesta();
		int resultado = ran.nextInt(36 + 0) + 0;
		
			if(color.compareToIgnoreCase("rojo")==0)
			{
				if(resultado>=0 && resultado<=18)
				{
					apuestaObjeto.setGanador("Visistante");
					apuestaObjeto.setPremio(apuesta*2);
					apuestaObjeto.setRuleta(ruleta);
					save(apuestaObjeto);
					return new ResponseEntity<>("Ganaste: "+ apuesta*2,HttpStatus.OK);
					
				}else {
					apuestaObjeto.setGanador("Casa");
					apuestaObjeto.setPremio(apuesta);
					apuestaObjeto.setRuleta(ruleta);
					save(apuestaObjeto);
					ruleta.setGanacia(apuesta+ruleta.getGanacia());
					return new ResponseEntity<>("Perdiste",HttpStatus.OK);
					
				}
				
				
				
			}
			if(color.compareToIgnoreCase("negro")==0)
			{
				if(resultado>=19 && resultado<=36)
				{
					apuestaObjeto.setGanador("Visistante");
					apuestaObjeto.setPremio(apuesta*2);
					apuestaObjeto.setRuleta(ruleta);
					save(apuestaObjeto);
					return new ResponseEntity<>("Ganaste: "+ apuesta*2,HttpStatus.OK);
				}else {
					apuestaObjeto.setGanador("Casa");
					apuestaObjeto.setPremio(apuesta);
					apuestaObjeto.setRuleta(ruleta);
					save(apuestaObjeto);
					ruleta.setGanacia(apuesta+ruleta.getGanacia());
					return new ResponseEntity<>("Perdiste"+ apuesta*2,HttpStatus.OK);
				}
			}
	   throw new BadRequestException("Datos ingresado invalidos");
		
	}
	
@Override
public ResponseEntity<?> apuestaNumero(Ruleta ruleta, Integer numero, Double apuesta) 
	{
		Random ran = new Random ();
		Apuesta apuestaObjeto= new Apuesta();
		int resultado = ran.nextInt(36 + 0) + 0;
		if(numero<=36 && numero>=0)
		{
		if(resultado==numero)	
		   {
			apuestaObjeto.setGanador("Visistante");
			apuestaObjeto.setPremio(apuesta*2);
			apuestaObjeto.setRuleta(ruleta);
			save(apuestaObjeto);
			return new ResponseEntity<>("Ganaste: "+ apuesta*2,HttpStatus.OK);
		   }else {
			    apuestaObjeto.setGanador("Casa");
				apuestaObjeto.setPremio(apuesta);
				apuestaObjeto.setRuleta(ruleta);
				save(apuestaObjeto);
			   ruleta.setGanacia(apuesta+ruleta.getGanacia());
			   return new ResponseEntity<>("Perdiste",HttpStatus.OK);
			   
		   }
		}
		throw new BadRequestException("Datos ingresado invalidos");
	}
	
	
	@Transactional(readOnly = true)
	public List<Ruleta> list()
	{
        return ruletaRepository.findAll();
    }
	@Transactional(readOnly = true)
	public List<Apuesta> listApuesta()
	{
        return apuestaRepository.findAll();
    }
	@Override
	@Transactional(readOnly = true)
	public Optional<Ruleta>findById(Long id)
	{
		return ruletaRepository.findById(id);
	}
	@Override
	@Transactional(readOnly = true)
	public Optional<Apuesta>findByIdApuesta(Long id)
	{
		return apuestaRepository.findById(id);
	}
	
	public void save(Apuesta apuesta)
	{
		apuestaRepository.save(apuesta);
	}
	

	public void save(Ruleta ruleta)
	{
		ruletaRepository.save(ruleta);
	}
	

	public void delete (Long id)
	{
		ruletaRepository.deleteById(id);
	}
	
	
	@Transactional(readOnly = true)
	public boolean existsById(Long id)
	{
        return ruletaRepository.existsById(id);
    }
	

}
