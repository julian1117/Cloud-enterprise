package co.edu.eam.ingesoft.bi.negocio.beans;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Producto;
import co.edu.eam.ingesoft.bi.negocio.conexion.Conexion;
import co.edu.eam.ingesoft.bi.negocio.excepciones.ExcepcionNegocio;

@Stateless
@LocalBean
public class ProductoEJB {
	
	@EJB
	private Conexion em;

	/**
	 * crea un producto
	 * @param producto
	 * @param bd
	 */
	public void crearProducto(Producto producto, int bd) {
		em.setBd(bd);
		em.crear(producto);
	}
	
	//public void crearProducto (Producto producto, int bd) {
		//Producto pr = buscarProducto(producto.getIdProducto());
		
		//if(pr== null ) {
			//em.persist(producto);
		//}else {
		//	throw new ExcepcionNegocio("El Producto ya se encuentra registrado");
		//}
	//}
	
	/**
	 * permite la busqueda de un producto en el sistema
	 * @param id que representa el parametro de busqueda del productp
	 * @return el producto encontrado 
	 */
	public Producto buscarProducto(Integer id, int bd) {
		em.setBd(bd);
		return (Producto) em.buscar(Producto.class, id);
	}
	
	/**
	 * editar un producto
	 * @param producto
	 * @param bd
	 */
	public void editar(Producto producto, int bd) {
		em.setBd(bd);
		em.editar(producto);
	}
	
	/**
	 * eliminar un producto
	 * @param producto
	 * @param bd
	 */
	public void eliminarProducto(Integer producto, int bd) {
		try {
			
			Producto pro = buscarProducto(producto, bd);
			if(pro != null) {
				em.setBd(bd);
				em.eliminar(pro);
			}
			
		} catch (Exception e) {
			throw new ExcepcionNegocio("No fue posible eliminar el producto ");
			
		}
	}
}
