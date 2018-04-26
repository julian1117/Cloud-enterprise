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

	
	@Column(name="salario")
	private double salario;
	
	@Column(name="fechaIngreso")
	@Temporal(TemporalType.DATE)
	private Date fechaIngreso;
	
	@ManyToOne
	@JoinColumn(name="AreaEmpresa_id")
	private AreaEmpresa area;
	
	@ManyToOne
	@JoinColumn(name="AreaEmpresa_id")
	private Cargo cargo;
	
	@Id
	@ManyToOne
	@JoinColumn(name="persona_Id")
	private Persona idPersona;	
	

	public Empleado() {
		super();
	}


	public Empleado(double salario, Date fechaIngreso, AreaEmpresa area, Cargo cargo, Persona idPersona) {
		super();
		this.salario = salario;
		this.fechaIngreso = fechaIngreso;
		this.area = area;
		this.cargo = cargo;
		this.idPersona = idPersona;
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


	public Persona getIdPersona() {
		return idPersona;
	}


	public void setIdPersona(Persona idPersona) {
		this.idPersona = idPersona;
	}
	
	


	
	
}
