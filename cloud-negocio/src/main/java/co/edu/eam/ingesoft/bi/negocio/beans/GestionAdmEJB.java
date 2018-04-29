package co.edu.eam.ingesoft.bi.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Persona;
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

	
}
