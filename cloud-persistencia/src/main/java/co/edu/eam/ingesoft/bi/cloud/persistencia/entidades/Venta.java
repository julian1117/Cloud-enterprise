package co.edu.eam.ingesoft.bi.cloud.persistencia.entidades;

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

@Entity
@Table(name="Venta")
@NamedQuery(name=Venta.LISTA_VENT,query="select MAX(V.idVenta) from Venta V")
public class Venta implements Serializable {
	
	public static final  String LISTA_VENT = "Venta:listVen";

	
	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VEN_SEQID")
	//@SequenceGenerator(sequenceName = "VENT_SEQ", allocationSize = 1, name = "VEN_SEQID")
	@Column(name="IDVENTA")
	private Integer idVenta;
	
	@ManyToOne
	@JoinColumn(name = "Inventario_id")
	private Inventario inventario;
	
	
	@ManyToOne
	@JoinColumn(name = "GestionVenta_id")
	private GestionVenta gestionVenta;
	
	@Column(name = "CANTIDAD")
	private Integer cantidad;

	public Venta() {
		super();
	}
	

	public Venta(Inventario inventario, GestionVenta gestionVenta, Integer cantidad) {
	
		this.inventario = inventario;
		this.gestionVenta = gestionVenta;
		this.cantidad = cantidad;
	}



	public Venta(Integer idVenta, Inventario inventario, GestionVenta gestionVenta, Integer cantidad) {
		
		this.idVenta = idVenta;
		this.inventario = inventario;
		this.gestionVenta = gestionVenta;
		this.cantidad = cantidad;
	}

	public Integer getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idVenta == null) ? 0 : idVenta.hashCode());
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
		if (idVenta == null) {
			if (other.idVenta != null)
				return false;
		} else if (!idVenta.equals(other.idVenta))
			return false;
		return true;
	}

	
	

	
	

}
