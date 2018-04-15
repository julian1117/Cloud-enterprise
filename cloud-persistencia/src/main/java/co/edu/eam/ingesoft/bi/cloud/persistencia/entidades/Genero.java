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
	private Integer id;
	
	@Column(name="genero")
	private String genero;

	public Genero() {
		super();
	}

	public Genero(Integer id, String genero) {
		super();
		this.id = id;
		this.genero = genero;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		Genero other = (Genero) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  genero;
	}

	
	

}
