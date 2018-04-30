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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TipoUsuario")
@NamedQuery(name = TipoUsuario.TIPO_IS, query = "SELECT T FROM TipoUsuario t")
public class TipoUsuario implements Serializable{

	public static final String TIPO_IS = "Usuario.listTI";

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_TI_SEQ")
	@SequenceGenerator(sequenceName = "TIPO_USUARIO", allocationSize = 1, name = "USUARIO_TI_SEQ")
	@Column(name="id")
	private Integer idTipoUs;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@OneToOne
	@JoinColumn(name="Persona_cedula")
	private Persona persona;

	public TipoUsuario() {
		super();
	}

	public TipoUsuario(Integer idTipoUs, String nombre, String descripcion, Persona persona) {
		super();
		this.idTipoUs = idTipoUs;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.persona = persona;
	}

	public Integer getidTipoUs() {
		return idTipoUs;
	}

	public void setidTipoUs(Integer idTipoUs) {
		this.idTipoUs = idTipoUs;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	
	
}
