package co.edu.eam.ingesoft.bi.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.AreaEmpresa;
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
	public void eliminarTipoUs(TipoUsuario tipoUsuario) {
		try {
			em.remove(tipoUsuario);
		} catch (ExcepcionNegocio e) {
			throw new ExcepcionNegocio("No fue posible eliminar el tipo de usuario ");
		}
	}

	/**
	 * Eliminar area de la empresa
	 * 
	 * @param tipoUsuario
	 */
	public void eliminarAreaEmpresa(AreaEmpresa areaEmpresa) {
		try {
			em.remove(areaEmpresa);
		} catch (ExcepcionNegocio e) {
			throw new ExcepcionNegocio("No fue posible eliminar el area de la empresa ");
		}
	}
}
