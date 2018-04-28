package co.edu.eam.ingesoft.bi.cloud.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Producto")
@NamedQuery(name=Producto.LISTA_PRODUCTO, query="SELECT p FROM Producto p ")
public class Producto implements Serializable{
	
	public static final  String LISTA_PRODUCTO = "Producto.listaProducto";
	
	@Id
	@Column(name="id")
	private Integer idProducto;
	
	@Column(name="nombre")
	private String nombre;		
	
	@Column(name="descripcion")
	private String descirpcion;	
	
	@Column(name="codigoLote")
	private Integer codigoLote;
	
	@Column(name="peso")
	private String peso;
	
	@Column(name="dimensiones")
	private String dimensiones;
	
	@Column(name="valor")
	private double valor;

	public Producto() {
		super();
	}

	public Producto(Integer id, String nombre, String descirpcion, Integer codigoLote, String peso, String dimensiones,
			double valor) {
		super();
		this.idProducto = id;
		this.nombre = nombre;
		this.descirpcion = descirpcion;
		this.codigoLote = codigoLote;
		this.peso = peso;
		this.dimensiones = dimensiones;
		this.valor = valor;
	}

	

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public static String getListaProducto() {
		return LISTA_PRODUCTO;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescirpcion() {
		return descirpcion;
	}

	public void setDescirpcion(String descirpcion) {
		this.descirpcion = descirpcion;
	}

	public Integer getCodigoLote() {
		return codigoLote;
	}

	public void setCodigoLote(Integer codigoLote) {
		this.codigoLote = codigoLote;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getDimensiones() {
		return dimensiones;
	}

	public void setDimensiones(String dimensiones) {
		this.dimensiones = dimensiones;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProducto == null) ? 0 : idProducto.hashCode());
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
		Producto other = (Producto) obj;
		if (idProducto == null) {
			if (other.idProducto != null)
				return false;
		} else if (!idProducto.equals(other.idProducto))
			return false;
		return true;
	}

	
	
	

	

}
