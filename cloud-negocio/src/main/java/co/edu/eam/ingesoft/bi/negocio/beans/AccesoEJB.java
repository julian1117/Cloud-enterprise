package co.edu.eam.ingesoft.bi.negocio.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Paginas;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Usuario;

@Stateless
@LocalBean
public class AccesoEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Crear Accesos a usuarios
	 * @param acceso
	 */
	public void crearAccesos(Paginas acceso) {
		em.persist(acceso);
	}

	/**
	 * Buscar usuario por codigo
	 * @param codigo
	 * @return
	 */
	public Usuario buscarUsuario(Integer codigo) {
		return em.find(Usuario.class, codigo);
	}
}
