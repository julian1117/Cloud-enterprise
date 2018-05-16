package co.edu.eam.ingesoft.bi.negocio.beans;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.bi.negocio.conexion.Conexion;
import co.edu.eam.ingesoft.bi.negocio.excepciones.ExcepcionNegocio;


import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.AreaEmpresa;

@Stateless
@LocalBean
public class AreaEmpresaEJB {

	@EJB
	private Conexion em;
	
	public void crearArea(AreaEmpresa idArea, int bd) {
		AreaEmpresa area = buscarArea(idArea.getIdArea(),bd);
		
		if(area == null) {
			em.crear(idArea);
		}else {
			throw new ExcepcionNegocio("El Area ya se encuentra registrado");
			
		}
	}
	
	public AreaEmpresa buscarArea(Integer idArea, int bd) {
		em.setBd(bd);
		return (AreaEmpresa) em.buscar(AreaEmpresa.class, idArea);
	}
	
	public void editarArea (AreaEmpresa area, int bd) {
		AreaEmpresa areaEm = buscarArea(area.getIdArea(),bd);
		
		if(areaEm != null) {
			em.editar(area);
		}else {
			throw new ExcepcionNegocio("El Area ya se encuentra registrado");
			
		}
		
	}
	
}
