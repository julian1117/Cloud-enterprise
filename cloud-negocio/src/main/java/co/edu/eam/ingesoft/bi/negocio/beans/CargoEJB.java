package co.edu.eam.ingesoft.bi.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.eam.ingesoft.bi.negocio.excepciones.ExcepcionNegocio;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Cargo;

@Stateless
@LocalBean
public class CargoEJB {

	@PersistenceContext
	private EntityManager em;
	
	public void crearCargo(Cargo cargo) {
		Cargo ca = buscarCargo(cargo.getIdCar());
		
		if(ca == null ) {
			em.persist(cargo);
		}else {
		throw new ExcepcionNegocio("El cargo ya se encuentra registrado");
			
		}
	}
	
	public Cargo buscarCargo(Integer idCargo) {
		return em.find(Cargo.class, idCargo);
	}
	
	public void editarCargo(Cargo cargo) {
		Cargo ca = buscarCargo(cargo.getIdCar());
		
		if(ca != null ) {
			em.merge(cargo);
		}else {
			throw new ExcepcionNegocio("El Producto ya se encuentra registrado");
			
		}
	}

	

}
