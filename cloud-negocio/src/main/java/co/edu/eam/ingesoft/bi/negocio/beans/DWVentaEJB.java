package co.edu.eam.ingesoft.bi.negocio.beans;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades.DWInventario;
import co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades.DWempleado;
import co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades.DWgestionVenta;
import co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades.DWpersona;
import co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades.DWventa;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Auditoria;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Venta;
import co.edu.eam.ingesoft.bi.negocio.conexion.Conexion;

@Stateless
@LocalBean
public class DWVentaEJB {

	@EJB
	private Conexion em;

	public static List<DWventa> listaVenta;

	public static List<Venta> list;

	public List<Venta> cargarDWventa() {
		em.setBd(1);
		list = (List<Venta>) (Object) em.listar(Venta.LISTA_VENT);
		return list;
	}

	public List<Venta> cargarDWVentaAcumulacion(Date fechaIni, Date fechaFin) {
		System.out.println(
				fechaIni + " " + fechaFin + "------------------------------------------------------------------");
		if (fechaIni.getDate() <= fechaFin.getDate()) {
			em.setBd(1);
			list = (List<Venta>) (Object) em.listarConDosParametros(Venta.POR_VENTA_FECHA, fechaIni, fechaFin);
			return list;
		}
		return null;
	}

	public List<DWventa> tranformacionVenta(int bd) {

		listaVenta = new ArrayList<DWventa>();
		em.setBd(bd);

		for (int i = 0; i < list.size(); i++) {

			// Inventario
			DWInventario inv = new DWInventario();
			inv.setCantidad(list.get(i).getInventario().getCantidad());
			inv.setFechaIngresoInv(list.get(i).getInventario().getFechaIngreso());
			inv.setValorProducto(String.valueOf(list.get(i).getInventario().getProducto().getValor()));
			inv.setDescripcionProducto(list.get(i).getInventario().getProducto().getDescirpcion());
			inv.setNombreProducto(list.get(i).getInventario().getProducto().getNombre());

			// Cliente
			Date fechaActual = new Date();
			int fecha = list.get(i).getGestionVenta().getPersona().getFechaNacimiento().getYear();
			int edad = fechaActual.getYear() - fecha;

			DWpersona persona = new DWpersona();
			persona.setCedula(list.get(i).getGestionVenta().getPersona().getCedula());
			persona.setNombreCompleto(list.get(i).getGestionVenta().getPersona().getNombre() + " "
					+ list.get(i).getGestionVenta().getPersona().getApellido());
			persona.setFechaNacimiento(String.valueOf(edad));
			persona.setGenero(list.get(i).getGestionVenta().getPersona().getGenero().getGenero());

			/**
			 * DWpersona perEmp = new DWpersona();
			 * perEmp.setNombreCompleto(list.get(i).getInventario().getIdPersona().getIdPersona().getNombre()
			 * + " " +
			 * list.get(i).getInventario().getIdPersona().getIdPersona().getApellido());
			 * 
			 * Date fechaActualEm = new Date(); int fechaEm =
			 * list.get(i).getInventario().getIdPersona().getIdPersona().getFechaNacimiento().getYear();
			 * int edadEm = fechaActualEm.getYear() - fechaEm;
			 * 
			 * perEmp.setFechaNacimiento(String.valueOf(edadEm));
			 * perEmp.setGenero(list.get(i).getInventario().getIdPersona().getIdPersona().getGenero().getGenero());
			 * perEmp.setCedula(list.get(i).getInventario().getIdPersona().getIdPersona().getCedula());
			 */

			// Empleado
			DWempleado empleado = new DWempleado();
			empleado.setFechaIngresoEmp(list.get(i).getGestionVenta().getEmpleado().getFechaIngreso());
			empleado.setSalario(list.get(i).getGestionVenta().getEmpleado().getSalario());
			empleado.setNombreEmpleado(list.get(i).getGestionVenta().getEmpleado().getIdPersona().getNombre() + " "
					+ list.get(i).getGestionVenta().getEmpleado().getIdPersona().getApellido());

			// Gestion Venta
			DWgestionVenta gestion = new DWgestionVenta();
			gestion.setNumeroFactura(String.valueOf(list.get(i).getGestionVenta().getIdFactura()));
			gestion.setFecha(list.get(i).getGestionVenta().getFecha());

			// Venta
			DWventa ve = new DWventa();
			ve.setCantidad(list.get(i).getCantidad());
			ve.setInventario(inv);
			ve.setGestionVenta(gestion);
			ve.setEmpleado(empleado);
			ve.setPersona(persona);

			// Lista venta principal
			listaVenta.add(ve);

		}
		return listaVenta;

	}

	public void enviarTransformacionDatosVenta() throws ParseException {

		for (int i = 0; i < listaVenta.size(); i++) {

			em.consultaNativa("INSERT INTO cloud.dw_persona(cedula,fechaNacimiento,genero,nombre) VALUES ('"
					+ listaVenta.get(i).getPersona().getCedula() + "','"
					+ listaVenta.get(i).getPersona().getFechaNacimiento() + "','"
					+ listaVenta.get(i).getPersona().getGenero() + "','"
					+ listaVenta.get(i).getPersona().getNombreCompleto() + "');");

			em.consultaNativa("INSERT INTO cloud.dwgestionventa (fecha,numero_factura) VALUES ('"
					+ listaVenta.get(i).getGestionVenta().getFecha() + "','"
					+listaVenta.get(i).getGestionVenta().getNumeroFactura()+"');");

			em.consultaNativa("INSERT INTO cloud.dw_empleado (fechaIngreso_emp,salario,Nombre_empleado) VALUES ('"
					+ listaVenta.get(i).getEmpleado().getFechaIngresoEmp() + "','"
					+ listaVenta.get(i).getEmpleado().getSalario() + "','"
					+ listaVenta.get(i).getEmpleado().getNombreEmpleado() + "');");

			em.consultaNativa(
					"INSERT INTO cloud.dwinventario (cantidad,fecha_ingreso_inv,descripcion_producto,valor_producto,nombre_producto) "
							+ "VALUES ('" + listaVenta.get(i).getInventario().getCantidad() + "','"
							+ listaVenta.get(i).getInventario().getFechaIngresoInv() + "','"
							+ listaVenta.get(i).getInventario().getDescripcionProducto() + "','"
							+ listaVenta.get(i).getInventario().getValorProducto() + "','"
							+ listaVenta.get(i).getInventario().getNombreProducto() + "');");

			em.consultaNativa(
					"INSERT INTO cloud.dwventa (CANTIDAD, empleado_id, GestionVenta_id, Inventario_id, persona_id) "
							+ "VALUES ('" + listaVenta.get(i).getCantidad() + "', "
							+ "(select p.id from cloud.dw_empleado  p order by  p.id desc limit 1), "
							+ "(select p.id from cloud.dwgestionventa  p order by  p.id desc limit 1), "
							+ "(select p.id from cloud.dwinventario  p order by  p.id desc limit 1), "
							+ "(select p.id from cloud.dw_persona  p order by  p.id desc limit 1));");
		}
	}

	public void enviarTransformacionDatosRolling() throws ParseException {
		
		em.consultaNativa("DELETE FROM cloud.dwventa WHERE id between 1 and 1000000000;");
		em.consultaNativa("DELETE FROM cloud.dw_persona WHERE id between 1 and 1000000000;");
		em.consultaNativa("DELETE FROM cloud.dw_empleado WHERE id between 1 and 1000000000;");
		em.consultaNativa("DELETE FROM cloud.dwinventario WHERE id between 1 and 1000000000;");
		em.consultaNativa("DELETE FROM cloud.dwgestionventa WHERE id between 1 and 1000000000;");

		for (int i = 0; i < listaVenta.size(); i++) {

			em.consultaNativa("INSERT INTO cloud.dw_persona(cedula,fechaNacimiento,genero,nombre) VALUES ('"
					+ listaVenta.get(i).getPersona().getCedula() + "','"
					+ listaVenta.get(i).getPersona().getFechaNacimiento() + "','"
					+ listaVenta.get(i).getPersona().getGenero() + "','"
					+ listaVenta.get(i).getPersona().getNombreCompleto() + "');");

			em.consultaNativa("INSERT INTO cloud.dwgestionventa (fecha,numero_factura) VALUES ('"
					+ listaVenta.get(i).getGestionVenta().getFecha() + "','"
					+listaVenta.get(i).getGestionVenta().getNumeroFactura()+"');");

			em.consultaNativa("INSERT INTO cloud.dw_empleado (fechaIngreso_emp,salario,Nombre_empleado) VALUES ('"
					+ listaVenta.get(i).getEmpleado().getFechaIngresoEmp() + "','"
					+ listaVenta.get(i).getEmpleado().getSalario() + "','"
					+ listaVenta.get(i).getEmpleado().getNombreEmpleado() + "');");

			em.consultaNativa(
					"INSERT INTO cloud.dwinventario (cantidad,fecha_ingreso_inv,descripcion_producto,valor_producto,nombre_producto) "
							+ "VALUES ('" + listaVenta.get(i).getInventario().getCantidad() + "','"
							+ listaVenta.get(i).getInventario().getFechaIngresoInv() + "','"
							+ listaVenta.get(i).getInventario().getDescripcionProducto() + "','"
							+ listaVenta.get(i).getInventario().getValorProducto() + "','"
							+ listaVenta.get(i).getInventario().getNombreProducto() + "');");

			em.consultaNativa(
					"INSERT INTO cloud.dwventa (CANTIDAD, empleado_id, GestionVenta_id, Inventario_id, persona_id) "
							+ "VALUES ('" + listaVenta.get(i).getCantidad() + "', "
							+ "(select p.id from cloud.dw_empleado  p order by  p.id desc limit 1), "
							+ "(select p.id from cloud.dwgestionventa  p order by  p.id desc limit 1), "
							+ "(select p.id from cloud.dwinventario  p order by  p.id desc limit 1), "
							+ "(select p.id from cloud.dw_persona  p order by  p.id desc limit 1));");
		}
	}

}
