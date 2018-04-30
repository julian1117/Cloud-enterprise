package co.edu.eam.ingesoft.bi.cloud.web.controladores;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Ciudad;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Departamento;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Genero;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Pais;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Persona;
import co.edu.eam.ingesoft.bi.negocio.beans.AuditoriaEJB;
import co.edu.eam.ingesoft.bi.negocio.beans.General_EJB;
import co.edu.eam.ingesoft.bi.negocio.beans.RegistroNuevosEJB;

@Named(value = "gestionClienteController")
@ViewScoped
public class ClienteControler implements Serializable{

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

	private List<Genero> listGeneros;

	private Genero genero;

	private List<Pais> listPais;

	private String pais;

	private List<Departamento> listDepartamento;

	private String departamento;

	private List<Ciudad> listCiudad;

	private Integer ciudad;
	
	@EJB
	private RegistroNuevosEJB registroNuevosEJB;

	@EJB
	private General_EJB generalEJB;
	
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

	public List<Genero> getListGeneros() {
		return listGeneros;
	}

	public void setListGeneros(List<Genero> listGeneros) {
		this.listGeneros = listGeneros;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public List<Pais> getListPais() {
		return listPais;
	}

	public void setListPais(List<Pais> listPais) {
		this.listPais = listPais;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public List<Departamento> getListDepartamento() {
		return listDepartamento;
	}

	public void setListDepartamento(List<Departamento> listDepartamento) {
		this.listDepartamento = listDepartamento;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public List<Ciudad> getListCiudad() {
		return listCiudad;
	}

	public void setListCiudad(List<Ciudad> listCiudad) {
		this.listCiudad = listCiudad;
	}

	public Integer getCiudad() {
		return ciudad;
	}

	public void setCiudad(Integer ciudad) {
		this.ciudad = ciudad;
	}

	public RegistroNuevosEJB getRegistroNuevosEJB() {
		return registroNuevosEJB;
	}

	public void setRegistroNuevosEJB(RegistroNuevosEJB registroNuevosEJB) {
		this.registroNuevosEJB = registroNuevosEJB;
	}

	public General_EJB getGeneralEJB() {
		return generalEJB;
	}

	public void setGeneralEJB(General_EJB generalEJB) {
		this.generalEJB = generalEJB;
	}

	public AuditoriaEJB getAuditoriaEJB() {
		return auditoriaEJB;
	}

	public void setAuditoriaEJB(AuditoriaEJB auditoriaEJB) {
		this.auditoriaEJB = auditoriaEJB;
	}
	
	public void crearCliente() {
		try {
			Genero buscarGenero = generalEJB.buscarGenero(genero.getId());
			Ciudad buscarCiudad = generalEJB.buscarCiudad(ciudad);

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
			
			if(persona == null) {
				registroNuevosEJB.crearPersona(persona);
				registrarAuditoria("CREAR", "REGISTRO NUEVOS");
				Messages.addFlashGlobalInfo("Registro éxitoso");

				
			}else {

				Messages.addFlashGlobalError("El cliente ya Existe");
			
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void buscarCliente() {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
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
	
}
