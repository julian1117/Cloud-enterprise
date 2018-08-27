package co.edu.eam.ingesoft.bi.cloud.persistencia.wiki;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="recentchanges")
@NamedQueries({ 
	@NamedQuery(name = CambiosWIKI.LISTA_CAMB_WIKI, query = "SELECT C FROM CambiosWIKI C group by C.titulo having c.idPagina > 1")
})
public class CambiosWIKI implements Serializable{
	
	public static final String LISTA_CAMB_WIKI = "CambiosWIKI.LISTACAMBWIKI";
	
	@Id
	@Column(name="rc_id")
	private Integer idPagina;
	
	@Column(name="rc_user")
	private Integer idUser;
	
	@Column(name= "rc_title")
	private String titulo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="rc_timestamp")
	private Date fechaModificacion;
	
	@Column(name="rc_comment")
	private String texto;
	
	@Column(name="rc_ip")
	private String ip;
	
	@Column(name="rc_new")
	private boolean esNueva;
	
	@Column(name="rc_type")
	private Integer tipoPagina;

	public CambiosWIKI() {
		super();
	}

	public CambiosWIKI(Integer idPagina, Integer idUser, String titulo, Date fechaModificacion, String texto, String ip,
			boolean esNueva, Integer tipoPagina) {
		super();
		this.idPagina = idPagina;
		this.idUser = idUser;
		this.titulo = titulo;
		this.fechaModificacion = fechaModificacion;
		this.texto = texto;
		this.ip = ip;
		this.esNueva = esNueva;
		this.tipoPagina = tipoPagina;
	}

	public Integer getIdPagina() {
		return idPagina;
	}



	public void setIdPagina(Integer idPagina) {
		this.idPagina = idPagina;
	}



	public Integer getIdUser() {
		return idUser;
	}



	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
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



	public boolean isEsNueva() {
		return esNueva;
	}



	public void setEsNueva(boolean esNueva) {
		this.esNueva = esNueva;
	}

	
	public Integer getTipoPagina() {
		return tipoPagina;
	}

	public void setTipoPagina(Integer tipoPagina) {
		this.tipoPagina = tipoPagina;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPagina == null) ? 0 : idPagina.hashCode());
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
		CambiosWIKI other = (CambiosWIKI) obj;
		if (idPagina == null) {
			if (other.idPagina != null)
				return false;
		} else if (!idPagina.equals(other.idPagina))
			return false;
		return true;
	}

	
	
	
}
