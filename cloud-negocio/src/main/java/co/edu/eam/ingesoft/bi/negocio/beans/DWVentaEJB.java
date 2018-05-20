package co.edu.eam.ingesoft.bi.negocio.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades.DWInventario;
import co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades.DWempleado;
import co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades.DWgestionVenta;
import co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades.DWventa;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Venta;
import co.edu.eam.ingesoft.bi.negocio.conexion.Conexion;

@Stateless
@LocalBean
public class DWVentaEJB {	

	@EJB
	private Conexion em;
	
	public static  List<DWventa> listaVenta;
	
	public List<Venta> cargarDWventa(){
		em.setBd(1);
		return (List<Venta>) (Object) em.listar(Venta.LISTA_VENT);
	}
	
	public List<DWventa> tranformacionVenta(int bd) {
		
		listaVenta = new ArrayList<DWventa>();		
		em.setBd(bd);
		
		List<Venta> list = (List<Venta>) (Object) em.listar(Venta.LISTA_VENT);
		
		for (int i = 0; i < list.size(); i++) {
			
			DWgestionVenta gestion = new DWgestionVenta();
			gestion.setFecha(list.get(i).getGestionVenta().getFecha());
			
			DWInventario inv = new DWInventario();
			inv.setCantidad(list.get(i).getInventario().getCantidad());
			inv.setFechaIngreso(list.get(i).getInventario().getFechaIngreso());
			inv.setProducto(list.get(i).getInventario().getProducto().getNombre());
			
			DWempleado empleado = new DWempleado();
		//	empleado.setIdPersona(list.get(i).getGestionVenta().getEmpleado().getIdPersona().getCedula());
			
			
			
			DWventa ve = new DWventa();
						
			
			ve.setInventario(inv);
			ve.setGestionVenta(gestion);			
			ve.setEmpleado(empleado);
	//		ve.setPersona(persona);
		//	ve.setProducto(producto);
			ve.setCantidad(list.get(i).getCantidad());
			
			
			
		}
		return null;
		
	}

}
