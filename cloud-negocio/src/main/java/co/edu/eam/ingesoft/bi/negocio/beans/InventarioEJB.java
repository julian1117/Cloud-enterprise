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
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Empleado;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Inventario;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Producto;

@Stateless
@LocalBean
public class InventarioEJB {
	
	@EJB
	private Conexion em;
	
	public void crearInventario(Inventario inventario, int bd) {		
		em.setBd(bd);
		em.crear(inventario);				
	}
	
	public Inventario buscarInventario(Integer idInventario,int bd) {
		em.setBd(bd);
		return (Inventario) em.buscar(Inventario.class, idInventario);
	}
	
	public void editarInventario(Inventario inventario,int bd) {
		em.setBd(bd);
		em.editar(inventario);
		
	}
	
	public void eliminarInventario(Integer inventario, int bd) {
		try {
			Inventario inv = buscarInventario(inventario, bd);
			if(inv != null) {
				em.setBd(bd);
				em.eliminar(inv);
			}
		} catch (Exception e) {
			throw new ExcepcionNegocio("No fue posible eliminar el inventario ");
			}
	}
	
	public Empleado buscarEmpleado(Integer empleado, int bd) {
		em.setBd(bd);
		return (Empleado) em.buscar(Empleado.class, empleado);
	}
	
	public List<Object> listarProductos(int bd) {
		em.setBd(bd);
		return em.listar(Producto.LISTA_PRODUCTO);
	}
	
	public List<Object> listarInventario(int bd){
		em.setBd(bd);
		return em.listar(Inventario.LISTA_InventarioS);
	}
	

}
