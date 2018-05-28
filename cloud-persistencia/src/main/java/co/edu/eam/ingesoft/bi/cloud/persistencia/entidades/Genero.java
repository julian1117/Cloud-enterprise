package co.edu.eam.ingesoft.bi.cloud.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="Genero")
@NamedQuery(name=Genero.LISTA_GENEROS,query="select g from Genero g")
public class Genero implements Serializable {
	
	public static final  String LISTA_GENEROS = "Genero.listaGeneros";
	
	@Id
	@Column(name="id")
	private Integer idGenero;
	
	@Column(name="genero")
	private String genero;

	public Genero() {
		super();
	}

	public Genero(Integer idGenero, String genero) {
		super();
		this.idGenero = idGenero;
		this.genero = genero;
	}

	public Integer getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(Integer idGenero) {
		this.idGenero = idGenero;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idGenero == null) ? 0 : idGenero.hashCode());
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
		Genero other = (Genero) obj;
		if (idGenero == null) {
			if (other.idGenero != null)
				return false;
		} else if (!idGenero.equals(other.idGenero))
			return false;
		return true;
	}
	

}
