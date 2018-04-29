package co.edu.eam.ingesoft.bi.cloud.web.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Persona;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.bi.negocio.beans.AuditoriaEJB;
import co.edu.eam.ingesoft.bi.negocio.beans.General_EJB;
import co.edu.eam.ingesoft.bi.negocio.beans.GestionAdmEJB;

@Named(value = "gestionAdmController")
@ViewScoped
public class GestionAdmController implements Serializable {

	private List<Usuario> listUsuarioInact;
	
	private List<Usuario> listaPersona;

	public List<Usuario> getListUsuarioInact() {
		return listUsuarioInact;
	}

	public void setListUsuarioInact(List<Usuario> listUsuarioInact) {
		this.listUsuarioInact = listUsuarioInact;
	}

	public List<Usuario> getListaPersona() {
		return listaPersona;
	}

	public void setListaPersona(List<Usuario> listaPersona) {
		this.listaPersona = listaPersona;
	}



	@EJB
	private GestionAdmEJB gestionAdmEJB;

	@EJB
	private AuditoriaEJB auditoriaEJB;
	
	@Inject
	private RecursosHumanosController recursosH;

	@Inject
	private SessionController sesion;

	@PostConstruct
	public void inicializar() {
		listUsuarioInact = gestionAdmEJB.listaUsuarioI();
		listaPersona = gestionAdmEJB.listaUsuarioI();
	}

	/**
	 * Cambio de estado del usuario a activo
	 * 
	 * @param usuario
	 * @return
	 */
	public String activarUsuario(Usuario usuario) {
		try {
			gestionAdmEJB.ActivarUsario(usuario);
			registrarAuditoria("Activar usuarios", "Administrar Usuarios",
					String.valueOf(usuario.getPersona().getCedula()));
			Messages.addFlashGlobalInfo("Cambio de estado con éxitoso");
			 return "/paginas/seguro/adm/activarUs.xhtml?faces-redirect=true";
		} catch (Exception e) {
			Messages.addFlashGlobalFatal("=( \n " + e);
		}
		 return null;
	}

	/**
	 * Cambio de estado del usuario a inactivo
	 * 
	 * @param usuario
	 * @return
	 */
	public String inactivarUsuario(Usuario usuario) {
		try {
			gestionAdmEJB.inactivarUsario(usuario);
			registrarAuditoria("Inactivar usuarios", "Administrar Usuarios",
					String.valueOf(usuario.getPersona().getCedula()));
			Messages.addFlashGlobalInfo("Cambio de estado con éxitoso");
			return "/paginas/seguro/adm/activarUs.xhtml?faces-redirect=true";
		} catch (Exception e) {
			Messages.addFlashGlobalFatal("=( \n " + e);
		}
		 return null;
	}

	/**
	 * Metodo para registrar las auditorias generales
	 * 
	 * @param accion
	 *            Crear, Editar, Eliminar o Actualizar
	 * @param nombreReg
	 *            modulo que se esta trabajando
	 */
	public void registrarAuditoria(String accion, String nombreReg, String usuarioAF) {
		try {
			String browserDetails = Faces.getRequest().getHeader("User-Agent");
			// ----obtengo el usuario que esta en session
			// String us = String.valueOf(sesion.getUse().getPersona().getCedula());

			// ----Mando usuario null por que aqui no hay session de usuario
			auditoriaEJB.crearAuditoria(accion, nombreReg, browserDetails, "N/A", usuarioAF);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para cargar la cedula de una persona de manera statica
	 * para configurarla con el empleado
	 * @param persona
	 */
	public String cedulaStaticaEmpleado(Usuario persona) {
		recursosH.cedula=String.valueOf(persona.getPersona().getCedula());
		return "/paginas/seguro/adm/empleado.xhtml?faces-redirect=true";

	}
	
	
}
