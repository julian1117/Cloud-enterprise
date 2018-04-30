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
	
	



	

	
	

}
