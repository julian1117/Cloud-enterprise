package co.edu.eam.ingesoft.bi.cloud.web.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Auditoria;
import co.edu.eam.ingesoft.bi.negocio.beans.AuditoriaEJB;

@Named(value = "auditoriaControlador")
@ViewScoped
public class AuditoriaController implements Serializable{
	
	private String nombre;
	
	private List<Auditoria> listAuditoria;
	
	private List<Auditoria> listAuditoriaPor;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Auditoria> getListAuditoria() {
		return listAuditoria;
	}

	public void setListAuditoria(List<Auditoria> listAuditoria) {
		this.listAuditoria = listAuditoria;
	}

	public List<Auditoria> getListAuditoriaPor() {
		return listAuditoriaPor;
	}

	public void setListAuditoriaPor(List<Auditoria> listAuditoriaPor) {
		this.listAuditoriaPor = listAuditoriaPor;
	}	
	
	@EJB
	private AuditoriaEJB auditoriaEJB;
	
	@PostConstruct
	public void inicializar() {
		listAuditoria = auditoriaEJB.listaAudit();
	}
	
	public void listAuditoriaPor() {
		listAuditoriaPor = auditoriaEJB.listaAuditoriaPor(nombre);
	}
	

}
