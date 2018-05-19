package co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades;

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
@Table(name="DWProducto")
@NamedQuery(name=DWProducto.LISTA_PRODUCTO, query="SELECT p FROM Producto p ")
public class DWProducto implements Serializable{
	
	public static final  String LISTA_PRODUCTO = "Producto.listaProductoo";
	
	@Id
	@Column(name="id")
	private Integer idProducto;
	
	@Column(name="nombre")
	private String nombre;		
	
	@Column(name="descripcion")
	private String descirpcion;	
	
	@Column(name="codigoLote")
	private Integer codigoLote;
	
	@Column(name="valor")
	private double valor;

	public DWProducto() {
		super();
	}

	public DWProducto(Integer id, String nombre, String descirpcion, Integer codigoLote, 
			double valor) {
		super();
		this.idProducto = id;
		this.nombre = nombre;
		this.descirpcion = descirpcion;
		this.codigoLote = codigoLote;
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
		DWProducto other = (DWProducto) obj;
		if (idProducto == null) {
			if (other.idProducto != null)
				return false;
		} else if (!idProducto.equals(other.idProducto))
			return false;
		return true;
	}

	
	
	

	

}
