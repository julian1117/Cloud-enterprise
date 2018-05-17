package co.edu.eam.ingesoft.bi.negocio.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Ciudad;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Departamento;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Genero;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Pais;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Persona;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.bi.negocio.conexion.Conexion;

@Stateless
@LocalBean
public class General_EJB {

	@EJB
	private Conexion em;

	/**
	 * @julian camilo henao Permite buscar un genero por su id
	 * @idGenero id del genero para buscar
	 */
	public Genero buscarGenero(Integer idGenero, int bd) {
		em.setBd(bd);
		return (Genero)em.buscar(Genero.class, idGenero);
	}

	/**
	 * Lista de paises
	 * 
	 * @return lidat de paises
	 */
	public List<Object> listaPaises(int bd) {
		em.setBd(bd);
		return em.listar(Pais.LISTA_PAISES);
	}

	/**
	 * Buscar pais por id
	 * 
	 * @param idPais
	 *            id del pais a buscar
	 * @return objeto pais
	 */
	public Pais buscarPais(Integer idPais, int bd) {
		em.setBd(bd);
		return (Pais) em.buscar(Pais.class, idPais);
	}

	/**
	 * buscar departamento por id
	 * 
	 * @param idDepartamento
	 * @return
	 */
	public Departamento buscarDepartamento(Integer idDepartamento, int bd) {
		em.setBd(bd);
		return (Departamento) em.buscar(Departamento.class, idDepartamento);
	}

	/**
	 * Permite buscar un departamento por medio del id del pais
	 * 
	 * @param pais
	 * @return lista de departamento
	 */
	public List<Object> listaDepartamento(String nombre, int bd) {
		em.setBd(bd);
		return em.listarConParametroString(Departamento.LISTA_DEPARTAMENTO, nombre);
	}

	/**
	 * Buscar una ciudad por id del departamento
	 * 
	 * @param Ciudad
	 * @return lista de ciudades
	 */
	public List<Object> listCiudad(String nombre, int bd) {
		em.setBd(bd);
		return em.listarConParametroString(Ciudad.LISTA_CIUDAD, nombre);
	}

	/**
	 * Buscar ciudad por id
	 * 
	 * @param idCiudad
	 * @return objeto ciudad
	 */
	public Ciudad buscarCiudad(Integer idCiudad, int bd) {
		em.setBd(bd);
		return (Ciudad) em.buscar(Ciudad.class, idCiudad);
	}

	

}
