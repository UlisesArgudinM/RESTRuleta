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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "apuestas",schema = "Casino")
public class Apuesta implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;
	
	@Column(name = "id_ruleta")
	private Long idRuleta;
	
	@Column(name= "fecha_Apuesta")
	private Date fechaApuesta;
	
	@Column(name="ganador")
	private String ganador;
	
	@Column(name="premio")
	private Double premio;
	
	
	
	
	@PrePersist
	private void antesPersistir()
	{
		this.fechaApuesta = new Date();
	}
	
	
	
	
	
	public Apuesta(String ganador, Double premio) {
		super();
		this.ganador = ganador;
		this.premio = premio;
	}





	private static final long serialVersionUID = 5897037430824427965L;

}
