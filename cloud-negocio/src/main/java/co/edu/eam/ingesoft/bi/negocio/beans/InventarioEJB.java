package co.edu.eam.ingesoft.bi.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.eam.ingesoft.bi.negocio.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Cargo;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Empleado;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Inventario;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Producto;

@Stateless
@LocalBean
public class InventarioEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	public void crearInventario(Inventario inventario) {		
		Inventario inv = buscarInventario(inventario.getIdInventario());		
		if(inv == null) {
			em.persist(inventario);
		}else {
			throw new ExcepcionNegocio("El Inventario con ese ID ya se encuentra registrado");
		}
				
	}
	
	public Inventario buscarInventario(Integer idInventario) {
		return em.find(Inventario.class, idInventario);
	}
	
	public void editarInventario(Inventario inventario) {
		Inventario inv = buscarInventario(inventario.getIdInventario());	
		if(inv != null) {
			em.merge(inventario);
		}else {
			throw new ExcepcionNegocio("El Inventario ya se encuentra registrado");
		}
	}
	
	public void eliminarInventario(Inventario inventario) {
		Inventario inv = buscarInventario(inventario.getIdInventario());	
		if(inv != null) {
			em.remove(inventario);
		}else {
			throw new ExcepcionNegocio("El Inventario no se encuentra registrado");
		}
	}
	
	public Empleado buscarEmpleado(Integer empleado) {
		return em.find(Empleado.class, empleado);
	}
	
	public List<Producto> listarProductos() {
		List<Producto> list= em.createNamedQuery(Producto.LISTA_PRODUCTO).getResultList();
		return list;
	}
	

}
