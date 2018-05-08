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

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Acceso;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.AreaEmpresa;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Paginas;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Persona;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.TipoUsuario;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.bi.negocio.beans.AuditoriaEJB;
import co.edu.eam.ingesoft.bi.negocio.beans.General_EJB;
import co.edu.eam.ingesoft.bi.negocio.beans.GestionAdmEJB;
import co.edu.eam.ingesoft.bi.negocio.beans.RecursosHumanosEJB;
import co.edu.eam.ingesoft.bi.negocio.beans.SeguridadEJB;

@Named(value = "gestionAdmController")
@ViewScoped
public class GestionAdmController implements Serializable {

	private List<Usuario> listUsuarioInact;

	private List<Usuario> listaPersona;

	private List<Usuario> listUsuariosActivos;

	private List<Paginas> listPaginas;
	
	private List<Acceso> listaAc;

	private Integer codigoUsuario;

	private Integer codigoPagina;

	private String nombreTipoUs;

	private String descripTipoUs;

	private String nombreAreaEmp;

	private String descripAreaEmp;

	private String codigoTipoUs;

	private String codigoAreaEmp;

	private String nombreBTipoUs;

	private String descripBTipoUs;

	private String nombreBAreaEmp;

	private String descripBAreaEmp;

	private List<TipoUsuario> listTipoUs;

	private List<AreaEmpresa> listArea;

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

	public String getNombreTipoUs() {
		return nombreTipoUs;
	}

	public void setNombreTipoUs(String nombreTipoUs) {
		this.nombreTipoUs = nombreTipoUs;
	}

	public String getDescripTipoUs() {
		return descripTipoUs;
	}

	public void setDescripTipoUs(String descripTipoUs) {
		this.descripTipoUs = descripTipoUs;
	}

	public String getNombreAreaEmp() {
		return nombreAreaEmp;
	}

	public void setNombreAreaEmp(String nombreAreaEmp) {
		this.nombreAreaEmp = nombreAreaEmp;
	}

	public String getDescripAreaEmp() {
		return descripAreaEmp;
	}

	public void setDescripAreaEmp(String descripAreaEmp) {
		this.descripAreaEmp = descripAreaEmp;
	}

	public String getCodigoTipoUs() {
		return codigoTipoUs;
	}

	public void setCodigoTipoUs(String codigoTipoUs) {
		this.codigoTipoUs = codigoTipoUs;
	}

	public String getCodigoAreaEmp() {
		return codigoAreaEmp;
	}

	public void setCodigoAreaEmp(String codigoAreaEmp) {
		this.codigoAreaEmp = codigoAreaEmp;
	}

	public List<TipoUsuario> getListTipoUs() {
		return listTipoUs;
	}

	public void setListTipoUs(List<TipoUsuario> listTipoUs) {
		this.listTipoUs = listTipoUs;
	}

	public String getNombreBTipoUs() {
		return nombreBTipoUs;
	}

	public void setNombreBTipoUs(String nombreBTipoUs) {
		this.nombreBTipoUs = nombreBTipoUs;
	}

	public String getDescripBTipoUs() {
		return descripBTipoUs;
	}

	public void setDescripBTipoUs(String descripBTipoUs) {
		this.descripBTipoUs = descripBTipoUs;
	}

	public String getNombreBAreaEmp() {
		return nombreBAreaEmp;
	}

	public void setNombreBAreaEmp(String nombreBAreaEmp) {
		this.nombreBAreaEmp = nombreBAreaEmp;
	}

	public String getDescripBAreaEmp() {
		return descripBAreaEmp;
	}

	public void setDescripBAreaEmp(String descripBAreaEmp) {
		this.descripBAreaEmp = descripBAreaEmp;
	}

	public List<AreaEmpresa> getListArea() {
		return listArea;
	}

	public void setListArea(List<AreaEmpresa> listArea) {
		this.listArea = listArea;
	}

	public List<Usuario> getListUsuariosActivos() {
		return listUsuariosActivos;
	}

	public void setListUsuariosActivos(List<Usuario> listUsuariosActivos) {
		this.listUsuariosActivos = listUsuariosActivos;
	}

	public List<Paginas> getListPaginas() {
		return listPaginas;
	}

	public void setListPaginas(List<Paginas> listPaginas) {
		this.listPaginas = listPaginas;
	}

	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public Integer getCodigoPagina() {
		return codigoPagina;
	}

	public void setCodigoPagina(Integer codigoPagina) {
		this.codigoPagina = codigoPagina;
	}

	public List<Acceso> getListaAc() {
		return listaAc;
	}

	public void setListaAc(List<Acceso> listaAc) {
		this.listaAc = listaAc;
	}


	@EJB
	private GestionAdmEJB gestionAdmEJB;

	@EJB
	private AuditoriaEJB auditoriaEJB;

	@EJB
	private RecursosHumanosEJB recursosEJB;

	@Inject
	private RecursosHumanosController recursosH;

	@Inject
	private SessionController sesion;

	@PostConstruct
	public void inicializar() {
		listUsuarioInact = gestionAdmEJB.listaUsuarioI();
		listaPersona = gestionAdmEJB.listaUsuarioI();
		listTipoUs = gestionAdmEJB.listaTipoUs();
		listArea = recursosEJB.listarAreas();
		listUsuariosActivos = gestionAdmEJB.listaUsuariosAct();
		listPaginas = gestionAdmEJB.listaPaginas();
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
			String us = String.valueOf(sesion.getUse().getPersona().getCedula());

			// ----Mando usuario null por que aqui no hay session de usuario
			auditoriaEJB.crearAuditoria(accion, nombreReg, browserDetails, us, usuarioAF);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para cargar la cedula de una persona de manera statica para
	 * configurarla con el empleado
	 * 
	 * @param persona
	 */
	public String cedulaStaticaEmpleado(Usuario persona) {
		recursosH.cedula = String.valueOf(persona.getPersona().getCedula());
		return "/paginas/seguro/adm/empleado.xhtml?faces-redirect=true";

	}

	/**
	 * Crear tipos de usuario
	 */
	public void crearTipoUs() {
		try {
			TipoUsuario tipoUs = new TipoUsuario();
			tipoUs.setNombre(nombreTipoUs);
			tipoUs.setDescripcion(descripTipoUs);

			gestionAdmEJB.crearTipoUs(tipoUs);
			registrarAuditoria("Crear", "Tipos de usuario", "N/A");
			Messages.addFlashGlobalInfo("Registro Creado Con Exito!!");

		} catch (Exception e) {
			Messages.addFlashGlobalError(e.getMessage());

		}
	}

	/**
	 * Crear areas de la empresa
	 */
	public void crearAreaEmpresa() {
		try {
			AreaEmpresa areaEmp = new AreaEmpresa();
			areaEmp.setNombreArea(nombreAreaEmp);
			areaEmp.setDescripcion(descripAreaEmp);

			gestionAdmEJB.crearAreaEmpresa(areaEmp);
			registrarAuditoria("Crear", "Areas de la empresa", "N/A");

			Messages.addFlashGlobalInfo("Registro Creado Con Exito!!");

		} catch (Exception e) {
			Messages.addFlashGlobalError(e.getMessage());

		}
	}

	/**
	 * Buscar tipo de usuario
	 */
	public void buscarPorTipoUs() {

		TipoUsuario tipoUs = gestionAdmEJB.buscarTipoUs(Integer.valueOf(codigoTipoUs));
		nombreBTipoUs = tipoUs.getNombre();
		descripBTipoUs = tipoUs.getDescripcion();
		registrarAuditoria("Buscar", "Tipos de usuario", "N/A");
	}

	/**
	 * Buscar areas de la empresa
	 */
	public void buscarAreaEmpresa() {

		AreaEmpresa area = gestionAdmEJB.buscarAreaEmpresa(Integer.parseInt(codigoAreaEmp));
		nombreBAreaEmp = area.getNombreArea();
		descripBAreaEmp = area.getDescripcion();
		registrarAuditoria("Buscar", "Areas de la empresa", "N/A");
	}

	/**
	 * Editar tipo de usuario
	 */
	public void editarTipoUs() {
		TipoUsuario tipoUs = gestionAdmEJB.buscarTipoUs(Integer.valueOf(codigoTipoUs));
		tipoUs.setNombre(nombreBTipoUs);
		tipoUs.setDescripcion(descripBTipoUs);
		gestionAdmEJB.editarTipoUs(tipoUs);
		registrarAuditoria("Editar", "Tipos de usuario", "N/A");
		Messages.addFlashGlobalInfo("Registro Editado Con Exito!!");

	}

	/**
	 * Editar area de la empresa
	 */
	public void editarAreaEmpresa() {

		AreaEmpresa area = gestionAdmEJB.buscarAreaEmpresa(Integer.parseInt(codigoAreaEmp));
		area.setNombreArea(nombreBAreaEmp);
		area.setDescripcion(descripBAreaEmp);
		gestionAdmEJB.editarAreaEmpresa(area);
		registrarAuditoria("Editar", "Areas de la empresa", "N/A");
		Messages.addFlashGlobalInfo("Registro Editado Con Exito!!");

	}

	/**
	 * Eliminar Tipo de usuario
	 */
	public void eliminarTipoUs() {
		try {
			gestionAdmEJB.eliminarTipoUs(Integer.parseInt(codigoTipoUs));
			registrarAuditoria("Eliminar", "Tipos de usuario", "N/A");
			Messages.addFlashGlobalInfo("Registro Eliminado Con Exito!!");
		} catch (Exception e) {
			Messages.addFlashGlobalError("=( lo snetimos no se puedo eliminar el registro");

		}
	}

	/**
	 * Eliminar Area de la empresa
	 */
	public void eliminarAreaEmpresa() {
		try {
			gestionAdmEJB.eliminarAreaEmpresa(Integer.parseInt(codigoAreaEmp));
			registrarAuditoria("Eliminar", "Areas de la empresa", "N/A");
			Messages.addFlashGlobalInfo("Registro Eliminado Con Exito!!");
		} catch (Exception e) {
			Messages.addFlashGlobalError("=( lo snetimos no se puedo eliminar el registro");
		}
	}

	/**
	 * Registro de usuarios con paginas
	 */
	public void registroUsuarioXPagina() {
		try {
	
			gestionAdmEJB.crearAcceso(codigoUsuario,codigoPagina);

			Messages.addFlashGlobalInfo("Registro Exitoso!!");
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
	/**
	 * lista de acceso por id de usuario
	 * @param usuario
	 */
	public void listaAcXUs() {
		listaAc = gestionAdmEJB.listaAcceso(codigoUsuario);
	}
	
	public void eliminarPXU(Acceso acceso) {
		
	}
	
}
