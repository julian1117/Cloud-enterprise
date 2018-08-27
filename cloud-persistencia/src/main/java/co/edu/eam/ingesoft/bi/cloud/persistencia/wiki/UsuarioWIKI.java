package co.edu.eam.ingesoft.bi.cloud.persistencia.wiki;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="user")
@NamedQueries({ 
	@NamedQuery(name = UsuarioWIKI.LISTA_WIKI, query = "SELECT U FROM UsuarioWIKI U")
})
public class UsuarioWIKI implements Serializable{
	
	public static final String LISTA_WIKI = "UsuarioWIKI.LISTAWIKI";
	
	@Id
	@Column(name="user_id")
	private Integer CodigoUser;
	
	@Column(name="user_name")
	private String nombre;
	
	@Column(name="user_real_name")
	private String nombreReal;
	
	@Column(name="user_email")
	private String emailUs;
	
	@Column(name="user_editcount")
	private Integer CantidadCambios;

	public UsuarioWIKI() {
		super();
		
	}	

	public UsuarioWIKI(Integer codigoUser, String nombre, String nombreReal, String emailUs, Integer cantidadCambios) {
		super();
		CodigoUser = codigoUser;
		this.nombre = nombre;
		this.nombreReal = nombreReal;
		this.emailUs = emailUs;
		CantidadCambios = cantidadCambios;
	}

	public Integer getCodigoUser() {
		return CodigoUser;
	}



	public void setCodigoUser(Integer codigoUser) {
		CodigoUser = codigoUser;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getNombreReal() {
		return nombreReal;
	}



	public void setNombreReal(String nombreReal) {
		this.nombreReal = nombreReal;
	}



	public String getEmailUs() {
		return emailUs;
	}



	public void setEmailUs(String emailUs) {
		this.emailUs = emailUs;
	}



	public Integer getCantidadCambios() {
		return CantidadCambios;
	}

	public void setCantidadCambios(Integer cantidadCambios) {
		CantidadCambios = cantidadCambios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		UsuarioWIKI other = (UsuarioWIKI) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	

}
