package co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades;

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

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.AreaEmpresa;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Cargo;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Persona;

@Entity
@Table(name="DW_EMPLEADO")
public class DWempleado implements Serializable {
	
	@Id
	@ManyToOne
	@JoinColumn(name="persona_Id")
	private DWpersona idPersona;
	
	@Column(name="salario")
	private double salario;
	
	@Column(name="fechaIngreso")
	@Temporal(TemporalType.DATE)
	private Date fechaIngreso;
	
	@ManyToOne
	@JoinColumn(name="AreaEmpresa_id")
	private DWareaEmpresa area;
	
	@ManyToOne
	@JoinColumn(name="Cargo_id")
	private DWcargo cargo;

	public DWempleado() {
		super();
	}

	public DWempleado(DWpersona idPersona, double salario, Date fechaIngreso, DWareaEmpresa area, DWcargo cargo) {
		super();
		this.idPersona = idPersona;
		this.salario = salario;
		this.fechaIngreso = fechaIngreso;
		this.area = area;
		this.cargo = cargo;
	}

	

	public DWpersona getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(DWpersona idPersona) {
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

	public DWareaEmpresa getArea() {
		return area;
	}

	public void setArea(DWareaEmpresa area) {
		this.area = area;
	}

	public DWcargo getCargo() {
		return cargo;
	}

	public void setCargo(DWcargo cargo) {
		this.cargo = cargo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPersona == null) ? 0 : idPersona.hashCode());
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
		DWempleado other = (DWempleado) obj;
		if (idPersona == null) {
			if (other.idPersona != null)
				return false;
		} else if (!idPersona.equals(other.idPersona))
			return false;
		return true;
	}
	
	
	
	

}
