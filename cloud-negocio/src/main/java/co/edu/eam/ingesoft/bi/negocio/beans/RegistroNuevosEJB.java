package co.edu.eam.ingesoft.bi.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Genero;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Persona;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Usuario;
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

	/**
	 * Busca una persona por su cedula
	 * 
	 * @param cedula
	 * @return objeto persona
	 */
	public Persona buscarPersona(Integer cedula) {
		return em.find(Persona.class, cedula);
	}

	/**
	 * Busca si ya existen usuario con el nombre de usuario enviado
	 * @param us
	 * @return
	 */
	public boolean buscarUsuarios(String us) {

		List<Usuario> use = em.createNamedQuery(Usuario.USUARIO).setParameter(1, us).getResultList();
		if (use.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public void editarCliente(Persona cedula) {
		Persona pers = buscarPersona(cedula.getCedula());
		if(pers!=null) {
			em.merge(cedula);
		}else {
			throw new ExcepcionNegocio("La persona ingresada no existe");
		}
	}
	
	
	/**
	 * Crea un usuario a partir de la persona creada
	 * @param usuario
	 */
	public void crearUsuario(Usuario usuario) {
		em.persist(usuario);
	}

}
