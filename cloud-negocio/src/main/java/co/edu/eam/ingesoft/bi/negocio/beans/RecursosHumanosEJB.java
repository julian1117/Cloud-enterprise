   package co.edu.eam.ingesoft.bi.negocio.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.AreaEmpresa;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Cargo;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Empleado;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Persona;
import co.edu.eam.ingesoft.bi.negocio.conexion.Conexion;
import co.edu.eam.ingesoft.bi.negocio.excepciones.ExcepcionNegocio;

@Stateless
@LocalBean
public class RecursosHumanosEJB {

	@EJB
	private Conexion em;

	public void crearEmpleado(Empleado Empleado, int bd) {
		Persona per = buscarEmpleado(Empleado.getIdPersona().getCedula(), bd);
		if(per != null) {
			em.setBd(bd);
			em.crear(Empleado);
		}else {
	//		throw new ExcepcionNegocio("La Empleado ya se encuentra registrado");
		}
	}
	
	public Persona buscarEmpleado(Integer cedula, int bd) {
		em.setBd(bd);
		return (Persona) em.buscar(Persona.class, cedula);
		}
	
	public void editarEmpleado(Empleado Empleado,int bd) {
		Persona per = buscarEmpleado(Empleado.getIdPersona().getCedula(), bd);
		if(per != null) {
			em.setBd(bd);
			em.editar(Empleado);
		}else {
	//		throw new ExcepcionNegocio("La Empleado ya se encuentra registrado");
		}
	}
	
	public void eliminarEmpleado(Integer idPersona, int bd) {
		try {
			Persona per = buscarEmpleado(idPersona, bd);
			if(per!=null) {
				em.setBd(bd);
				em.eliminar(per);
			}
		} catch (Exception e) {
			throw new ExcepcionNegocio("No fue posible eliminar el empleado ");
				}
	}
	
	public List<Object> listarCargos(int bd) {
		em.setBd(bd);
		return em.listar(Cargo.LISTA_CARGOS);
	}
	
	public List<Object> listarAreas(int bd) {
		em.setBd(bd);
		return em.listar(AreaEmpresa.LISTA_AREA_EMPRESA);
		
	}
	
	public List<Object> listarEmpleado(int bd){
		em.setBd(bd);
		return em.listar(Empleado.LISTA_EMPLEADOS);
	}
	
	public Cargo buscarCargo(Integer idCargo,int bd) {
		em.setBd(bd);
		return (Cargo) em.buscar(Cargo.class, idCargo);
	}
	
	public Empleado buscarEmp(Integer empleado,int bd) {
		em.setBd(bd);
		return(Empleado) em.buscar(Empleado.class, empleado);
	}
	

}
