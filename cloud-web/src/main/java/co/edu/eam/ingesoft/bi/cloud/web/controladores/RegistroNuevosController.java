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
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Ciudad;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Departamento;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Genero;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Pais;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Persona;
import co.edu.eam.ingesoft.bi.negocio.beans.General_EJB;
import co.edu.eam.ingesoft.bi.negocio.beans.RegistroNuevosEJB;

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

	// @Pattern(regexp = "[0-9]*", message = "El campo numero de identificacion solo
	// puede llevar caracteres numericos")
	// @Length(min = 4, max = 10, message = "Cedula - longitud entre 7 y 10")
	private Integer cedula;

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

	// Declaracion de EJB
	@EJB
	private RegistroNuevosEJB registroNuevosEJB;

	@EJB
	private General_EJB generalEJB;

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

	public Integer getCedula() {
		return cedula;
	}

	public void setCedula(Integer cedula) {
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

	public List<Departamento> getListDepartamento() {
		return listDepartamento;
	}

	public void setListDepartamento(List<Departamento> listDepartamento) {
		this.listDepartamento = listDepartamento;
	}

	public List<Ciudad> getListCiudad() {
		return listCiudad;
	}

	public void setListCiudad(List<Ciudad> listCiudad) {
		this.listCiudad = listCiudad;
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

	@PostConstruct
	public void inicializar() {
		listGeneros = registroNuevosEJB.listaGeneros();
		listPais = generalEJB.listaPaises();
		// listDepartamento = generalEJB.listaDepartamento();
		// listCiudad = generalEJB.listCiudad();

	}

	public void cargarDep() {
		listDepartamento = generalEJB.listaDepartamento(pais);
	}

	public void cargarCiu() {
		listCiudad = generalEJB.listCiudad(departamento);
	}

	/**
	 * @julian camilo henao Metodo para crear un usuario nuevo
	 */
	public void crearUsuarioNuevo() {

		try {
			Genero buscarGenero = generalEJB.buscarGenero(genero.getId());
			Ciudad buscarCiudad = generalEJB.buscarCiudad(ciudad);

			Persona persona = new Persona();
			persona.setNombre(nombre);
			persona.setApellido(apellido);
			persona.setCedula(cedula);
			persona.setCiudad(buscarCiudad);
			persona.setDireccion(direccion);
			persona.setEmail(email);
			persona.setFechaNacimiento(fechaNacimiento);
			persona.setGenero(buscarGenero);
			persona.setTelefono(telefono);

			registroNuevosEJB.crearPersona(persona);

			Messages.addFlashGlobalInfo("Persona creada con exito");

		} catch (Exception e) {
			Messages.addFlashGlobalInfo("La persona no se registro");

		}

	}

}
