package co.edu.eam.ingesoft.bi.cloud.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Venta")
public class Venta implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VENTA_SEQ")
    @SequenceGenerator(sequenceName = "VENTAS_SEQ", allocationSize = 1, name = "VENTA_SEQ")
	private Integer idVenta;
	
	@ManyToOne
	@JoinColumn(name = "Inventario_id")
	private Inventario inventario;
	
	
	@ManyToOne
	@JoinColumn(name = "GestionVenta_id")
	private GestionVenta gestionVenta;
	
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



	public Venta(Integer idVenta, Inventario inventario, GestionVenta gestionVenta, Integer cantidad) {
		super();
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

	
	

	
	

}
