package com.ibm.academia.restapi.ruleta.service;


import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.restapi.ruleta.entidad.Ruleta;
import com.ibm.academia.restapi.ruleta.modelo.RuletaResponse;
import com.ibm.academia.restapi.ruleta.repositorio.RuletaRepository;

@Service
@Transactional
public class RuletaService
{
	
	@Autowired
	RuletaRepository ruletaRepository;
	
	
	
public void crearRuleta()
	{
		Ruleta ruleta = new Ruleta();
		ruleta.setEstado(true);
		ruleta.setAutor("Ulises");
		ruleta.setGanacia(0.0);
		save(ruleta);
		
	}
	
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
			return new ResponseEntity<>("No se encontro la ruleta",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Se abrio la Ruleta con id: " + id,HttpStatus.OK);
	}
	
public ResponseEntity<?> cerrar(long id) 
	{
		Ruleta ruletaObj = new Ruleta();
		RuletaResponse ruletaResponse = new RuletaResponse();
		if(existsById(id))
		{
			Optional<Ruleta> ruletaOptinal = findById(id);
			ruletaObj = ruletaOptinal.get();
			if(ruletaObj.getEstado()==true)
			{
				ruletaObj.setEstado(false);
			
			}
			ruletaResponse.setId(ruletaObj.getId());
			ruletaResponse.setEstado(ruletaObj.getEstado());
			ruletaResponse.setGanancias(ruletaObj.getGanacia());
			save(ruletaObj);
		} 
		else {
			return new ResponseEntity<>("No se encontro la ruleta",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(ruletaResponse,HttpStatus.OK);
	}

public ResponseEntity<?>jugar (Ruleta ruleta,Double apuesta,String color,Integer numero)
	{
		
        if(ruleta.getEstado()==false) {
			
			return new ResponseEntity<>("No se puede jugar en una mesa fuera de servicio",HttpStatus.BAD_REQUEST);
		}
		
		if(color.isBlank() && numero!=null)
		{
			
			return new ResponseEntity<>("Solo puede elegir color o Numero",HttpStatus.BAD_REQUEST);
		}
		
		if(!color.isBlank()&&apuesta<=10000)
		{
		    return apuestaColor(ruleta, color, apuesta);
					
				
		}

				
		if (numero!=null && apuesta<=10000)
		{
			return apuestaNumero(ruleta, numero, apuesta);
		}
					
		return new ResponseEntity<>("Datos ingresado invalidos",HttpStatus.BAD_REQUEST);		
		
	}
	
	

public ResponseEntity<?> apuestaColor(Ruleta ru,String color,Double apuesta)
	{
		Random ran = new Random ();
		int resultado = ran.nextInt(36 + 0) + 0;
			if(color.compareToIgnoreCase("rojo")==0)
			{
				if(resultado>=0 && resultado<=18)
				{
					return new ResponseEntity<>("Ganaste: "+ apuesta*2,HttpStatus.OK);
				}else {
					ru.setGanacia(apuesta+ru.getGanacia());
					return new ResponseEntity<>("Perdiste",HttpStatus.OK);
					
				}
				
				
			}
			if(color.compareToIgnoreCase("negro")==0)
			{
				if(resultado>=19 && resultado<=36)
				{
					return new ResponseEntity<>("Ganaste: "+ apuesta*2,HttpStatus.OK);
				}else {
					ru.setGanacia(apuesta+ru.getGanacia());
					return new ResponseEntity<>("Perdiste"+ apuesta*2,HttpStatus.OK);
				}
			}
	   return new ResponseEntity<>("Datos ingresado invalidos",HttpStatus.BAD_REQUEST);
		
	}
	

public ResponseEntity<?> apuestaNumero(Ruleta ru, Integer numero, Double apuesta) 
	{
		Random ran = new Random ();
		int resultado = ran.nextInt(36 + 0) + 0;
		if(numero<=36 && numero>=0)
		{
		if(resultado==numero)	
		   {
			return new ResponseEntity<>("Ganaste: "+ apuesta*2,HttpStatus.OK);
		   }else {
			   
			   ru.setGanacia(apuesta+ru.getGanacia());
			   return new ResponseEntity<>("Perdiste",HttpStatus.OK);
			   
		   }
		}
		return new ResponseEntity<>("Datos ingresado invalidos",HttpStatus.BAD_REQUEST);
	}
	

	@Transactional(readOnly = true)
	public List<Ruleta> list()
	{
        return ruletaRepository.findAll();
    }
	
	@Transactional(readOnly = true)
	public Optional<Ruleta>findById(long id)
	{
		return ruletaRepository.findById(id);
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
