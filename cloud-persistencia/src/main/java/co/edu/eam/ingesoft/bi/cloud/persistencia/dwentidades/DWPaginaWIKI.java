package co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.edu.eam.ingesoft.bi.cloud.persistencia.wiki.PaginaWIKI;

@Entity
@Table(name="DWPaginaWIKI")
@NamedQueries({ 
	@NamedQuery(name = DWPaginaWIKI.TRAER_WIKI, query = "SELECT W FROM DWPaginaWIKI W")
})
public class DWPaginaWIKI implements Serializable{
	
	public static final String TRAER_WIKI= "DWPaginaWIKI.listdwwIKI";
	
	@Id
	@Column(name="id_pagina")
	private Integer idPaginaWiki;
		
	@Column(name= "titulo")
	private String titulo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_modificacion")
	private Date fechaModificacion;
	
	@Column(name="comentario")
	private String texto;
	
	@Column(name="ip_edicion")
	private String ip;
	
	@Column(name="nueva")
	private String esNueva;
	
	@Column(name="Tipo_pagina")
	private String tipoPagina;
	
	@ManyToOne
	@JoinColumn(name = "cambiosPaginaWiki")
	private DWUsCambiosWIKI cambiosWIKI;
	
	@ManyToOne
	@JoinColumn(name = "PaginaPrinWiki")
	private DWPaginaPrinWIKI paginaPrinWiki;

	public DWPaginaWIKI() {
		super();
	}

	public DWPaginaWIKI(Integer idPaginaWiki, String titulo, Date fechaModificacion, String texto, String ip,
			String esNueva, String tipoPagina, DWUsCambiosWIKI cambiosWIKI, DWPaginaPrinWIKI paginaPrinWiki) {
		super();
		this.idPaginaWiki = idPaginaWiki;
		this.titulo = titulo;
		this.fechaModificacion = fechaModificacion;
		this.texto = texto;
		this.ip = ip;
		this.esNueva = esNueva;
		this.tipoPagina = tipoPagina;
		this.cambiosWIKI = cambiosWIKI;
		this.paginaPrinWiki = paginaPrinWiki;
	}

	public Integer getIdPaginaWiki() {
		return idPaginaWiki;
	}

	public void setIdPaginaWiki(Integer idPaginaWiki) {
		this.idPaginaWiki = idPaginaWiki;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getEsNueva() {
		return esNueva;
	}

	public void setEsNueva(String esNueva) {
		this.esNueva = esNueva;
	}

	public String getTipoPagina() {
		return tipoPagina;
	}

	public void setTipoPagina(String tipoPagina) {
		this.tipoPagina = tipoPagina;
	}

	public DWUsCambiosWIKI getCambiosWIKI() {
		return cambiosWIKI;
	}

	public void setCambiosWIKI(DWUsCambiosWIKI cambiosWIKI) {
		this.cambiosWIKI = cambiosWIKI;
	}

	public DWPaginaPrinWIKI getPaginaPrinWiki() {
		return paginaPrinWiki;
	}

	public void setPaginaPrinWiki(DWPaginaPrinWIKI paginaPrinWiki) {
		this.paginaPrinWiki = paginaPrinWiki;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPaginaWiki == null) ? 0 : idPaginaWiki.hashCode());
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
		DWPaginaWIKI other = (DWPaginaWIKI) obj;
		if (idPaginaWiki == null) {
			if (other.idPaginaWiki != null)
				return false;
		} else if (!idPaginaWiki.equals(other.idPaginaWiki))
			return false;
		return true;
	}
	
}
