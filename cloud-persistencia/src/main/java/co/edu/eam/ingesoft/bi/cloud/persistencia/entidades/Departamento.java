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
@Table(name="Departamento")
@NamedQuery(name=Departamento.LISTA_DEPARTAMENTO,query="select d from Departamento d where d.IdPais.idPais=?1")
public class Departamento implements Serializable{

	public static final  String LISTA_DEPARTAMENTO = "Departamento.listaDepartamento";

	
	@Id
	@Column(name="id")
	private Integer IdDepartamento;
	
	@Column(name="nombre")
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name="Pais_id")
	private Pais IdPais;
	
	
	public Departamento() {
		super();
	}

	public Departamento(Integer idDepartamento, String nombre, Pais idPais) {
		super();
		IdDepartamento = idDepartamento;
		this.nombre = nombre;
		IdPais = idPais;
	}

	public Integer getIdDepartamento() {
		return IdDepartamento;
	}

	public void setIdDepartamento(Integer idDepartamento) {
		IdDepartamento = idDepartamento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Pais getIdPais() {
		return IdPais;
	}

	public void setIdPais(Pais idPais) {
		IdPais = idPais;
	}
	
	
	
	
	
}
