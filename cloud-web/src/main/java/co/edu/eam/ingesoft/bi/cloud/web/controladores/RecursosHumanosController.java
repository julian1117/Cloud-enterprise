package co.edu.eam.ingesoft.bi.cloud.web.controladores;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.AreaEmpresa;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Cargo;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Ciudad;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Empleado;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Genero;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Persona;
import co.edu.eam.ingesoft.bi.negocio.beans.AreaEmpresaEJB;
import co.edu.eam.ingesoft.bi.negocio.beans.CargoEJB;
import co.edu.eam.ingesoft.bi.negocio.beans.General_EJB;
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
		
	private List<Cargo> listaCargo;
	
	private Cargo idCargo;
	
	private List<AreaEmpresa> listaAreaEmpresa;
	
	private AreaEmpresa idAreaEmpresa;
	
	private double salario;
	
	private Date fechaIngreso;
	
	@EJB
	private RecursosHumanosEJB recursosEJB;
	
	@EJB
	private General_EJB generalEJB;
	
	@EJB
	private CargoEJB cargoEJB;
	
	@EJB
	private AreaEmpresaEJB areaEJB;
	
	
	
	
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



	public List<Cargo> getListaCargo() {
		return listaCargo;
	}


	public void setListaCargo(List<Cargo> listaCargo) {
		this.listaCargo = listaCargo;
	}


	public Cargo getIdCargo() {
		return idCargo;
	}


	public void setIdCargo(Cargo idCargo) {
		this.idCargo = idCargo;
	}


	public List<AreaEmpresa> getListaAreaEmpresa() {
		return listaAreaEmpresa;
	}


	public void setListaAreaEmpresa(List<AreaEmpresa> listaAreaEmpresa) {
		this.listaAreaEmpresa = listaAreaEmpresa;
	}


	public AreaEmpresa getIdAreaEmpresa() {
		return idAreaEmpresa;
	}


	public void setIdAreaEmpresa(AreaEmpresa idAreaEmpresa) {
		this.idAreaEmpresa = idAreaEmpresa;
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


	public General_EJB getGeneralEJB() {
		return generalEJB;
	}


	public void setGeneralEJB(General_EJB generalEJB) {
		this.generalEJB = generalEJB;
	}


	public CargoEJB getCargoEJB() {
		return cargoEJB;
	}


	public void setCargoEJB(CargoEJB cargoEJB) {
		this.cargoEJB = cargoEJB;
	}


	public AreaEmpresaEJB getAreaEJB() {
		return areaEJB;
	}


	public void setAreaEJB(AreaEmpresaEJB areaEJB) {
		this.areaEJB = areaEJB;
	}
	
	@PostConstruct
	public void inicializar() {
		listaCargo = recursosEJB.listarCargos();
	}


	public void crearEmpleado() {
		try {
			Cargo cargo = cargoEJB.buscarCargo(idCargo.getId());
			AreaEmpresa area = areaEJB.buscarArea(idAreaEmpresa.getId());
			Persona persona = recursosEJB.buscarEmpleado(Integer.parseInt(cedula));
			Empleado empleado = new Empleado(salario, fechaIngreso, area, cargo, persona);
			
			recursosEJB.crearEmpleado(empleado);
			Messages.addFlashGlobalInfo("Registro Creado Con Exito!!");
			
		}catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

		}
	}
	
	public void buscarEmpleado() {
		
	}
	
	public void editarEmpleado() {
		
	}
	
	public void eliminarEmpleado() {
		
	}
	
	
	

	

}
