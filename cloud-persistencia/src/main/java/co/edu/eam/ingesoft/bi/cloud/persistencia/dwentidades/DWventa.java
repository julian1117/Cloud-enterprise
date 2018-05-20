package co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@IdClass(DWVentaPK.class)
@Table(name="DWVenta")
public class DWventa implements Serializable {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "Inventario_id")
	private DWInventario inventario;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "GestionVenta_id")
	private DWgestionVenta gestionVenta;
	
	@ManyToOne
	@JoinColumn(name = "empleado_id")
	private DWempleado empleado;
	
	@ManyToOne
	@JoinColumn(name = "persona_id")
	private DWpersona persona;
	
	@ManyToOne
	@JoinColumn(name = "producto_id")
	private DWProducto producto;
	
	@Column(name = "CANTIDAD")
	private Integer cantidad;

	public DWventa() {
		super();
	}

	

	public DWventa(DWInventario inventario, DWgestionVenta gestionVenta, DWempleado empleado, DWpersona persona,
			DWProducto producto, Integer cantidad) {
		super();
		this.inventario = inventario;
		this.gestionVenta = gestionVenta;
		this.empleado = empleado;
		this.persona = persona;
		this.producto = producto;
		this.cantidad = cantidad;
	}



	public DWInventario getInventario() {
		return inventario;
	}

	public void setInventario(DWInventario inventario) {
		this.inventario = inventario;
	}

	public DWgestionVenta getGestionVenta() {
		return gestionVenta;
	}

	public void setGestionVenta(DWgestionVenta gestionVenta) {
		this.gestionVenta = gestionVenta;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	

	public DWempleado getEmpleado() {
		return empleado;
	}



	public void setEmpleado(DWempleado empleado) {
		this.empleado = empleado;
	}



	public DWpersona getPersona() {
		return persona;
	}



	public void setPersona(DWpersona persona) {
		this.persona = persona;
	}



	public DWProducto getProducto() {
		return producto;
	}



	public void setProducto(DWProducto producto) {
		this.producto = producto;
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
		DWventa other = (DWventa) obj;
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
