package co.edu.eam.ingesoft.bi.cloud.web.controladores;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.AreaEmpresa;
import co.edu.eam.ingesoft.bi.negocio.beans.AreaEmpresaEJB;

@Named(value = "areaControlador")
@ViewScoped
public class AreaEmpresaController implements Serializable{
	
	private String id;

	private String nombre;

	private String descripcion;
	
	@EJB
	private AreaEmpresaEJB areaEJB;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public AreaEmpresaEJB getAreaEJB() {
		return areaEJB;
	}

	public void setAreaEJB(AreaEmpresaEJB areaEJB) {
		this.areaEJB = areaEJB;
	}
	
	
	public void crearArea() {
		try {
			AreaEmpresa areaEmpresa = new AreaEmpresa(Integer.parseInt(id), nombre, descripcion);
			areaEJB.crearArea(areaEmpresa);
			Messages.addFlashGlobalInfo("Registro Creado Con Exito!!");

		}catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
	
		}
	}
	
	public void buscarAreaEmpresa() {
		try {
			AreaEmpresa areaEmpresa = areaEJB.buscarArea(Integer.parseInt(id));
			
			if(areaEmpresa != null) {
				descripcion = areaEmpresa.getDescripcion();
				nombre = areaEmpresa.getNombreArea();
			}else {
				Messages.addFlashGlobalInfo("La empresa no se encuentra registardo");
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		
		}
	}
	
	public void editarAreaEmpresa() {
		try {
			AreaEmpresa areaEmpresa = areaEJB.buscarArea(Integer.parseInt(id));
			if(areaEmpresa != null) {
				AreaEmpresa area = new AreaEmpresa(Integer.parseInt(id), nombre, descripcion);
				Messages.addFlashGlobalInfo("Registro Creado Con Exito!!");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		
		}
	}
	
	public void eliminarAreaEmpresa() {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	

}
