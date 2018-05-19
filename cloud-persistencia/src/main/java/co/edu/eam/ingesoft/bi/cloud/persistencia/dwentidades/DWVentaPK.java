package co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

public class DWVentaPK implements Serializable {
	
	
	

	private Integer inventario;
	
	private Integer gestionVenta;
	
	

	public DWVentaPK() {
		super();
	}



	public DWVentaPK(Integer inventario, Integer gestionVenta) {
		super();
		this.inventario = inventario;
		this.gestionVenta = gestionVenta;
	}



	public Integer getInventario() {
		return inventario;
	}



	public void setInventario(Integer inventario) {
		this.inventario = inventario;
	}



	public Integer getGestionVenta() {
		return gestionVenta;
	}



	public void setGestionVenta(Integer gestionVenta) {
		this.gestionVenta = gestionVenta;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gestionVenta == null) ? 0 : gestionVenta.hashCode());
		result = prime * result + ((inventario == null) ? 0 : inventario.hashCode());
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
		DWVentaPK other = (DWVentaPK) obj;
		if (gestionVenta == null) {
			if (other.gestionVenta != null)
				return false;
		} else if (!gestionVenta.equals(other.gestionVenta))
			return false;
		if (inventario == null) {
			if (other.inventario != null)
				return false;
		} else if (!inventario.equals(other.inventario))
			return false;
		return true;
	}



}
