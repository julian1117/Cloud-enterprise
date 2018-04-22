package co.edu.eam.ingesoft.bi.negocio.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Genero;

@Stateless
@LocalBean
public class General_EJB {
	
	@PersistenceContext
	private EntityManager em;
	
	
	/*
	 * @julian camilo henao
	 * Permite buscar un genero por su id
	 * @idGenero id del genero para buscar
	 */
	public Genero buscarGenero(Integer idGenero) {
		return em.find(Genero.class, idGenero);
	}
	

}
