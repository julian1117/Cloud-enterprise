package co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="DWGestionVenta")
@NamedQuery(name=DWgestionVenta.LISTAR_FACTURA, query="SELECT g from GestionVenta g")
public class DWgestionVenta implements Serializable {
	
	public static final  String LISTAR_FACTURA = "GestionVenta.listaFacturaa";

	@Id
	@Column(name="id")
	private Integer idFactura;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha")
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name="Empleado_cedula")
	private DWempleado empleado;
	
	@ManyToOne
	@JoinColumn(name="Persona_cedula")
	private DWpersona persona;

	public DWgestionVenta() {
		super();
	}

	public DWgestionVenta(Integer idFactura, Date fecha, DWempleado empleado, DWpersona persona) {
		super();
		this.idFactura = idFactura;
		this.fecha = fecha;
		this.empleado = empleado;
		this.persona = persona;
	}

	public Integer getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	public static String getListarFactura() {
		return LISTAR_FACTURA;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idFactura == null) ? 0 : idFactura.hashCode());
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
		if (idFactura == null) {
			if (other.idFactura != null)
				return false;
		} else if (!idFactura.equals(other.idFactura))
			return false;
		return true;
	}
	
	

	
	
}
