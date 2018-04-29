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
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.AreaEmpresa;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Cargo;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Ciudad;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Empleado;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Genero;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Persona;
import co.edu.eam.ingesoft.bi.negocio.beans.AreaEmpresaEJB;
import co.edu.eam.ingesoft.bi.negocio.beans.AuditoriaEJB;
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
	
	//@Pattern(regexp="[0-9]*",message="El campo numero de  fecha solo puede llevar caracteres numericos")
	private Date fechaNacimiento;
	
	@Pattern(regexp="[0-9]*",message="El campo numero de  identificacion solo puede llevar caracteres numericos")
	@Length(min=4,max=10,message="Cedula - longitud entre 5 y 10")
	private String cedula;
	
	@Pattern(regexp="[0-9]*",message="El campo numero de  telefonosolo puede llevar caracteres numericos")
	@Length(min=4,max=10,message="Cedula - longitud entre 7 y 12")
	private String telefono;
	
	private String direccion;
	
	private List<Empleado> listarEmpleado;
	
	private String idEmpleado;
	
	//private static Persona persona;
	
	private String email;
		
	private List<Cargo> listaCargo;
	
	private String idCargo;
	
	private List<AreaEmpresa> listaAreaEmpresa;
	
	private String idAreaEmpresa;
	
	//@Pattern(regexp="[0-9]*",message="El campo salario solo puede llevar caracteres numericos")
	private double salario;
	
	//@Pattern(regexp="[0-9]*",message="El campo fecha de ingreso solo puede llevar caracteres numericos")
	private Date fechaIngreso;
	
	@EJB
	private RecursosHumanosEJB recursosEJB;
	
	@EJB
	private General_EJB generalEJB;
	
	@EJB
	private CargoEJB cargoEJB;
	
	@EJB
	private AreaEmpresaEJB areaEJB;
	
	@EJB
	private AuditoriaEJB auditoriaEJB;

	
	
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


	public List<AreaEmpresa> getListaAreaEmpresa() {
		return listaAreaEmpresa;
	}


	public void setListaAreaEmpresa(List<AreaEmpresa> listaAreaEmpresa) {
		this.listaAreaEmpresa = listaAreaEmpresa;
	}

	

	public String getIdCargo() {
		return idCargo;
	}


	public void setIdCargo(String idCargo) {
		this.idCargo = idCargo;
	}


	public String getIdAreaEmpresa() {
		return idAreaEmpresa;
	}


	public void setIdAreaEmpresa(String idAreaEmpresa) {
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
	
	
	
	public List<Empleado> getListarEmpleado() {
		return listarEmpleado;
	}


	public void setListarEmpleado(List<Empleado> listarEmpleado) {
		this.listarEmpleado = listarEmpleado;
	}


	public String getIdEmpleado() {
		return idEmpleado;
	}


	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}


	public AuditoriaEJB getAuditoriaEJB() {
		return auditoriaEJB;
	}


	public void setAuditoriaEJB(AuditoriaEJB auditoriaEJB) {
		this.auditoriaEJB = auditoriaEJB;
	}


	@PostConstruct
	public void inicializar() {
		listaCargo = recursosEJB.listarCargos();
		listaAreaEmpresa = recursosEJB.listarAreas();
		listarEmpleado = recursosEJB.listarEmpleado();
	}


	public void crearEmpleado() {
		try {
			Cargo cargo = cargoEJB.buscarCargo(Integer.parseInt(idCargo));
			AreaEmpresa area = areaEJB.buscarArea(Integer.parseInt(idAreaEmpresa));
			//Messages.addFlashGlobalInfo("hola" + area);
			
			Persona persona = recursosEJB.buscarEmpleado(Integer.parseInt(cedula));
			
			if(persona != null) {
				Empleado empleado = new Empleado(salario, fechaIngreso, area, cargo, persona);
				
				recursosEJB.crearEmpleado(empleado);
				Messages.addFlashGlobalInfo("Registro Creado Con Exito!!");
			}else {
				Messages.addFlashGlobalInfo("Verifique que la persona exista!!");
			}
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

		}
	}
	
	public void buscarEmpleado() {
		Empleado emp = recursosEJB.buscarEmp(Integer.parseInt(cedula));
		
		if(emp != null) {
			salario= emp.getSalario();
			fechaIngreso =  emp.getFechaIngreso();
			idAreaEmpresa = emp.getArea().getIdArea().toString();
			idCargo = emp.getCargo().getIdCar().toString();
		}else {
			Messages.addFlashGlobalInfo("El Empleado no se encuentra registardo");

		}
	}
	
	public void editarEmpleado() {
Empleado emp = recursosEJB.buscarEmp(Integer.parseInt(cedula));
		
		if(emp != null) {
			Cargo cargo = cargoEJB.buscarCargo(Integer.parseInt(idCargo));
			
			AreaEmpresa area = areaEJB.buscarArea(Integer.parseInt(idAreaEmpresa));
			//Persona persona = recursosEJB.buscarEmpleado(Integer.parseInt(cedula));
			
			Empleado empleado = new Empleado(salario, fechaIngreso, area, cargo, emp.getIdPersona());
			recursosEJB.editarEmpleado(empleado);
			Messages.addFlashGlobalInfo("Registro editado Con Exito!!");

			
		}
	}
	
	public void eliminarEmpleado() {
		
	}
	
	/**
	 * Metodo para  registrar las auditorias generales
	 * @param accion Crear, Editar, Eliminar o Actualizar
	 * @param nombreReg modulo que se esta trabajando
	 */
	public void registrarAuditoria(String accion, String nombreReg) {
		try {
			String browserDetails = Faces.getRequest().getHeader("User-Agent");
			//----obtengo el usuario que esta en session
			//String us = String.valueOf(sesion.getUse().getPersona().getCedula());
			
			//----Mando usuario null por que aqui no hay session de usuario
			auditoriaEJB.crearAuditoria(accion, nombreReg , browserDetails,"N/A","N/A");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	

}
