package co.edu.eam.ingesoft.bi.cloud.web.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Cargo;
import co.edu.eam.ingesoft.bi.negocio.beans.AuditoriaEJB;
import co.edu.eam.ingesoft.bi.negocio.beans.CargoEJB;

@Named("cargoControlador")
@ViewScoped
public class CargoController implements Serializable{

	private String idCargo;

	private String nombre;

	private String descripcion;

	@EJB
	private AuditoriaEJB auditoriaEJB;

	
	@EJB
	private CargoEJB cargoEJB;


	@Inject
	private SessionController sesion;	




	public String getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(String idCargo) {
		this.idCargo = idCargo;
	}

	public CargoEJB getCargoEJB() {
		return cargoEJB;
	}

	public void setCargoEJB(CargoEJB cargoEJB) {
		this.cargoEJB = cargoEJB;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void crearCargo() {
		try {
			Cargo cargo = new Cargo(Integer.parseInt(idCargo), nombre, descripcion);
			cargoEJB.crearCargo(cargo,sesion.getBd());
			Messages.addFlashGlobalInfo("Registro Creado Con Exito!!");
			
			registrarAuditoria("CREAR", "Crea cargo nuevo");

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}

	}

	public void buscarCarga() {
		try {
			Cargo cargo = cargoEJB.buscarCargo(Integer.parseInt(idCargo),sesion.getBd());
			if (cargo != null) {
				descripcion = cargo.getDescripcion();
				nombre = cargo.getNombre();
			} else {
				Messages.addFlashGlobalInfo("El Cargo no se encuentra registardo");
				registrarAuditoria("BUSCAR", "Busco el cargo: " + cargo.getNombre());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

		}
	}

	public void editarCargo() {
		try {
			Cargo car = cargoEJB.buscarCargo(Integer.parseInt(idCargo),sesion.getBd());

			if (car != null) {
				Cargo cargo = new Cargo(Integer.parseInt(idCargo), nombre, descripcion);

				cargoEJB.editarCargo(cargo,sesion.getBd());
				Messages.addFlashGlobalInfo("Registro Creado Con Exito!!");
				registrarAuditoria("EDITO", "Edito el cargo: " + cargo.getNombre());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}

	}
	
	public void eliminarCrgo() {
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
			String us = String.valueOf(sesion.getUse().getPersona().getCedula());
			
			//----Mando usuario null por que aqui no hay session de usuario
			auditoriaEJB.crearAuditoria(accion, nombreReg , browserDetails,us,"N/A");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
