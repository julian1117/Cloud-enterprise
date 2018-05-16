package co.edu.eam.ingesoft.bi.negocio.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.GestionVenta;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Venta;
import co.edu.eam.ingesoft.bi.negocio.conexion.Conexion;
import co.edu.eam.ingesoft.bi.negocio.excepciones.ExcepcionNegocio;

@Stateless
@LocalBean
public class GestionarVentaEJB {

	@EJB
	private Conexion em;
	
	public void crearGestionVenta (GestionVenta GestionVenta,int bd) {
		GestionVenta pr = buscarGestionVenta(GestionVenta.getIdFactura(),bd);
		
		if(pr== null ) {
			em.crear(GestionVenta);
		}else {
			throw new ExcepcionNegocio("El GestionVenta ya se encuentra registrado");
		}
	}
	
	
	
	/**
	 * permite la busqueda de un GestionVenta en el sistema
	 * @param id que representa el parametro de busqueda del productp
	 * @return el GestionVenta encontrado 
	 */
	public GestionVenta buscarGestionVenta(Integer id,int bd) {
		em.setBd(bd);
		return (GestionVenta) em.buscar(GestionVenta.class, id);
	}
	
	public void editar(GestionVenta GestionVenta,int bd) throws Exception {
		em.setBd(bd);
		GestionVenta pro = buscarGestionVenta(GestionVenta.getIdFactura(),bd);
		if(pro != null) {
			em.editar(GestionVenta);
		}else {
			throw new Exception("El GestionVenta no se encuentra en el sistema");

		}
	}
	
	public void eliminarGestionVenta(Integer GestionVenta,int bd) throws Exception {
		try {
			GestionVenta pro = buscarGestionVenta(GestionVenta,bd);
			if(pro != null) {
				em.setBd(bd);
				em.eliminar(pro);
			}
			
		}catch (Exception e) {

			throw new ExcepcionNegocio("No fue posible eliminar ");
		
		}
	}
	
	
	public List<Object> listaFacturas(int bd){
		em.setBd(bd);
		return 	 em.listar(GestionVenta.LISTAR_FACTURA);
		
	}
	
	public List<Object> listaVent(int bd){
		return em.listar(Venta.LISTA_VENT);
	}
	
	
}
