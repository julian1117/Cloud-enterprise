package co.edu.eam.ingesoft.bi.cloud.web.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades.DWPaginaWIKI;
import co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades.DWventa;
import co.edu.eam.ingesoft.bi.cloud.persistencia.wiki.CambiosWIKI;
import co.edu.eam.ingesoft.bi.cloud.persistencia.wiki.UsuarioWIKI;
import co.edu.eam.ingesoft.bi.negocio.beans.DwWIKI;

@Named(value = "wikiController")
@ViewScoped
public class WikiController implements Serializable{
	
	private List<CambiosWIKI> listaWiki;
	private List<DWPaginaWIKI> listaWikiDW;
	
	public List<CambiosWIKI> getListaWiki() {
		return listaWiki;
	}

	public void setListaWiki(List<CambiosWIKI> listaWiki) {
		this.listaWiki = listaWiki;
	}
	
	public List<DWPaginaWIKI> getListaWikiDW() {
		return listaWikiDW;
	}

	public void setListaWikiDW(List<DWPaginaWIKI> listaWikiDW) {
		this.listaWikiDW = listaWikiDW;
	}

	@EJB
	private DwWIKI dwWIKI;
	
	@PostConstruct
	public void litsWiki() {
		listaWiki = dwWIKI.cargarWiki();
	}
	
	public void tranformarDatosWiki() {
		dwWIKI.transformacionWIKI();
		listaWikiDW = dwWIKI.cargarWikiDW();
		Messages.addFlashGlobalInfo("Transformacion y envio a DW con éxitoso");
		//listaWikiDW.get(0).getUsuarioWiki().
	}

}
