package entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Auditoria")
public class Auditoria implements Serializable{

	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="accion")
	private String accion;
	
	@Column(name="origen")
	private String origen;
	
	@Column(name="navegador")
	private String navegador;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha")
	private Date fecha;

	public Auditoria() {
		super();
	}

	public Auditoria(Integer id, String nombre, String accion, String origen, String navegador, Date fecha) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.accion = accion;
		this.origen = origen;
		this.navegador = navegador;
		this.fecha = fecha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getNavegador() {
		return navegador;
	}

	public void setNavegador(String navegador) {
		this.navegador = navegador;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	
	
}
