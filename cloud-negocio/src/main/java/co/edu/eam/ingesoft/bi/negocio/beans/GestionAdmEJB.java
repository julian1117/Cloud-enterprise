package co.edu.eam.ingesoft.bi.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Acceso;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.AccesoPK;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.AreaEmpresa;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Paginas;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Persona;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.TipoUsuario;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.bi.negocio.excepciones.ExcepcionNegocio;

@Stateless
@LocalBean
public class GestionAdmEJB {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Listado de usuarios inactivos
	 * 
	 * @return listado de usuarios
	 */
	public List<Usuario> listaUsuarioI() {
		return em.createNamedQuery(Usuario.USUARIO_I).getResultList();
	}

	/**
	 * Buscar usuario por id
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Usuario buscarUsuario(Integer idUsuario) {
		return em.find(Usuario.class, idUsuario);
	}

	/**
	 * Actualizacion de estado del usuario
	 * 
	 * @param usuario
	 */
	public void ActivarUsario(Usuario usuario) {
		Usuario us = buscarUsuario(usuario.getCodigo());
		if (us != null) {
			us.setEstado(true);
			em.merge(us);
		} else {
			throw new ExcepcionNegocio(
					"No fue posible realizar el cambio de estado del usuario: " + usuario.getNombre());
		}
	}

	/**
	 * Actualizacion de estado del usuario a inactivo
	 * 
	 * @param usuario
	 */
	public void inactivarUsario(Usuario usuario) {
		Usuario us = buscarUsuario(usuario.getCodigo());
		if (us != null) {
			us.setEstado(false);
			em.merge(us);
		} else {
			throw new ExcepcionNegocio(
					"No fue posible realizar el cambio de estado del usuario: " + usuario.getNombre());
		}
	}

	/**
	 * Crear tipo de usuario
	 * 
	 * @param tipoUsuario
	 */
	public void crearTipoUs(TipoUsuario tipoUsuario) {
		em.persist(tipoUsuario);
	}

	/**
	 * Crear area de la empresa
	 * 
	 * @param areaEmpresa
	 */
	public void crearAreaEmpresa(AreaEmpresa areaEmpresa) {
		em.persist(areaEmpresa);
	}

	/**
	 * Lista de tipos de usuario
	 * 
	 * @param tipoUsuario
	 * @return
	 */
	public List<TipoUsuario> listaTipoUs() {
		return em.createNamedQuery(TipoUsuario.TIPO_IS).getResultList();
	}

	/**
	 * Buscar por tipo de usuario
	 * 
	 * @param idTipo
	 * @return
	 */
	public TipoUsuario buscarTipoUs(Integer idTipo) {
		return em.find(TipoUsuario.class, idTipo);
	}

	/**
	 * Buscar area de la empresa
	 * 
	 * @param idArea
	 * @return
	 */
	public AreaEmpresa buscarAreaEmpresa(Integer idArea) {
		return em.find(AreaEmpresa.class, idArea);
	}

	/**
	 * Editar tipo de usuario
	 * 
	 * @param tipoUsuario
	 */
	public void editarTipoUs(TipoUsuario tipoUsuario) {
		em.merge(tipoUsuario);
	}

	/**
	 * Editar area de la empresa
	 * 
	 * @param areaEmpresa
	 */
	public void editarAreaEmpresa(AreaEmpresa areaEmpresa) {
		em.merge(areaEmpresa);
	}

	/**
	 * Eliminar tipo de usuario
	 * 
	 * @param tipoUsuario
	 */
	public void eliminarTipoUs(Integer tipoUsuario) {
		try {
			TipoUsuario tipo = buscarTipoUs(tipoUsuario);
			if (tipo != null) {
				em.remove(tipo);
			}
		} catch (ExcepcionNegocio e) {
			throw new ExcepcionNegocio("No fue posible eliminar el tipo de usuario ");
		}
	}

	/**
	 * Eliminar area de la empresa
	 * 
	 * @param tipoUsuario
	 */
	public void eliminarAreaEmpresa(Integer areaEmpresa) {
		try {
			AreaEmpresa area = buscarAreaEmpresa(areaEmpresa);
			if (area != null) {
				em.remove(areaEmpresa);
			}
		} catch (

		ExcepcionNegocio e) {
			throw new ExcepcionNegocio("No fue posible eliminar el area de la empresa ");
		}
	}

	/**
	 * Lista de usuarios activos en el sistema
	 * 
	 * @return
	 */
	public List<Usuario> listaUsuariosAct() {
		return em.createNamedQuery(Usuario.USUARIOS_ACT).getResultList();
	}

	/**
	 * Lista de paginas del sistema
	 * 
	 * @return
	 */
	public List<Paginas> listaPaginas() {
		return em.createNamedQuery(Paginas.LIST_PAGINAS).getResultList();
	}

	/**
	 * Buscar pagina por idpagina
	 * 
	 * @param idPagina
	 */
	public Paginas buscarPagina(Integer idPagina) {
		return em.find(Paginas.class, idPagina);
	}

	/**
	 * Crear accesos
	 * @param acceso
	 */
	public void crearAcceso(Integer uss,Integer pagg) {
		
		//busco en el ejb
		Usuario us = buscarUsuario(uss);
		Paginas pag = buscarPagina(pagg);
		
		//creo el obj
		Acceso acc = new Acceso();
		acc.setUsuario(us);
		acc.setPaginas(pag);
		
		//BUsco si la relacion ya existe
		Acceso buscarAc = buscarAccesos(acc.getUsuario().getCodigo(), acc.getPaginas().getIdPagina());
		if (buscarAc == null) {
			//creo el objeto si no existe
			em.persist(acc);
		} else {
			throw new ExcepcionNegocio("No fue posible realizar el registro ");
		}
		
	}

	/**
	 * buscar accesos
	 * @param us
	 * @param pag
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Acceso buscarAccesos(Integer us, Integer pag) {

		AccesoPK accesoPK = new AccesoPK(us, pag);

		return em.find(Acceso.class, accesoPK);
	}
	
	/**
	 * lista de accesos por usuario
	 * @return
	 */
	public List<Acceso> listaAcceso(Integer codUser){
		return em.createNamedQuery(Acceso.LISTA_ACCESO_US).setParameter(1, codUser).getResultList();
	}
	
}
