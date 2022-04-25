package com.ibm.academia.restapi.ruleta.service;


import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.restapi.ruleta.entidad.Ruleta;
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
		ruleta.setGanacia(0.0);
		save(ruleta);
		
	}
	public Boolean apertura(long id)
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
			return false;
		}
		return true;
	}
	public Ruleta cerrar(long id) 
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
		return ruletaObj;
	}
	
	
	
	
	public Ruleta apuestaColor(Ruleta ru,String color,Double apuesta)
	{
		Random ran = new Random ();
		int resultado = ran.nextInt(36 + 0) + 0;
			if(color.compareToIgnoreCase("rojo")<=0)
			{
				if(resultado>=0 && resultado<=18)
				{
					ru.setUltimoGanador(true);
				}else {
					ru.setUltimoGanador(false);
					ru.setGanacia(apuesta+ru.getGanacia());
					
				}
				save(ru);
				return ru;
				
			}
			if(color.compareToIgnoreCase("negro")<=0)
			{
				if(resultado>=19 && resultado<=36)
				{
					ru.setUltimoGanador(true);
				}else {
					ru.setUltimoGanador(false);
					ru.setGanacia(apuesta+ru.getGanacia());
				}
				save(ru);
				return ru;
			}
		return null;
		
	}
	public Ruleta apuestaNumero(Ruleta ru, Integer numero, Double apuesta) 
	{
		Random ran = new Random ();
		int resultado = ran.nextInt(36 + 0) + 0;
		if(numero<=36 && numero>=0)
		{
		if(resultado==numero)	
		   {
			  ru.setUltimoGanador(true);
		   }else {
			   ru.setUltimoGanador(false);
			   ru.setGanacia(apuesta+ru.getGanacia());
			   
		   }
		save(ru);
		return ru;
		}
		return null;
	}
	
	public List<Ruleta> list()
	{
        return ruletaRepository.findAll();
    }
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
	
	public boolean existsById(long id)
	{
        return ruletaRepository.existsById(id);
    }
	

}
