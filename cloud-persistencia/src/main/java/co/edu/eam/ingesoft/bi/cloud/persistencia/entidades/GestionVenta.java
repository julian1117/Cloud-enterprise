package co.edu.eam.ingesoft.bi.cloud.persistencia.entidades;

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
@Table(name="GestionVenta")
@NamedQuery(name=GestionVenta.LISTAR_FACTURA, query="SELECT g from GestionVenta g")
public class GestionVenta implements Serializable {
	
	public static final  String LISTAR_FACTURA = "GestionVenta.listaFactura";

	@Id
	@Column(name="id")
	private Integer idFactura;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha")
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name="Empleado_cedula")
	private Empleado empleado;
	
	@ManyToOne
	@JoinColumn(name="Persona_cedula")
	private Persona persona;

	public GestionVenta() {
		super();
	}

	public GestionVenta(Integer idFactura, Date fecha, Empleado empleado, Persona persona) {
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

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
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
		GestionVenta other = (GestionVenta) obj;
		if (idFactura == null) {
			if (other.idFactura != null)
				return false;
		} else if (!idFactura.equals(other.idFactura))
			return false;
		return true;
	}
	
	

	
	
}
