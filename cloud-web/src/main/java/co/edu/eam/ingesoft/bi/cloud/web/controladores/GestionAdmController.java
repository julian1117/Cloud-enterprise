package co.edu.eam.ingesoft.bi.cloud.web.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.bi.negocio.beans.General_EJB;
import co.edu.eam.ingesoft.bi.negocio.beans.GestionAdmEJB;

@Named(value = "gestionAdmController")
@ViewScoped
public class GestionAdmController implements Serializable {

	private List<Usuario> listUsuarioInact;

	public List<Usuario> getListUsuarioInact() {
		return listUsuarioInact;
	}

	public void setListUsuarioInact(List<Usuario> listUsuarioInact) {
		this.listUsuarioInact = listUsuarioInact;
	}

	@EJB
	private GestionAdmEJB gestionAdmEJB;

	@PostConstruct
	public void inicializar() {
		listUsuarioInact = gestionAdmEJB.listaUsuarioI();
	}

	/**
	 * Cambio de estado del usuario a activo
	 * @param usuario
	 * @return
	 */
	public String activarUsuario(Usuario usuario) {
		gestionAdmEJB.ActivarUsario(usuario);
		Messages.addFlashGlobalInfo("Cambio de estado con éxitoso");
		return "/paginas/seguro/adm/activarUs.xhtml?faces-redirect=true";
	}

}
