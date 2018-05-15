package co.edu.eam.ingesoft.bi.negocio.beans;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Paginas;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.bi.negocio.conexion.Conexion;

@Stateless
@LocalBean
public class AccesoEJB {
	
	@EJB
	private Conexion em;
	
	/**
	 * Crear Accesos a usuarios
	 * @param acceso
	 */
	public void crearAccesos(Paginas acceso,int bd) {
		em.setBd(bd);
		em.crear(acceso);
			//em.persist(acceso);
	}

	/**
	 * Buscar usuario por codigo
	 * @param codigo
	 * @return
	 */
	public Usuario buscarUsuario(Integer codigo,int bd) {
		em.setBd(bd);
		return (Usuario) em.buscar(Usuario.class, codigo);
	}
}
