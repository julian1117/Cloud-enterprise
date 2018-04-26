package co.edu.eam.ingesoft.bi.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Genero;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Persona;
import co.edu.eam.ingesoft.bi.negocio.excepciones.ExcepcionNegocio;

@Stateless
@LocalBean
public class RegistroNuevosEJB {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Permite buscar un genero por su id de identificacion
	 * 
	 * @param id
	 *            del genero a buscar
	 * @return objeto genero buscado
	 */
	public Genero buscarGenro(Integer id) {
		return em.find(Genero.class, id);
	}

	/**
	 * lista todos los generos de la base de datos
	 * 
	 * @param ninguno
	 * @return lista de todos los generos
	 */
	public List<Genero> listaGeneros() {
		List<Genero> list = em.createNamedQuery(Genero.LISTA_GENEROS).getResultList();
		return list;
		// return em.createNativeQuery("SELECT * FROM
		// GENERO;",Genero.class).getResultList();
	}

	/**
	 * Permite crear una persona
	 * 
	 * @param persona
	 */
	public void crearPersona(Persona persona) {
		Persona pers = buscarPersona(persona.getCedula());
		if (pers == null) {
			em.persist(persona);
		} else {
			throw new ExcepcionNegocio("La persona ingresada ya existe");
		}
	}

	public Persona buscarPersona(Integer cedula) {
		return em.find(Persona.class, cedula);
	}

}
