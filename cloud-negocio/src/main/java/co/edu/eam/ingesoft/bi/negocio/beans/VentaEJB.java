package co.edu.eam.ingesoft.bi.negocio.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Empleado;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.GestionVenta;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Inventario;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Producto;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Venta;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.VentaPK;
import co.edu.eam.ingesoft.bi.negocio.conexion.Conexion;
import co.edu.eam.ingesoft.bi.negocio.excepciones.ExcepcionNegocio;

@Stateless
@LocalBean
public class VentaEJB {
	
	@EJB
	private Conexion em;
	
	public void crearGestionVenta(GestionVenta GestionVenta, int bd) {		
		GestionVenta inv = buscarGestionVenta(GestionVenta.getIdFactura(),bd);		
		if(inv == null) {
			em.setBd(bd);
			em.crear(GestionVenta);
		}else {
			throw new ExcepcionNegocio("El Factura con ese ID ya se encuentra registrado");
		}
				
	}
	
	public GestionVenta buscarGestionVenta(Integer idGestionVenta, int bd) {
		em.setBd(bd);
		return (GestionVenta) em.buscar(GestionVenta.class, idGestionVenta);
	}
	
	public void editarGestionVenta(GestionVenta GestionVenta, int bd) {
		GestionVenta inv = buscarGestionVenta(GestionVenta.getIdFactura(),bd);	
		if(inv != null) {
			em.setBd(bd);
			em.editar(GestionVenta);
		}else {
			throw new ExcepcionNegocio("El Factura ya se encuentra registrado");
		}
	}
	
	public void eliminarGestionVenta(Integer GestionVenta, int bd) {
		try {
		GestionVenta gestion = buscarGestionVenta(GestionVenta, bd);
		if(gestion!=null) {
			em.setBd(bd);
			em.eliminar(gestion);
		}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public Empleado buscarEmpleado(Integer empleado, int bd) {
		em.setBd(bd);
		return (Empleado)em.buscar(Empleado.class, empleado);
	}
	
	public List<Object> listarFacturas(int bd) {
		em.setBd(bd);
		return em.listar(GestionVenta.LISTAR_FACTURA);
		 
	}
	
	
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Venta buscarVenta(Integer idInventario,Integer idGestion, int bd) {
		em.setBd(bd);
		VentaPK ventaPK = new VentaPK(idInventario, idGestion);
		return (Venta) em.buscar(Venta.class, ventaPK);
	}
	
	public Inventario buscarInventario(Integer idInventario,int bd) {
		em.setBd(bd);
		return (Inventario) em.buscar(Inventario.class, idInventario);
	}
	
	public GestionVenta buscarIdGestionVenta(Integer idGestionVenta, int bd) {
		em.setBd(bd);
		return (GestionVenta)em.buscar(GestionVenta.class, idGestionVenta);
	}

	
	public void prueba(Integer a, Integer b, Integer c, int bd) {
		
		Inventario inve = buscarInventario(a,bd);
		GestionVenta gestion =  buscarIdGestionVenta(b,bd);

		Venta busVenta = buscarVenta(inve.getIdInventario(), gestion.getIdFactura(),bd);
		//System.out.println(busVenta.getGestionVenta() + "22222222222222222-----------------------"  );

		if(busVenta ==null) {
			Venta venta = new Venta(inve, gestion, c);
			em.setBd(bd);
			em.crear(venta);
		}else {
			throw new ExcepcionNegocio("El Venta ya se encuentra registrado");
			
		}
	}
}
