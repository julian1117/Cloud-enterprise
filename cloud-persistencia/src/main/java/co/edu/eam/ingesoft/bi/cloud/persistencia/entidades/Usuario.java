package co.edu.eam.ingesoft.bi.cloud.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Usuario")
@NamedQuery(name=Usuario.USUARIO,query="SELECT us FROM Usuario us WHERE us.nombre=?1")
public class Usuario implements Serializable{
	
	public static final String USUARIO = "Usuario.listUs";
	
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="contrasenia")
	private String contrasenia;
	
	@Column(name="estado")
	private boolean estado;

	@OneToOne
	@JoinColumn(name="Persona_cedula")
	private Persona persona;

	public Usuario() {
		super();
	}

	public Usuario(Integer id, String nombre, String contrasenia, boolean estado, Persona persona) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.estado = estado;
		this.persona = persona;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}

