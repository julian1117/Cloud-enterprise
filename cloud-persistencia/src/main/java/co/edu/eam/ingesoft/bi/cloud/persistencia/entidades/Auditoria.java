package co.edu.eam.ingesoft.bi.cloud.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Auditoria")
public class Auditoria implements Serializable {

	@Id
	@Column(name = "id")
	private Integer idAuditoria;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "accion")
	private String accion;

	@Column(name = "origen")
	private String origen;

	@Column(name = "navegador")
	private String navegador;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha")
	private Date fecha;

	public Auditoria() {
		super();
	}

	public Auditoria(Integer idAuditoria, String nombre, String accion, String origen, String navegador, Date fecha) {
		super();
		this.idAuditoria = idAuditoria;
		this.nombre = nombre;
		this.accion = accion;
		this.origen = origen;
		this.navegador = navegador;
		this.fecha = fecha;
	}

	public Integer getIdAuditoria() {
		return idAuditoria;
	}

	public void setIdAuditoria(Integer idAuditoria) {
		this.idAuditoria = idAuditoria;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAuditoria == null) ? 0 : idAuditoria.hashCode());
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
		Auditoria other = (Auditoria) obj;
		if (idAuditoria == null) {
			if (other.idAuditoria != null)
				return false;
		} else if (!idAuditoria.equals(other.idAuditoria))
			return false;
		return true;
	}

}
