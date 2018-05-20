package co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades;

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
@Table(name="DW_AUDITORIA")
public class DWauditoria implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idAuditoria;

	@Column(name = "nombre",nullable = false)
	private String nombre;

	@Column(name = "accion",nullable = false)
	private String accion;

	@Column(name = "origen",nullable = false)
	private String origen;

	@Column(name = "navegador",nullable = false)
	private String navegador;

	@Column(name = "fecha",nullable = false)
	private String fecha;

	@Column(name = "usuarioSe",nullable = false)
	private String usuarioSe;
	
	@Column(name = "usuarioAfectado",nullable = false)
	private String usuarioAfectado;
	
	public DWauditoria() {
		super();
	}
	
	public DWauditoria(Integer idAuditoria, String nombre, String accion, String origen, String navegador, String fecha,
			String usuarioSe, String usuarioAfectado) {
		super();
		this.idAuditoria = idAuditoria;
		this.nombre = nombre;
		this.accion = accion;
		this.origen = origen;
		this.navegador = navegador;
		this.fecha = fecha;
		this.usuarioSe = usuarioSe;
		this.usuarioAfectado = usuarioAfectado;
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

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getUsuarioSe() {
		return usuarioSe;
	}

	public void setUsuarioSe(String usuarioSe) {
		this.usuarioSe = usuarioSe;
	}

	public String getUsuarioAfectado() {
		return usuarioAfectado;
	}

	public void setUsuarioAfectado(String usuarioAfectado) {
		this.usuarioAfectado = usuarioAfectado;
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
		DWauditoria other = (DWauditoria) obj;
		if (idAuditoria == null) {
			if (other.idAuditoria != null)
				return false;
		} else if (!idAuditoria.equals(other.idAuditoria))
			return false;
		return true;
	}
	
	

}
