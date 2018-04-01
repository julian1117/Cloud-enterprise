package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="GestionInventario")
public class GestionInventario implements Serializable {
	
	@Id
	@Column(name="id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "Inventario_id")
	private Inventario inventario;
	
	@ManyToOne
	@JoinColumn(name = "GestionVenta_id")
	private GestionVenta gestionVenta;

	public GestionInventario() {
		super();
	}

	public GestionInventario(Integer id, Inventario inventario, GestionVenta gestionVenta) {
		super();
		this.id = id;
		this.inventario = inventario;
		this.gestionVenta = gestionVenta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	

}
