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
@Table(name="DWVenta")
public class DWventa implements Serializable {
	
	@Id
	@Column(name="id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "Inventario_id")
	private DWInventario inventario;
	
	
	@ManyToOne
	@JoinColumn(name = "GestionVenta_id")
	private DWgestionVenta gestionVenta;
	
	@ManyToOne
	@JoinColumn(name = "empleado_id")
	private DWempleado empleado;
	
	@ManyToOne
	@JoinColumn(name = "persona_id")
	private DWpersona persona;
	
	
	@Column(name = "CANTIDAD")
	private Integer cantidad;

	public DWventa() {
		super();
	}

	

	public DWventa(DWInventario inventario, DWgestionVenta gestionVenta, DWempleado empleado, DWpersona persona,
			Integer cantidad) {
		super();
		this.inventario = inventario;
		this.gestionVenta = gestionVenta;
		this.empleado = empleado;
		this.persona = persona;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	

	
	



	

	
	

}
