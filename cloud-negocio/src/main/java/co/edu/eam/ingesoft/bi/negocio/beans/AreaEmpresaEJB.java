package co.edu.eam.ingesoft.bi.negocio.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.eam.ingesoft.bi.negocio.excepciones.ExcepcionNegocio;


import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.AreaEmpresa;

@Stateless
@LocalBean
public class AreaEmpresaEJB {

	@PersistenceContext
	private EntityManager em;
	
	public void crearArea(AreaEmpresa idArea) {
		AreaEmpresa area = buscarArea(idArea.getIdArea());
		
		if(area == null) {
			em.persist(idArea);
		}else {
			throw new ExcepcionNegocio("El Area ya se encuentra registrado");
			
		}
	}
	
	public AreaEmpresa buscarArea(Integer idArea) {
		return em.find(AreaEmpresa.class, idArea);
	}
	
	public void editarArea (AreaEmpresa area) {
		AreaEmpresa areaEm = buscarArea(area.getIdArea());
		
		if(areaEm != null) {
			em.merge(area);
		}else {
			throw new ExcepcionNegocio("El Area ya se encuentra registrado");
			
		}
		
	}
	
}
