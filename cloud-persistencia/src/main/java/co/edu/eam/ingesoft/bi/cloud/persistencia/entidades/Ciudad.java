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
@Table(name="Ciudad")
@NamedQuery(name=Ciudad.LISTA_CIUDAD,query="select c from Ciudad c where c.departamento.nombre=?1")
public class Ciudad implements Serializable{
	
	public static final  String LISTA_CIUDAD = "Ciudad.listaCiudad";
	
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="nombre")
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name="Departamento_id")
	private Departamento departamento;
	
	
	public Ciudad() {
		super();
	}

	public Ciudad(Integer id, String nombre, Departamento departamento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.departamento = departamento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	
	

}
