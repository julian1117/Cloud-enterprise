package co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="DWAreaEmpresa")
public class DWareaEmpresa implements Serializable{
	


	@Id
	@Column(name="id")
	private Integer idArea;
	
	@Column(name="nombreArea")
	private String nombreArea;
	
	@Column(name="descripcion")
	private String descripcion;

	public DWareaEmpresa() {
		super();
	}

	
	public DWareaEmpresa(Integer idArea, String nombreArea, String descripcion) {
		super();
		this.idArea = idArea;
		this.nombreArea = nombreArea;
		this.descripcion = descripcion;
	}



	public Integer getIdArea() {
		return idArea;
	}

	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idArea == null) ? 0 : idArea.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DWareaEmpresa other = (DWareaEmpresa) obj;
		if (idArea == null) {
			if (other.idArea != null)
				return false;
		} else if (!idArea.equals(other.idArea))
			return false;
		return true;
	}

	
	
	
}
