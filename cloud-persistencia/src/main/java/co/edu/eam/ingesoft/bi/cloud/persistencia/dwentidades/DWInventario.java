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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="DWInventario")
public class DWInventario implements Serializable{	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer idInventario;
	
	@Column(name="cantidad")
	private Integer cantidad;
	
	@Column(name="fecha_ingreso_inv")
	@Temporal(TemporalType.DATE)
	private Date fechaIngresoInv;
	
	@Column(name="nombre_producto")
	private String nombreProducto;
	
	@Column(name="descripcion_producto")
	private String descripcionProducto;
	
	@Column(name="valor_producto")
	private String valorProducto;
	
	public DWInventario() {
		super();
	}

	public DWInventario(Integer idInventario, Integer cantidad, Date fechaIngresoInv, String nombreProducto,
			String descripcionProducto, String valorProducto) {
		super();
		this.idInventario = idInventario;
		this.cantidad = cantidad;
		this.fechaIngresoInv = fechaIngresoInv;
		this.nombreProducto = nombreProducto;
		this.descripcionProducto = descripcionProducto;
		this.valorProducto = valorProducto;
	}

	public Integer getIdInventario() {
		return idInventario;
	}

	public void setIdInventario(Integer idInventario) {
		this.idInventario = idInventario;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaIngresoInv() {
		return fechaIngresoInv;
	}

	public void setFechaIngresoInv(Date fechaIngresoInv) {
		this.fechaIngresoInv = fechaIngresoInv;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public String getValorProducto() {
		return valorProducto;
	}

	public void setValorProducto(String valorProducto) {
		this.valorProducto = valorProducto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idInventario == null) ? 0 : idInventario.hashCode());
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
		DWInventario other = (DWInventario) obj;
		if (idInventario == null) {
			if (other.idInventario != null)
				return false;
		} else if (!idInventario.equals(other.idInventario))
			return false;
		return true;
	}

}
