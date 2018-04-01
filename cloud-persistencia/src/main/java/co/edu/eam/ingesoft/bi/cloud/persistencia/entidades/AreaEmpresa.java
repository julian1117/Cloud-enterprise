package co.edu.eam.ingesoft.bi.cloud.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AreaEmpresa")
public class AreaEmpresa implements Serializable{

	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="nombreArea")
	private String nombreArea;
	
	@Column(name="descripcion")
	private String descripcion;

	public AreaEmpresa() {
		super();
	}

	public AreaEmpresa(Integer id, String nombreArea, String descripcion) {
		super();
		this.id = id;
		this.nombreArea = nombreArea;
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreArea() {
		return nombreArea;
	}

	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
