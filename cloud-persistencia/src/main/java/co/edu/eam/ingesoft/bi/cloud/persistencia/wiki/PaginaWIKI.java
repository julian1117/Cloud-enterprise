package co.edu.eam.ingesoft.bi.cloud.persistencia.wiki;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="page")
@NamedQueries({ 
	@NamedQuery(name = PaginaWIKI.LISTA_PAG_WIKI, query = "SELECT P FROM PaginaWIKI P")
})
public class PaginaWIKI implements Serializable{

	public static final String LISTA_PAG_WIKI = "PaginaWIKI.LISTAWIKI";

	
	@Id
	@Column(name="page_id")
	private Integer idPagina;
	
	@Column(name="page_title")
	private String titulo;
	
	@Column(name="page_is_new")
	private boolean pagina_nuva;
	
	@Column(name="page_random")
	private double tamanoPagina;
	
	@Column(name="page_is_redirect")
	private boolean redirige;

	public PaginaWIKI() {
		super();
	}

	public PaginaWIKI(Integer idPagina, String titulo, boolean pagina_nuva, double tamanoPagina, boolean redirige) {
		super();
		this.idPagina = idPagina;
		this.titulo = titulo;
		this.pagina_nuva = pagina_nuva;
		this.tamanoPagina = tamanoPagina;
		this.redirige = redirige;
	}

	public Integer getIdPagina() {
		return idPagina;
	}

	public void setIdPagina(Integer idPagina) {
		this.idPagina = idPagina;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public boolean isPagina_nuva() {
		return pagina_nuva;
	}

	public void setPagina_nuva(boolean pagina_nuva) {
		this.pagina_nuva = pagina_nuva;
	}
	
	public double getTamanoPagina() {
		return tamanoPagina;
	}

	public void setTamanoPagina(double tamanoPagina) {
		this.tamanoPagina = tamanoPagina;
	}

	public boolean isRedirige() {
		return redirige;
	}

	public void setRedirige(boolean redirige) {
		this.redirige = redirige;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		PaginaWIKI other = (PaginaWIKI) obj;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
	
}
