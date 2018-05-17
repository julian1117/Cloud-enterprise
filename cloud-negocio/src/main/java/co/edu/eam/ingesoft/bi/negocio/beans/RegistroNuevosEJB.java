package co.edu.eam.ingesoft.bi.negocio.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Genero;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Persona;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.bi.negocio.conexion.Conexion;
import co.edu.eam.ingesoft.bi.negocio.excepciones.ExcepcionNegocio;

@Stateless
@LocalBean
public class RegistroNuevosEJB {

	@EJB
	private Conexion em;

	/**
	 * Permite buscar un genero por su id de identificacion
	 * 
	 * @param id
	 *            del genero a buscar
	 * @return objeto genero buscado
	 */
	public Genero buscarGenro(Integer id,int bd) {
		em.setBd(bd);
		return (Genero) em.buscar(Genero.class, id);
	}

	/**
	 * lista todos los generos de la base de datos
	 * 
	 * @param ninguno
	 * @return lista de todos los generos
	 */
	public List<Object> listaGeneros(int bd) {
		em.setBd(bd);
		return em.listar(Genero.LISTA_GENEROS);
		
	}

	/**
	 * Permite crear una persona
	 * 
	 * @param persona
	 */
	public void crearPersona(Persona persona,int bd) {
		Persona pers = buscarPersona(persona.getCedula(),bd);
		if (pers == null) {
			em.setBd(bd);
			em.crear(persona);
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
	public Persona buscarPersona(Integer cedula,int bd) {
		em.setBd(bd);
		return (Persona)em.buscar(Persona.class, cedula);
	}

	/**
	 * Busca si ya existen usuario con el nombre de usuario enviado
	 * @param us
	 * @return
	 */
	public boolean buscarUsuarios(String us,int bd) {
		em.setBd(bd);
		List<Object> use = em.listarConParametroString(Usuario.USUARIO, us);
		if (use.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public void editarCliente(Persona cedula,int bd) {
		Persona pers = buscarPersona(cedula.getCedula(),bd);
		if(pers!=null) {
			em.setBd(bd);
			em.editar(cedula);
		}else {
			throw new ExcepcionNegocio("La persona ingresada no existe");
		}
	}
	
	
	/**
	 * Crea un usuario a partir de la persona creada
	 * @param usuario
	 */
	public void crearUsuario(Usuario usuario,int bd) {
		em.setBd(bd);
		em.crear(usuario);
	}
}
