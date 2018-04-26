package co.edu.eam.ingesoft.bi.cloud.web.controladores;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Cargo;
import co.edu.eam.ingesoft.bi.negocio.beans.CargoEJB;

@Named("cargoControlador")
@ViewScoped
public class CargoController {

	private String idCargo;

	private String nombre;

	private String descripcion;

	private CargoEJB cargoEJB;

	//private List<Cargo> listaCargo;

//	@PostConstruct
	//public void inicializar() {
		//listaCargo = cargoEJB.listarCargos();
	//}

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
			cargoEJB.crearCargo(cargo);
			Messages.addFlashGlobalInfo("Registro Creado Con Exito!!");

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}

	}

	public void buscarCarga() {
		try {
			Cargo cargo = cargoEJB.buscarCargo(Integer.parseInt(idCargo));
			if (cargo != null) {
				descripcion = cargo.getDescripcion();
				nombre = cargo.getNombre();
			} else {
				Messages.addFlashGlobalInfo("El Cargo no se encuentra registardo");
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

		}
	}

	public void editarCargo() {
		try {
			Cargo car = cargoEJB.buscarCargo(Integer.parseInt(idCargo));

			if (car != null) {
				Cargo cargo = new Cargo(Integer.parseInt(idCargo), nombre, descripcion);

				cargoEJB.editarCargo(cargo);
				Messages.addFlashGlobalInfo("Registro Creado Con Exito!!");
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

}
