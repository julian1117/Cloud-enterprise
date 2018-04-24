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
@Table(name="Usuario")
@NamedQuery(name=Usuario.USUARIO,query="SELECT us FROM Usuario us WHERE us.nombre=?1")
public class Usuario implements Serializable{
	
	public static final String USUARIO = "Usuario.listUs";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_SEQ")
    @SequenceGenerator(sequenceName = "USUARIOS_SEQ", allocationSize = 1, name = "USUARIO_SEQ")
	@Column(name="id")
	private Integer codigo;
	
	@Column(name="nombre",nullable=false,length=30)
	private String nombre;
	
	@Column(name="contrasenia",nullable=false,length=30)
	private String contrasenia;
	
	@Column(name="estado",nullable=false)
	private boolean estado;

	@OneToOne
	@JoinColumn(name="Persona_cedula",nullable=false)
	private Persona persona;

	public Usuario() {
		super();
	}

	public Usuario(Integer codigo, String nombre, String contrasenia, boolean estado, Persona persona) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.estado = estado;
		this.persona = persona;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public static String getUsuario() {
		return USUARIO;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Usuario other = (Usuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	
	
}

