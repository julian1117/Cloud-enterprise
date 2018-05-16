package co.edu.eam.ingesoft.bi.negocio.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.bi.negocio.conexion.Conexion;
import co.edu.eam.ingesoft.bi.negocio.excepciones.ExcepcionNegocio;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Cargo;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Pais;

@Stateless
@LocalBean
public class CargoEJB {

	@EJB
	private Conexion em;
	
	public void crearCargo(Cargo cargo,int bd) {
		Cargo ca = buscarCargo(cargo.getIdCar(),bd);
		
		if(ca == null ) {
			em.crear(cargo);
		}else {
		throw new ExcepcionNegocio("El cargo ya se encuentra registrado");
			
		}
	}
	
	public Cargo buscarCargo(Integer idCargo, int bd) {
		em.setBd(bd);
		return (Cargo) em.buscar(Cargo.class, idCargo);
	}
	
	public void editarCargo(Cargo cargo,int bd) {
		Cargo ca = buscarCargo(cargo.getIdCar(), bd);
		
		if(ca != null ) {
			em.editar(cargo);
		}else {
			throw new ExcepcionNegocio("El Producto ya se encuentra registrado");
			
		}
	}
	
	public List<Object> listarCargo(int bd){
		em.setBd(bd);
		return  em.listar(Cargo.LISTA_CARGOS);		
	}
	
	

	

}
