package com.ibm.academia.restapi.ruleta.entidad;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Ruletas")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Ruleta implements Serializable
{
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private long id;
	
	@Column(name = "Ganancias")
	private Double ganacia;
	
	@Column(name = "Estado")
	private Boolean estado;
	
	@Column(name = "Autor")
	private String autor;
	
	@Column (name = "fechaCreacion", nullable = false)
	private Date fechaCreacion;
	 
	
	
	@PrePersist
	private void antesPersistir()
	{
		this.fechaCreacion = new Date();
	}

	private static final long serialVersionUID = -5413128261886649018L;
}
