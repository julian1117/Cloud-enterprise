package co.edu.eam.ingesoft.bi.negocio.beans;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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

		// list = (List<Venta>) (Object) em.listar(Venta.LISTA_VENT);

		for (int i = 0; i < list.size(); i++) {

			DWProducto producto = new DWProducto();
			producto.setNombre(list.get(i).getInventario().getProducto().getNombre());
			producto.setDescirpcion(list.get(i).getInventario().getProducto().getDescirpcion());
			producto.setValor(list.get(i).getInventario().getProducto().getValor());

			DWInventario inv = new DWInventario();
			inv.setCantidad(list.get(i).getInventario().getCantidad());
			inv.setFechaIngreso(list.get(i).getInventario().getFechaIngreso().toString());
			inv.setProducto(producto);

			DWpersona persona = new DWpersona();
			persona.setCedula(list.get(i).getGestionVenta().getPersona().getCedula());
			persona.setNombreCompleto(list.get(i).getGestionVenta().getPersona().getNombre() + " "
					+ list.get(i).getGestionVenta().getPersona().getApellido());

			Date fechaActual = new Date();
			int fecha = list.get(i).getGestionVenta().getPersona().getFechaNacimiento().getYear();
			int edad = fechaActual.getYear() - fecha;

			persona.setFechaNacimiento(String.valueOf(edad));
			persona.setGenero(list.get(i).getGestionVenta().getPersona().getGenero().getGenero());

			DWpersona perEmp = new DWpersona();
			perEmp.setNombreCompleto(list.get(i).getInventario().getIdPersona().getIdPersona().getNombre() + " "
					+ list.get(i).getInventario().getIdPersona().getIdPersona().getApellido());

			Date fechaActualEm = new Date();
			int fechaEm = list.get(i).getInventario().getIdPersona().getIdPersona().getFechaNacimiento().getYear();
			int edadEm = fechaActualEm.getYear() - fechaEm;

			perEmp.setFechaNacimiento(String.valueOf(edadEm));
			perEmp.setGenero(list.get(i).getInventario().getIdPersona().getIdPersona().getGenero().getGenero());
			perEmp.setCedula(list.get(i).getInventario().getIdPersona().getIdPersona().getCedula());

			DWempleado empleado = new DWempleado();
			empleado.setIdPersona(perEmp);
			empleado.setFechaIngreso(list.get(i).getGestionVenta().getEmpleado().getFechaIngreso());
			empleado.setSalario(list.get(i).getGestionVenta().getEmpleado().getSalario());

			DWgestionVenta gestion = new DWgestionVenta();
			gestion.setIdFactura(list.get(i).getGestionVenta().getIdFactura());
			gestion.setFecha(list.get(i).getGestionVenta().getFecha());

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

	public void enviarTransformacionDatosVenta() throws ParseException {

		for (int i = 0; i < listaVenta.size(); i++) {

			em.consultaNativa("INSERT INTO cloud.dwproducto (descripcion,nombre,valor) VALUES ('"
					+ listaVenta.get(i).getInventario().getProducto().getDescirpcion() + "','"
					+ listaVenta.get(i).getInventario().getProducto().getNombre() + "','"
					+ listaVenta.get(i).getInventario().getProducto().getValor() + "');");

			
			em.consultaNativa("INSERT INTO cloud.dw_persona(cedula,fechaNacimiento,genero,nombre) VALUES ('"
					+ listaVenta.get(i).getPersona().getCedula() + "','"
					+ listaVenta.get(i).getPersona().getFechaNacimiento() + "','"
					+ listaVenta.get(i).getPersona().getGenero() + "','"
					+ listaVenta.get(i).getPersona().getNombreCompleto() + "');");

			em.consultaNativa("INSERT INTO cloud.dwgestionventa (fecha) VALUES ('"
					+ listaVenta.get(i).getGestionVenta().getFecha() + "');");
			
			

			// em.consultaNativa("INSERT INTO cloud.dw_empleado (fechaIngreso,salario,persona_Id) VALUES ('"
			// +listaVenta.get(i).getEmpleado().getFechaIngreso()+"','"
			// +listaVenta.get(i).getEmpleado().getSalario()+"','"
			// +listaVenta.get(i).getEmpleado().getIdPersona().getIdPer()+"');");

			// em.consultaNativa("INSERT INTO cloud.dwinventario
			// (cantidad,fechaIngreso,producto) VALUES ('"
			// +listaVenta.get(i).getInventario().getCantidad()+"','"
			// +listaVenta.get(i).getInventario().getFechaIngreso()+"','"
			// +listaVenta.get(i).getInventario().getProducto().getIdProducto()+"');");

		}

	}
	
	public void enviarTransformacionDatosRolling() throws ParseException {
		em.consultaNativa("TRUNCATE TABLE cloud.dwventa;");
		for (int i = 0; i < listaVenta.size(); i++) {

			em.consultaNativa("INSERT INTO cloud.dwproducto (descripcion,nombre,valor) VALUES ('"
					+ listaVenta.get(i).getInventario().getProducto().getDescirpcion() + "','"
					+ listaVenta.get(i).getInventario().getProducto().getNombre() + "','"
					+ listaVenta.get(i).getInventario().getProducto().getValor() + "');");

			em.consultaNativa("INSERT INTO cloud.dw_persona(cedula,fechaNacimiento,genero,nombre) VALUES ('"
					+ listaVenta.get(i).getPersona().getCedula() + "','"
					+ listaVenta.get(i).getPersona().getFechaNacimiento() + "','"
					+ listaVenta.get(i).getPersona().getGenero() + "','"
					+ listaVenta.get(i).getPersona().getNombreCompleto() + "');");

			em.consultaNativa("INSERT INTO cloud.dwgestionventa (fecha) VALUES ('"
					+ listaVenta.get(i).getGestionVenta().getFecha() + "');");
			
			
		//	 em.consultaNativa("INSERT INTO cloud.dw_empleado (fechaIngreso,salario,persona_Id) VALUES ('"
			// +listaVenta.get(i).getEmpleado().getFechaIngreso()+"','"
			 //+listaVenta.get(i).getEmpleado().getSalario()+"','"
			 //+listaVenta.get(i).getEmpleado().getIdPersona().getIdPer()+"');");

			// em.consultaNativa("INSERT INTO cloud.dwinventario
			// (cantidad,fechaIngreso,producto) VALUES ('"
			// +listaVenta.get(i).getInventario().getCantidad()+"','"
			// +listaVenta.get(i).getInventario().getFechaIngreso()+"','"
			// +listaVenta.get(i).getInventario().getProducto().getIdProducto()+"');");
			
//			em.consultaNativa("INSERT INTO cloud.dwventa(CANTIDAD,empleado_id,GestionVenta_id,Inventario_id,persona_id) VALUES ('"
					//		+listaVenta.get(i).getCantidad()+ "','"
						//	+listaVenta.get(i).getEmpleado().getIdEmpleado()+ "','"
							//+listaVenta.get(i).getGestionVenta().getIdFactura()+ "','"
							//+listaVenta.get(i).getInventario().getIdInventario()+ "','"
							//+listaVenta.get(i).getPersona().getIdPer()+"');");


		}
	}

}
