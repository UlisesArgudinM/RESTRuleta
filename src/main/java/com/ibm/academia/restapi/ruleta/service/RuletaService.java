package com.ibm.academia.restapi.ruleta.service;


import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.restapi.ruleta.entidad.Apuesta;
import com.ibm.academia.restapi.ruleta.entidad.Ruleta;
import com.ibm.academia.restapi.ruleta.excepcion.BadRequestException;
import com.ibm.academia.restapi.ruleta.excepcion.NotFoundExcepcion;
import com.ibm.academia.restapi.ruleta.repositorio.ApuestaRepository;
import com.ibm.academia.restapi.ruleta.repositorio.RuletaRepository;

@Service
@Transactional
public class RuletaService implements IRuletaService
{
	
	@Autowired
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
public ResponseEntity<?> apertura(long id)
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
public List<Apuesta> cerrar(long id) 
	{
		Ruleta ruletaObj = new Ruleta();
		if(existsById(id))
		{
			Optional<Ruleta> ruletaOptinal = findById(id);
			ruletaObj = ruletaOptinal.get();
			if(ruletaObj.getEstado()==true)
			{
				ruletaObj.setEstado(false);
				
			}
				
			save(ruletaObj);
		} 
		else {
			return null;
		}
		return ruletaObj.getApuestas();
	}
@Override
public ResponseEntity<?>jugar (Ruleta ruleta,Double apuesta,String color,Integer numero)
	{
		
        if(ruleta.getEstado()==false) {
			
			throw new BadRequestException("No se puede jugar en una mesa fuera de servicio");
		}
		
		if(color.isBlank() && numero!=null)
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
public ResponseEntity<?> apuestaColor(Ruleta ru,String color,Double apuesta)
	{
		Random ran = new Random ();
		Apuesta apuestaObj = new Apuesta();
		int resultado = ran.nextInt(36 + 0) + 0;
		
			if(color.compareToIgnoreCase("rojo")==0)
			{
				if(resultado>=0 && resultado<=18)
				{
					apuestaObj.setGanador("Visistante");
					apuestaObj.setPremio(apuesta*2);
					apuestaObj.setRuleta(ru);
					save(apuestaObj);
					return new ResponseEntity<>("Ganaste: "+ apuesta*2,HttpStatus.OK);
					
				}else {
					apuestaObj.setGanador("Casa");
					apuestaObj.setPremio(apuesta);
					apuestaObj.setRuleta(ru);
					save(apuestaObj);
					ru.setGanacia(apuesta+ru.getGanacia());
					return new ResponseEntity<>("Perdiste",HttpStatus.OK);
					
				}
				
				
				
			}
			if(color.compareToIgnoreCase("negro")==0)
			{
				if(resultado>=19 && resultado<=36)
				{
					apuestaObj.setGanador("Visistante");
					apuestaObj.setPremio(apuesta*2);
					apuestaObj.setRuleta(ru);
					save(apuestaObj);
					return new ResponseEntity<>("Ganaste: "+ apuesta*2,HttpStatus.OK);
				}else {
					apuestaObj.setGanador("Casa");
					apuestaObj.setPremio(apuesta);
					apuestaObj.setRuleta(ru);
					save(apuestaObj);
					ru.setGanacia(apuesta+ru.getGanacia());
					return new ResponseEntity<>("Perdiste"+ apuesta*2,HttpStatus.OK);
				}
			}
	   throw new BadRequestException("Datos ingresado invalidos");
		
	}
	
@Override
public ResponseEntity<?> apuestaNumero(Ruleta ru, Integer numero, Double apuesta) 
	{
		Random ran = new Random ();
		Apuesta apuestaObj= new Apuesta();
		int resultado = ran.nextInt(36 + 0) + 0;
		if(numero<=36 && numero>=0)
		{
		if(resultado==numero)	
		   {
			apuestaObj.setGanador("Visistante");
			apuestaObj.setPremio(apuesta*2);
			apuestaObj.setRuleta(ru);
			save(apuestaObj);
			return new ResponseEntity<>("Ganaste: "+ apuesta*2,HttpStatus.OK);
		   }else {
			    apuestaObj.setGanador("Casa");
				apuestaObj.setPremio(apuesta);
				apuestaObj.setRuleta(ru);
				save(apuestaObj);
			   ru.setGanacia(apuesta+ru.getGanacia());
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
	
	public List<Apuesta> listApuesta()
	{
        return apuestaRepository.findAll();
    }
	@Override
	@Transactional(readOnly = true)
	public Optional<Ruleta>findById(long id)
	{
		return ruletaRepository.findById(id);
	}
	@Override
	@Transactional(readOnly = true)
	public Optional<Apuesta>findByIdApuesta(long id)
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
	

	public void delete (long id)
	{
		ruletaRepository.deleteById(id);
	}
	
	
	@Transactional(readOnly = true)
	public boolean existsById(long id)
	{
        return ruletaRepository.existsById(id);
    }
	

}
