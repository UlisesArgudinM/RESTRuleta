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
@Table(name = "ruletas",schema = "Casino")
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

	public Ruleta(long id, Double ganacia, Boolean estado, String autor) {
		super();
		this.id = id;
		this.ganacia = ganacia;
		this.estado = estado;
		this.autor = autor;
	}

	private static final long serialVersionUID = -5413128261886649018L;
}
