package co.edu.eam.ingesoft.bi.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Ciudad;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Departamento;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Genero;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Pais;

@Stateless
@LocalBean
public class General_EJB {
	
	@PersistenceContext
	private EntityManager em;
	
	
	/**
	 * @julian camilo henao
	 * Permite buscar un genero por su id
	 * @idGenero id del genero para buscar
	 */
	public Genero buscarGenero(Integer idGenero) {
		return em.find(Genero.class, idGenero);
	}
	
	
	/**
	 * Lista de paises
	 * @return lidat de paises
	 */
	public List<Pais> listaPaises(){
		return em.createNamedQuery(Pais.LISTA_PAISES).getResultList();
	}
	
	/**
	 * Buscar pais por id
	 * @param idPais id del pais a buscar
	 * @return objeto pais
	 */
	public Pais buscarPais(Integer idPais) {
		return em.find(Pais.class, idPais);
	}
	
	/**
	 * buscar departamento por id
	 * @param idDepartamento
	 * @return
	 */
	public Departamento buscarDepartamento(Integer idDepartamento) {
		return em.find(Departamento.class, idDepartamento);
	}
	
	/**
	 * Permite buscar un departamento por medio del id del pais
	 * @param pais
	 * @return lista de departamento
	 */
	public List<Departamento> listaDepartamento (Pais pais){
		return em.createNativeQuery("select * from DEPARTAMENTO where ID =?1;", Departamento.class).setParameter(1, pais.getIdPais())
				.getResultList();
	}
	
	/**
	 * Buscar una ciudad por id del departamento
	 * @param Ciudad
	 * @return lista de ciudades
	 */
	public List<Ciudad> listCiudad(Ciudad Ciudad){
		return em.createNativeQuery("select * from CIUDAD where ID =?1;", Ciudad.class).setParameter(1, Ciudad.getId())
				.getResultList();
	}

	/**
	 * Buscar ciudad por id
	 * @param idCiudad
	 * @return objeto ciudad
	 */
	public Ciudad buscarCiudad(Integer idCiudad) {
		return em.find(Ciudad.class, idCiudad);
	}
	
}
