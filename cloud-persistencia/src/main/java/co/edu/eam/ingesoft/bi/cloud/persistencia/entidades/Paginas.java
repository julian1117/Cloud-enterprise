package co.edu.eam.ingesoft.bi.cloud.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "paginas")
public class Paginas implements Serializable {


	@Id
	@Column(name = "id")
	private Integer idPagina;

	@Column(name = "links")
	private String lin;

	@Column(name = "nombre")
	private String nombre;

	public Paginas() {
		super();
	}

	public Paginas(Integer idPagina, String lin, String nombre) {
		super();
		this.idPagina = idPagina;
		this.lin = lin;
		this.nombre = nombre;
	}

	public Integer getIdPagina() {
		return idPagina;
	}

	public void setIdPagina(Integer idPagina) {
		this.idPagina = idPagina;
	}

	public String getLin() {
		return lin;
	}

	public void setLin(String lin) {
		this.lin = lin;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
		Paginas other = (Paginas) obj;
		if (idPagina == null) {
			if (other.idPagina != null)
				return false;
		} else if (!idPagina.equals(other.idPagina))
			return false;
		return true;
	}

	
}
