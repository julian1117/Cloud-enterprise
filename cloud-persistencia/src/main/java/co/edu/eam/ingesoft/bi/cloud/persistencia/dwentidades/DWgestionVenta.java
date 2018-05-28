package co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="DWGestionVenta")
public class DWgestionVenta implements Serializable {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer idGestionVenta;
	
	@Column(name="numero_factura")
	private String numeroFactura;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha")
	private Date fecha;

	public DWgestionVenta() {
		super();
	}

	public DWgestionVenta(Integer idGestionVenta, String numeroFactura, Date fecha) {
		super();
		this.idGestionVenta = idGestionVenta;
		this.numeroFactura = numeroFactura;
		this.fecha = fecha;
	}

	public Integer getIdGestionVenta() {
		return idGestionVenta;
	}

	public void setIdGestionVenta(Integer idGestionVenta) {
		this.idGestionVenta = idGestionVenta;
	}

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((idGestionVenta == null) ? 0 : idGestionVenta.hashCode());
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
		DWgestionVenta other = (DWgestionVenta) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (idGestionVenta == null) {
			if (other.idGestionVenta != null)
				return false;
		} else if (!idGestionVenta.equals(other.idGestionVenta))
			return false;
		return true;
	}
	
}
