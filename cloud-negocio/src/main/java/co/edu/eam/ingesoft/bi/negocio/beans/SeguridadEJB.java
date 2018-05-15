package co.edu.eam.ingesoft.bi.negocio.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Paginas;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Acceso;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Empresa;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Genero;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.bi.negocio.conexion.Conexion;

@Stateless
@LocalBean
public class SeguridadEJB {

	@EJB
	private Conexion em; // Instancia de la clase conexion que contiene los entitimanger

	public Usuario buscar(String us, int bd) {

		// List<Usuario> use = em.lis(Usuario.USUARIO).setParameter(1,
		// us).getResultList();
		em.setBd(bd);
		List<Object> use = em.listarConParametroString(Usuario.USUARIO, us);
		
		if (use.isEmpty()) {
			return null;
		} else {
			return (Usuario) use.get(0);
		}
	}

	public Usuario buscarUs(String nombreUs,int bd) {
		em.setBd(bd);
		return (Usuario) em.buscar(Usuario.class, nombreUs);
	}

	/*
	 * public List<Roll> listaRoles(Integer idRoll){ return
	 * em.createNamedQuery(Roll.listaRoles).setParameter(1, idRoll).getResultList();
	 * }
	 */

	public List<Object> listaAcc(String usuario) {
		return em.listarConParametroString(Acceso.LISTA_ACCESO,usuario);
	}

	/**
	 * Busca la configuracion de la base de datos
	 * @return
	 */
	public Empresa buscarEmpresa(){		
		return (Empresa) em.buscar(Empresa.class, 1);
	}
	
}
