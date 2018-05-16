package co.edu.eam.ingesoft.bi.cloud.web.controladores;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
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
	
	//@Pattern(regexp="[0-9]*",message="El campo numero de  identificacion solo puede llevar caracteres numericos")
	//@Length(min=4,max=10,message="Cedula - longitud entre 5 y 10")
	public static String cedula;
	
	private String cedulaPer = cedula;
	
	@Pattern(regexp="[0-9]*",message="El campo numero de  telefonosolo puede llevar caracteres numericos")
	@Length(min=4,max=10,message="Cedula - longitud entre 7 y 12")
	private String telefono;
	
	private String direccion;
	
	private List<Object> listarEmpleado;
	
	private String idEmpleado;
	
	//private static Persona persona;
	
	private String email;
		
	private List<Object> listaCargo;
	
	private String idCargo;
	
	private List<Object> listaAreaEmpresa;
	
	private String idAreaEmpresa;
	
	//@Pattern(regexp="[0-9]*",message="El campo salario solo puede llevar caracteres numericos")
	private double salario;
	
	//@Pattern(regexp="[0-9]*",message="El campo fecha de ingreso solo puede llevar caracteres numericos")
	private Date fechaIngreso;
	
	
	
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


	public static String getCedula() {
		return cedula;
	}


	public static void setCedula(String cedula) {
		RecursosHumanosController.cedula = cedula;
	}


	public String getCedulaPer() {
		return cedulaPer;
	}


	public void setCedulaPer(String cedulaPer) {
		this.cedulaPer = cedulaPer;
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


	public List<Object> getListarEmpleado() {
		return listarEmpleado;
	}


	public void setListarEmpleado(List<Object> listarEmpleado) {
		this.listarEmpleado = listarEmpleado;
	}


	public String getIdEmpleado() {
		return idEmpleado;
	}


	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public List<Object> getListaCargo() {
		return listaCargo;
	}


	public void setListaCargo(List<Object> listaCargo) {
		this.listaCargo = listaCargo;
	}


	public String getIdCargo() {
		return idCargo;
	}


	public void setIdCargo(String idCargo) {
		this.idCargo = idCargo;
	}


	public List<Object> getListaAreaEmpresa() {
		return listaAreaEmpresa;
	}


	public void setListaAreaEmpresa(List<Object> listaAreaEmpresa) {
		this.listaAreaEmpresa = listaAreaEmpresa;
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


	public SessionController getSesion() {
		return sesion;
	}


	public void setSesion(SessionController sesion) {
		this.sesion = sesion;
	}

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

	@Inject
	private SessionController sesion;	


	@PostConstruct
	public void inicializar() {
		listaCargo = recursosEJB.listarCargos(sesion.getBd());
		listaAreaEmpresa = recursosEJB.listarAreas(sesion.getBd());
		listarEmpleado = recursosEJB.listarEmpleado(sesion.getBd());
	}


	public void crearEmpleado() {
		try {
			Cargo cargo = cargoEJB.buscarCargo(Integer.parseInt(idCargo),sesion.getBd());
			AreaEmpresa area = areaEJB.buscarArea(Integer.parseInt(idAreaEmpresa),sesion.getBd());
			//Messages.addFlashGlobalInfo("hola" + area);
			
			Persona persona = recursosEJB.buscarEmpleado(Integer.parseInt(cedula),sesion.getBd());
			
			if(persona != null) {
				Empleado empleado = new Empleado();
				empleado.setArea(area);
				empleado.setCargo(cargo);
				empleado.setFechaIngreso(fechaIngreso);
				empleado.setIdPersona(persona);
				empleado.setSalario(salario);
				
				recursosEJB.crearEmpleado(empleado,sesion.getBd());
				
				Messages.addFlashGlobalInfo("Registro Creado Con Exito!!");
				
				registrarAuditoria("CREAR", "Creo un nuevo empleado");
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
		Empleado emp = recursosEJB.buscarEmp(Integer.parseInt(cedula),sesion.getBd());
		
		if(emp != null) {
			salario= emp.getSalario();
			fechaIngreso =  emp.getFechaIngreso();
			idAreaEmpresa = emp.getArea().getIdArea().toString();
			idCargo = emp.getCargo().getIdCar().toString();
			
			registrarAuditoria("BUSCAR", "Busco al empleado con cedula: " + emp.getIdPersona().getCedula());
		}else {
			Messages.addFlashGlobalInfo("El Empleado no se encuentra registardo");

		}
	}
	
	public void editarEmpleado() {
Empleado emp = recursosEJB.buscarEmp(Integer.parseInt(cedula),sesion.getBd());
		
		if(emp != null) {
			Cargo cargo = cargoEJB.buscarCargo(Integer.parseInt(idCargo),sesion.getBd());
			
			AreaEmpresa area = areaEJB.buscarArea(Integer.parseInt(idAreaEmpresa),sesion.getBd());
			//Persona persona = recursosEJB.buscarEmpleado(Integer.parseInt(cedula));
			
			Empleado empleado = new Empleado(salario, fechaIngreso, area, cargo, emp.getIdPersona());
			recursosEJB.editarEmpleado(empleado,sesion.getBd());
			Messages.addFlashGlobalInfo("Registro editado Con Exito!!");
			registrarAuditoria("EDITAR", "Edito al empleado con cedula: " + emp.getIdPersona().getCedula());


			
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
			String us = String.valueOf(sesion.getUse().getPersona().getCedula());
			
			//----Mando usuario null por que aqui no hay session de usuario
			auditoriaEJB.crearAuditoria(accion, nombreReg , browserDetails,us,"N/A");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	

}
