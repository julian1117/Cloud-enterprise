package co.edu.eam.ingesoft.bi.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Empleado;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.GestionVenta;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Producto;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Venta;
import co.edu.eam.ingesoft.bi.negocio.excepciones.ExcepcionNegocio;

@Stateless
@LocalBean
public class VentaEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	public void crearGestionVenta(GestionVenta GestionVenta) {		
		GestionVenta inv = buscarGestionVenta(GestionVenta.getIdFactura());		
		if(inv == null) {
			em.persist(GestionVenta);
		}else {
			throw new ExcepcionNegocio("El Factura con ese ID ya se encuentra registrado");
		}
				
	}
	
	public GestionVenta buscarGestionVenta(Integer idGestionVenta) {
		return em.find(GestionVenta.class, idGestionVenta);
	}
	
	public void editarGestionVenta(GestionVenta GestionVenta) {
		GestionVenta inv = buscarGestionVenta(GestionVenta.getIdFactura());	
		if(inv != null) {
			em.merge(GestionVenta);
		}else {
			throw new ExcepcionNegocio("El Factura ya se encuentra registrado");
		}
	}
	
	public void eliminarGestionVenta(GestionVenta GestionVenta) {
		GestionVenta inv = buscarGestionVenta(GestionVenta.getIdFactura());	
		if(inv != null) {
			em.remove(GestionVenta);
		}else {
			throw new ExcepcionNegocio("El GestionVenta no se encuentra registrado");
		}
	}
	
	public Empleado buscarEmpleado(Integer empleado) {
		return em.find(Empleado.class, empleado);
	}
	
	public List<GestionVenta> listarFacturas() {
		List<GestionVenta> list= em.createNamedQuery(GestionVenta.LISTAR_FACTURA).getResultList();
		return list;
	}
	
	public void crearVenta(Venta venta) {
		Venta ven = buscarVenta(venta.getIdVenta());
		
		if(ven == null) {
			em.persist(venta);
		}else {
			throw new ExcepcionNegocio("El Venta ya se encuentra registrado");
			
		}
	}
	
	public Venta buscarVenta(Integer idVenta) {
		return em.find(Venta.class, idVenta);
	}
	

}
