package co.edu.eam.ingesoft.bi.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.GestionVenta;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Venta;
import co.edu.eam.ingesoft.bi.negocio.excepciones.ExcepcionNegocio;

@Stateless
@LocalBean
public class GestionarVentaEJB {

	@PersistenceContext
	private EntityManager em;
	
	public void crearGestionVenta (GestionVenta GestionVenta) {
		GestionVenta pr = buscarGestionVenta(GestionVenta.getIdFactura());
		
		if(pr== null ) {
			em.persist(GestionVenta);
		}else {
			throw new ExcepcionNegocio("El GestionVenta ya se encuentra registrado");
		}
	}
	
	
	
	/**
	 * permite la busqueda de un GestionVenta en el sistema
	 * @param id que representa el parametro de busqueda del productp
	 * @return el GestionVenta encontrado 
	 */
	public GestionVenta buscarGestionVenta(Integer id) {
		return em.find(GestionVenta.class, id);
	}
	
	public void editar(GestionVenta GestionVenta) throws Exception {
		GestionVenta pro = buscarGestionVenta(GestionVenta.getIdFactura());
		if(pro != null) {
			em.merge(GestionVenta);
		}else {
			throw new Exception("El GestionVenta no se encuentra en el sistema");

		}
	}
	
	public void eliminarGestionVenta(GestionVenta GestionVenta) throws Exception {
		GestionVenta pro = buscarGestionVenta(GestionVenta.getIdFactura());
		if(pro != null) {
			em.remove(GestionVenta);
		}else {
		throw new Exception("El GestionVenta no se encuentra en el sistema");

		}
	}
	
	
	public List<GestionVenta> listaFacturas(){
		List<GestionVenta> list = em.createNamedQuery(GestionVenta.LISTAR_FACTURA).getResultList();
		return list;
	}
	
	public List<Venta> listaVent(){
		return em.createNamedQuery(Venta.LISTA_VENT).getResultList();
	}
	
	
}
