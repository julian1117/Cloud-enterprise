package co.edu.eam.ingesoft.bi.cloud.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Empelado")
public class Empleado implements Serializable{

	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="salario")
	private double salario;
	
	@Column(name="fechaIngreso")
	@Temporal(TemporalType.DATE)
	private Date fechaIngreso;
	
	@ManyToOne
	@JoinColumn(name="Persona_cedula")
	private Persona persona;
	
	@ManyToOne
	@JoinColumn(name="AreaEmpresa_id")
	private AreaEmpresa area;
	
	@ManyToOne
	@JoinColumn(name="AreaEmpresa_id")
	private Cargo cargo;

	public Empleado() {
		super();
	}

	public Empleado(Integer id, double salario, Date fechaIngreso, Persona persona, AreaEmpresa area, Cargo cargo) {
		super();
		this.id = id;
		this.salario = salario;
		this.fechaIngreso = fechaIngreso;
		this.persona = persona;
		this.area = area;
		this.cargo = cargo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public AreaEmpresa getArea() {
		return area;
	}

	public void setArea(AreaEmpresa area) {
		this.area = area;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	
	
}
