package co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="page_prin_wiki")
public class DWPaginaPrinWIKI implements Serializable{

	@Id
	@Column(name="id_pagina_prin")
	private Integer idPaginaPrin;
	
	@Column(name="page_random")
	private double tamanoPagina;
	
	@Column(name="page_is_redirect")
	private String redirige;

	public DWPaginaPrinWIKI() {
		super();
	}

	public DWPaginaPrinWIKI(Integer idPaginaPrin, double tamanoPagina, String redirige
			) {
		super();
		this.idPaginaPrin = idPaginaPrin;
		this.tamanoPagina = tamanoPagina;
		this.redirige = redirige;
	}

	public Integer getIdPaginaPrin() {
		return idPaginaPrin;
	}

	public void setIdPaginaPrin(Integer idPaginaPrin) {
		this.idPaginaPrin = idPaginaPrin;
	}

	public double getTamanoPagina() {
		return tamanoPagina;
	}

	public void setTamanoPagina(double tamanoPagina) {
		this.tamanoPagina = tamanoPagina;
	}

	public String getRedirige() {
		return redirige;
	}

	public void setRedirige(String redirige) {
		this.redirige = redirige;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPaginaPrin == null) ? 0 : idPaginaPrin.hashCode());
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
		DWPaginaPrinWIKI other = (DWPaginaPrinWIKI) obj;
		if (idPaginaPrin == null) {
			if (other.idPaginaPrin != null)
				return false;
		} else if (!idPaginaPrin.equals(other.idPaginaPrin))
			return false;
		return true;
	}
	
	
	
}
