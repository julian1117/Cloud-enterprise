package co.edu.eam.ingesoft.bi.cloud.web.controladores;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Auditoria;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Ciudad;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Departamento;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Genero;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Pais;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Persona;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.bi.negocio.beans.AuditoriaEJB;
import co.edu.eam.ingesoft.bi.negocio.beans.General_EJB;
import co.edu.eam.ingesoft.bi.negocio.beans.RegistroNuevosEJB;
import co.edu.eam.ingesoft.bi.negocio.excepciones.ExcepcionNegocio;

/*
 * @julian camilo henao
 * Clase que se encarga de recibir todos los datos del formulario nuevos usuarios
 */

@Named(value = "gestionNuevosController")
@ViewScoped
public class RegistroNuevosController implements Serializable {

	// Declaracion de variables
	@Pattern(regexp = "[A-Za-z ]*", message = "El campo nombre solo permites caracteres alfabetico")
	@Length(min = 3, max = 20, message = "El campo nombre-longitud entre 3 y 30")
	private String nombre;

	@Pattern(regexp = "[A-Za-z ]*", message = "El campo apellido solo permites caracteres alfabetico")
	@Length(min = 3, max = 20, message = "El campo apellido-longitud entre 3 y 30")
	private String apellido;

	private Date fechaNacimiento;

	@Pattern(regexp = "[0-9]*", message = "El campo numero de identificacion solo puede llevar caracteres numericos")
	@Length(min = 4, max = 10, message = "Cedula - longitud entre 7 y 10")
	private String cedula;

	@Pattern(regexp = "[0-9]*", message = "El campo numero de  telefonosolo puede llevar caracteres numericos")
	@Length(min = 4, max = 10, message = "Cedula - longitud entre 7 y 12")
	private String telefono;

	private String direccion;

	private String email;

	private List<Object> listGeneros;

	private Genero genero;

	private List<Object> listPais;

	private String pais;

	private List<Object> listDepartamento;

	private String departamento;

	private List<Object> listCiudad;

	private Integer ciudad;

	private String nombreUsuario;

	private String contrasenaA;

	private String contrasenaB;

	// Declaracion de EJB
	@EJB
	private RegistroNuevosEJB registroNuevosEJB;

	@EJB
	private General_EJB generalEJB;
	
	@EJB
	private AuditoriaEJB auditoriaEJB;
	
	@Inject
	private SessionController sesion;

	// Declaracion de get y set

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

	

	public List<Object> getListGeneros() {
		return listGeneros;
	}

	public void setListGeneros(List<Object> listGeneros) {
		this.listGeneros = listGeneros;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	

	public List<Object> getListPais() {
		return listPais;
	}

	public void setListPais(List<Object> listPais) {
		this.listPais = listPais;
	}

	public List<Object> getListDepartamento() {
		return listDepartamento;
	}

	public void setListDepartamento(List<Object> listDepartamento) {
		this.listDepartamento = listDepartamento;
	}

	public List<Object> getListCiudad() {
		return listCiudad;
	}

	public void setListCiudad(List<Object> listCiudad) {
		this.listCiudad = listCiudad;
	}

	public String getContrasenaA() {
		return contrasenaA;
	}

	public void setContrasenaA(String contrasenaA) {
		this.contrasenaA = contrasenaA;
	}

	public String getContrasenaB() {
		return contrasenaB;
	}

	public void setContrasenaB(String contrasenaB) {
		this.contrasenaB = contrasenaB;
	}

	public SessionController getSesion() {
		return sesion;
	}

	public void setSesion(SessionController sesion) {
		this.sesion = sesion;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public Integer getCiudad() {
		return ciudad;
	}

	public void setCiudad(Integer ciudad) {
		this.ciudad = ciudad;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getcontrasenaA() {
		return contrasenaA;
	}

	public void setcontrasenaA(String contrasenaA) {
		this.contrasenaA = contrasenaA;
	}

	public String getcontrasenaB() {
		return contrasenaB;
	}

	public void setcontrasenaB(String contrasenaB) {
		this.contrasenaB = contrasenaB;
	}

	@PostConstruct
	public void inicializar() {
		listGeneros = registroNuevosEJB.listaGeneros(1);
		listPais = generalEJB.listaPaises(1);
	}

	public void cargarDep() {
		listDepartamento = generalEJB.listaDepartamento(pais,1);
	}

	public void cargarCiu() {
		listCiudad = generalEJB.listCiudad(departamento,1);
	}

	/**
	 * @julian camilo henao Metodo para crear un usuario nuevo
	 */
	public void crearUsuarioNuevo() {

		try {
			Genero buscarGenero = generalEJB.buscarGenero(genero.getId(),1);
			Ciudad buscarCiudad = generalEJB.buscarCiudad(ciudad,1);

			Persona persona = new Persona();
			persona.setNombre(nombre);
			persona.setApellido(apellido);
			persona.setCedula(Integer.parseInt(cedula));
			persona.setCiudad(buscarCiudad);
			persona.setDireccion(direccion);
			persona.setEmail(email);
			persona.setFechaNacimiento(fechaNacimiento);
			persona.setGenero(buscarGenero);
			persona.setTelefono(telefono);

			// valido que el usuario no este registrado antes
			if (registroNuevosEJB.buscarUsuarios(nombreUsuario,1)) {
				// valido que las contraseñas ingresadas sean las mismas
				if (contrasenaA.equals(contrasenaB)) {
					
					// creo la persona
					registroNuevosEJB.crearPersona(persona,1);
					
					Persona per = registroNuevosEJB.buscarPersona(Integer.parseInt(cedula),1);
					
					Usuario usuario = new Usuario();
					usuario.setNombre(nombreUsuario);
					usuario.setContrasenia(contrasenaA);
					usuario.setEstado(false);
					usuario.setPersona(per);
					
					
					// creo el usuario
					registroNuevosEJB.crearUsuario(usuario,1);
					registrarAuditoria("CREAR", "REGISTRO NUEVOS");
					Messages.addFlashGlobalInfo("Registro éxitoso");

				} else {
					Messages.addFlashGlobalError("Las contraseñas no coinciden");
				}

			} else {
				Messages.addFlashGlobalError("El nombre de usuario ingresado ya existe: " + nombreUsuario);
			}

			

		} catch (Exception e) {
			Messages.addFlashGlobalFatal("=( \nLosentimos el registro no fue posible. intentalo de nuevo");

		}

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

	public String registrar() {
		return "/paginas/publico/login.xhtml?faces-redirect=true";
	}
	
	
}
