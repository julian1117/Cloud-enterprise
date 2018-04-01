package co.edu.eam.ingesoft.bi.cloud.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Pais")
public class Pais implements Serializable{
	
	@Id
	@Column(name="id")
	private Integer idPais;
	
	@Column(name="nombre",nullable=false,length=25)
	private String nombre;

	public Pais() {
		super();
	}

	public Pais(Integer idPais, String nombre) {
		super();
		this.idPais = idPais;
		this.nombre = nombre;
	}

	public Integer getIdPais() {
		return idPais;
	}

	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
}
