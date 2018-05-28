package co.edu.eam.ingesoft.bi.cloud.web.controladores;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.Initialized;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.model.UploadedFile;

import co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades.DWauditoria;
import co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades.DWventa;
import co.edu.eam.ingesoft.bi.negocio.beans.DWCSV;

@Named(value = "csvController")
@ViewScoped
public class GenerarcsvController implements Serializable {

	List<DWauditoria> listAdDW;
	
	List<DWventa> listaVentaDW;

	private UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	@EJB
	private DWCSV dwcsv;

	public List<DWauditoria> getListAdDW() {
		return listAdDW;
	}

	public void setListAdDW(List<DWauditoria> listAdDW) {
		this.listAdDW = listAdDW;
	}

	public List<DWventa> getListaVentaDW() {
		return listaVentaDW;
	}

	public void setListaVentaDW(List<DWventa> listaVentaDW) {
		this.listaVentaDW = listaVentaDW;
	}

	/**
	 * Trae todo del dw auditoria
	 */
	public void generalcsvAuditoria() {
		listAdDW = dwcsv.listAuditoriDW();
	}
	
	public void upload() {
        if(file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

	/**
	 * Trae todo del dw venta
	 */
	public void generalcsvVenta() {
		listaVentaDW = dwcsv.listVentaDW();
	}
}
