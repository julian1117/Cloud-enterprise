package co.edu.eam.ingesoft.bi.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.AreaEmpresa;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Cargo;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Empleado;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Persona;

@Stateless
@LocalBean
public class RecursosHumanosEJB {

	@PersistenceContext
	private EntityManager em;

	public void crearEmpleado(Empleado Empleado) {
		Persona per = buscarEmpleado(Empleado.getIdPersona().getCedula());
		if(per != null) {
			em.persist(Empleado);
		}else {
	//		throw new ExcepcionNegocio("La Empleado ya se encuentra registrado");
		}
	}
	
	public Persona buscarEmpleado(Integer cedula) {
		return em.find(Persona.class, cedula);
		}
	
	public void editarEmpleado(Empleado Empleado) {
		Persona per = buscarEmpleado(Empleado.getIdPersona().getCedula());
		if(per != null) {
			em.merge(Empleado);
		}else {
	//		throw new ExcepcionNegocio("La Empleado ya se encuentra registrado");
		}
	}
	
	public List<Cargo> listarCargos() {
		List<Cargo> list= em.createNamedQuery(Cargo.LISTA_CARGOS).getResultList();
		return list;
	}
	
	public List<AreaEmpresa> listarAreas() {
		List<AreaEmpresa> list= em.createNamedQuery(AreaEmpresa.LISTA_AREA_EMPRESA).getResultList();
		return list;
	}
	
	public List<Empleado> listarEmpleado(){
		List<Empleado> list = em.createNamedQuery(Empleado.LISTA_EMPLEADOS).getResultList();
		return list;
	}
	
	public Cargo buscarCargo(Integer idCargo) {
		return em.find(Cargo.class, idCargo);
	}
	
	public Empleado buscarEmp(Integer empleado) {
		return em.find(Empleado.class, empleado);
	}
	

}
