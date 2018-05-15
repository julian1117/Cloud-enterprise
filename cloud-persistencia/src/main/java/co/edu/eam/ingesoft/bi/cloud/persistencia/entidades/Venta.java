package co.edu.eam.ingesoft.bi.cloud.persistencia.entidades;

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
@IdClass(VentaPK.class)
@Table(name="Venta")
@NamedQuery(name=Venta.LISTA_VENT,query="select V from Venta V")
public class Venta implements Serializable {
	
	public static final  String LISTA_VENT = "Venta:listVen";

	@Id
	@ManyToOne
	@JoinColumn(name = "Inventario_id")
	private Inventario inventario;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "GestionVenta_id")
	private GestionVenta gestionVenta;
	
	@Column(name = "CANTIDAD")
	private Integer cantidad;

	public Venta() {
		super();
	}

	public Venta(Inventario inventario, GestionVenta gestionVenta, Integer cantidad) {
		super();
		this.inventario = inventario;
		this.gestionVenta = gestionVenta;
		this.cantidad = cantidad;
	}

	public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

	public GestionVenta getGestionVenta() {
		return gestionVenta;
	}

	public void setGestionVenta(GestionVenta gestionVenta) {
		this.gestionVenta = gestionVenta;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public static String getListaVent() {
		return LISTA_VENT;
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
		Venta other = (Venta) obj;
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
