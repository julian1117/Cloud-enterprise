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


@Entity
@Table(name="DWVenta")
@NamedQuery(name = DWventa.TRAER_VENTA, query = "SELECT A FROM DWventa A")
public class DWventa implements Serializable {
	
	public static final String TRAER_VENTA = "DWauditoria.listdwVENTA";
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer idVenta;
	
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

	public Integer getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
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
		result = prime * result + ((cantidad == null) ? 0 : cantidad.hashCode());
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
		if (cantidad == null) {
			if (other.cantidad != null)
				return false;
		} else if (!cantidad.equals(other.cantidad))
			return false;
		return true;
	}

}
