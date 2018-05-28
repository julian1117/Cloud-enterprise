package co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.AreaEmpresa;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Cargo;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Empleado;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Persona;

@Entity
@Table(name="DW_EMPLEADO")
public class DWempleado implements Serializable {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer idEmpleado;
	
	@Column(name="salario")
	private double salario;
	
	@Column(name="fechaIngreso_emp")
	@Temporal(TemporalType.DATE)
	private Date fechaIngresoEmp;
	
	@Column(name="Nombre_empleado")
	private String nombreEmpleado;
	

	public DWempleado() {
		super();
	}
	
	public DWempleado(Integer idEmpleado, double salario, Date fechaIngresoEmp, String nombreEmpleado) {
		super();
		this.idEmpleado = idEmpleado;
		this.salario = salario;
		this.fechaIngresoEmp = fechaIngresoEmp;
		this.nombreEmpleado = nombreEmpleado;
	}

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Date getFechaIngresoEmp() {
		return fechaIngresoEmp;
	}

	public void setFechaIngresoEmp(Date fechaIngresoEmp) {
		this.fechaIngresoEmp = fechaIngresoEmp;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idEmpleado == null) ? 0 : idEmpleado.hashCode());
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
		if (idEmpleado == null) {
			if (other.idEmpleado != null)
				return false;
		} else if (!idEmpleado.equals(other.idEmpleado))
			return false;
		return true;
	}

}
