package co.edu.eam.ingesoft.bi.negocio.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades.DWInventario;
import co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades.DWProducto;
import co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades.DWempleado;
import co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades.DWgestionVenta;
import co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades.DWpersona;
import co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades.DWventa;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Venta;
import co.edu.eam.ingesoft.bi.negocio.conexion.Conexion;

@Stateless
@LocalBean
public class DWVentaEJB {

	@EJB
	private Conexion em;

	public static List<DWventa> listaVenta;

	public List<Venta> cargarDWventa() {
		em.setBd(1);
		return (List<Venta>) (Object) em.listar(Venta.LISTA_VENT);
	}

	public List<DWventa> tranformacionVenta(int bd) {

		listaVenta = new ArrayList<DWventa>();
		em.setBd(bd);

		List<Venta> list = (List<Venta>) (Object) em.listar(Venta.LISTA_VENT);

		for (int i = 0; i < list.size(); i++) {

			DWgestionVenta gestion = new DWgestionVenta();
			gestion.setIdFactura(list.get(i).getGestionVenta().getIdFactura());
			gestion.setFecha(list.get(i).getGestionVenta().getFecha());

			DWProducto producto = new DWProducto();
			producto.setNombre(list.get(i).getInventario().getProducto().getNombre());
			producto.setDescirpcion(list.get(i).getInventario().getProducto().getDescirpcion());
			producto.setValor(list.get(i).getInventario().getProducto().getValor());

			
			DWInventario inv = new DWInventario();
			inv.setCantidad(list.get(i).getInventario().getCantidad());
			inv.setFechaIngreso(list.get(i).getInventario().getFechaIngreso());
			inv.setProducto(producto);

			DWpersona persona = new DWpersona();
			persona.setCedula(list.get(i).getGestionVenta().getPersona().getCedula());
			persona.setNombreCompleto(list.get(i).getGestionVenta().getPersona().getNombre()
					+" " + list.get(i).getGestionVenta().getPersona().getApellido());
			
			DWpersona perEmp = new DWpersona();
			perEmp.setNombreCompleto(list.get(i).getInventario().getIdPersona().getIdPersona().getNombre()
					+ " " + list.get(i).getInventario().getIdPersona().getIdPersona().getApellido());
			
			DWempleado empleado = new DWempleado();
			empleado.setIdPersona(perEmp);
			empleado.setFechaIngreso(list.get(i).getGestionVenta().getEmpleado().getFechaIngreso());
			empleado.setSalario(list.get(i).getGestionVenta().getEmpleado().getSalario());

			
			DWventa ve = new DWventa();

			ve.setInventario(inv);
			ve.setGestionVenta(gestion);
			ve.setEmpleado(empleado);
			ve.setPersona(persona);
			ve.setCantidad(list.get(i).getCantidad());

			listaVenta.add(ve);

		}
		return listaVenta;

	}

}
