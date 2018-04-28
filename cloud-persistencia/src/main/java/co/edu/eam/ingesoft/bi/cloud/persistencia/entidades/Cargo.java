package co.edu.eam.ingesoft.bi.cloud.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Cargo")
@NamedQuery(name=Cargo.LISTA_CARGOS, query="SELECT c FROM Cargo c ")
public class Cargo implements Serializable{
	
	public static final  String LISTA_CARGOS = "Cargo.listaCargos";
	
	
	@Id
	@Column(name="id")
	private Integer idCar;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="descripcion")
	private String Descripcion;

	public Cargo() {
		super();
	}

	
	public Cargo(Integer idCar, String nombre, String descripcion) {
		super();
		this.idCar = idCar;
		this.nombre = nombre;
		Descripcion = descripcion;
	}


	public Integer getIdCar() {
		return idCar;
	}


	public void setIdCar(Integer idCar) {
		this.idCar = idCar;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return Descripcion;
	}


	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}


	@Override
	public String toString() {
		return  nombre;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCar == null) ? 0 : idCar.hashCode());
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
		Cargo other = (Cargo) obj;
		if (idCar == null) {
			if (other.idCar != null)
				return false;
		} else if (!idCar.equals(other.idCar))
			return false;
		return true;
	}
	
	
	
	

}
