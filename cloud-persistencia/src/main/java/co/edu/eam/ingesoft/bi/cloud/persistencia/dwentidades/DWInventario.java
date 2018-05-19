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
@NamedQueries({
	@NamedQuery(name=DWInventario.LISTA_InventarioS,query="SELECT m FROM Inventario m"),
	//@NamedQuery(name=Inventario.LISTA_PRODUCTO_INVENTARIO,query="SELECT p.nombre FROM Inventario m INNER JOIN Producto p")
})
public class DWInventario implements Serializable{
	
	public static final String LISTA_InventarioS = "Inventario.listaInventario";
	public static final String LISTA_PRODUCTO_INVENTARIO ="Inventario.listaInv";
	
	@Id@Column(name="id")
	private Integer idInventario;
	
	@Column(name="cantidad")
	private Integer cantidad;
	
	@Column(name="fechaIngreso")
	@Temporal(TemporalType.DATE)
	private Date fechaIngreso;
	
	@ManyToOne
	@JoinColumn(name="producto")
	private DWProducto producto;
	
	
	@ManyToOne
	@JoinColumn(name="persona_Id")
	private DWempleado idPersona;	

	public DWInventario() {
		super();
	}

	public DWInventario(Integer idInventario, Integer cantidad, Date fechaIngreso, DWProducto producto,
			DWempleado idPersona) {
		super();
		this.idInventario = idInventario;
		this.cantidad = cantidad;
		this.fechaIngreso = fechaIngreso;
		this.producto = producto;
		this.idPersona = idPersona;
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

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public DWProducto getProducto() {
		return producto;
	}

	public void setProducto(DWProducto producto) {
		this.producto = producto;
	}

	public DWempleado getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(DWempleado idPersona) {
		this.idPersona = idPersona;
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
