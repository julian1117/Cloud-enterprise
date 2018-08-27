package co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DWUsCambiosWIKI")
public class DWUsCambiosWIKI implements Serializable{
	
	@Id
	@Column(name="id_user")
	private Integer idUsCamb;
	
	@Column(name="nombre_us")
	private String nombreUs;
	
	@Column(name="nombre_real")
	private String nombreReal;
	
	@Column(name="email")
	private String email;
	
	@Column(name="cantidad_cambios")
	private Integer camntidadCambios;

	public DWUsCambiosWIKI() {
		super();
	}

	public DWUsCambiosWIKI(Integer idUsCamb, String nombreUs, String nombreReal, String email,
			Integer camntidadCambios) {
		super();
		this.idUsCamb = idUsCamb;
		this.nombreUs = nombreUs;
		this.nombreReal = nombreReal;
		this.email = email;
		this.camntidadCambios = camntidadCambios;
	}

	public Integer getIdUsCamb() {
		return idUsCamb;
	}

	public void setIdUsCamb(Integer idUsCamb) {
		this.idUsCamb = idUsCamb;
	}

	public String getNombreUs() {
		return nombreUs;
	}

	public void setNombreUs(String nombreUs) {
		this.nombreUs = nombreUs;
	}

	public String getNombreReal() {
		return nombreReal;
	}

	public void setNombreReal(String nombreReal) {
		this.nombreReal = nombreReal;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getCamntidadCambios() {
		return camntidadCambios;
	}

	public void setCamntidadCambios(Integer camntidadCambios) {
		this.camntidadCambios = camntidadCambios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUsCamb == null) ? 0 : idUsCamb.hashCode());
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
		DWUsCambiosWIKI other = (DWUsCambiosWIKI) obj;
		if (idUsCamb == null) {
			if (other.idUsCamb != null)
				return false;
		} else if (!idUsCamb.equals(other.idUsCamb))
			return false;
		return true;
	}
	
	
	
}
