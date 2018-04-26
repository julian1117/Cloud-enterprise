package co.edu.eam.ingesoft.bi.cloud.web.controladores;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.AreaEmpresa;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Cargo;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Ciudad;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Genero;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Persona;
import co.edu.eam.ingesoft.bi.negocio.beans.RecursosHumanosEJB;

@Named("recursosControlador")
@ViewScoped
public class RecursosHumanosController implements Serializable {
	
	@Pattern(regexp="[A-Za-z ]*",message="El campo nombre solo permites caracteres alfabetico")
	@Length(min=3,max=20,message="El campo nombre-longitud entre 3 y 30")
	private String nombre;
	
	@Pattern(regexp="[A-Za-z ]*",message="El campo apellido solo permites caracteres alfabetico")
	@Length(min=3,max=20,message="El campo apellido-longitud entre 3 y 30")
	private String apellido;
	
	private Date fechaNacimiento;
	
	@Pattern(regexp="[0-9]*",message="El campo numero de  identificacion solo puede llevar caracteres numericos")
	@Length(min=4,max=10,message="Cedula - longitud entre 7 y 10")
	private String cedula;
	
	@Pattern(regexp="[0-9]*",message="El campo numero de  telefonosolo puede llevar caracteres numericos")
	@Length(min=4,max=10,message="Cedula - longitud entre 7 y 12")
	private String telefono;
	
	private String direccion;
	
	//private static Persona persona;
	
	private String email;
	
	private List<Genero> listGeneros;
	
	private List<Cargo> listaCargo;
	
	private List<AreaEmpresa> listaAreaEmpresa;
	
	private Genero genero;
	
	private double salario;
	
	private Date fechaIngreso;
	
	@EJB
	private RecursosHumanosEJB recursosEJB;
	
	@PostConstruct
	public void inicializar() {
		listaCargo = recursosEJB.listarCargos();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Genero> getListGeneros() {
		return listGeneros;
	}

	public void setListGeneros(List<Genero> listGeneros) {
		this.listGeneros = listGeneros;
	}

	public List<Cargo> getListaCargo() {
		return listaCargo;
	}

	public void setListaCargo(List<Cargo> listaCargo) {
		this.listaCargo = listaCargo;
	}

	public List<AreaEmpresa> getListaAreaEmpresa() {
		return listaAreaEmpresa;
	}

	public void setListaAreaEmpresa(List<AreaEmpresa> listaAreaEmpresa) {
		this.listaAreaEmpresa = listaAreaEmpresa;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
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

	public RecursosHumanosEJB getRecursosEJB() {
		return recursosEJB;
	}

	public void setRecursosEJB(RecursosHumanosEJB recursosEJB) {
		this.recursosEJB = recursosEJB;
	}
	
	public void crearEmpleado() {
		try {
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void buscarEmpleado() {
		
	}
	
	public void editarEmpleado() {
		
	}
	
	
	

	

}
