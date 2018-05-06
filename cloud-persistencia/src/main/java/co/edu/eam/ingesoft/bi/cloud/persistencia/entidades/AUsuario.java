package co.edu.eam.ingesoft.bi.cloud.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="AUsuario")
public class AUsuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUDITORIA_US_SEQ")
	@SequenceGenerator(sequenceName = "AUDIT_US_SEQ", allocationSize = 1, name = "AUDITORIA_US_SEQ")
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@Column(name="fecha",nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	
	@Column(name="ingreso",nullable = false)
	private boolean ingreso;
	
	@Column(name="usuario",nullable = false)
	private String usuario;
	
	@Column(name = "origen",nullable = false)
	private String origen;

	@Column(name = "navegador",nullable = false)
	private String navegador;

	public AUsuario() {
		super();
	}

	public AUsuario(Integer id, Date fecha, boolean ingreso, String usuario, String origen, String navegador) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.ingreso = ingreso;
		this.usuario = usuario;
		this.origen = origen;
		this.navegador = navegador;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public boolean isIngreso() {
		return ingreso;
	}

	public void setIngreso(boolean ingreso) {
		this.ingreso = ingreso;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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

	
	
}
