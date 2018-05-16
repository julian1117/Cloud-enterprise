package co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="DW_USUARIO")
public class DWusuario implements Serializable{
	
	@Id
	@Column(name="id")
	private int codigoUs;
	
	@Column(name="nombre_us")
	private int nombreUs;

	public DWusuario() {
		super();
	}

	public DWusuario(int codigoUs, int nombreUs) {
		super();
		this.codigoUs = codigoUs;
		this.nombreUs = nombreUs;
	}

	public int getCodigoUs() {
		return codigoUs;
	}

	public void setCodigoUs(int codigoUs) {
		this.codigoUs = codigoUs;
	}

	public int getNombreUs() {
		return nombreUs;
	}

	public void setNombreUs(int nombreUs) {
		this.nombreUs = nombreUs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigoUs;
		result = prime * result + nombreUs;
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
		DWusuario other = (DWusuario) obj;
		if (codigoUs != other.codigoUs)
			return false;
		if (nombreUs != other.nombreUs)
			return false;
		return true;
	}	
	
}
