package co.edu.eam.ingesoft.bi.negocio.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades.DWauditoria;
import co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades.DWusuario;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Auditoria;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Persona;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.TipoUsuario;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.bi.negocio.conexion.Conexion;

@Stateless
@LocalBean
public class DWGeneral {

	@EJB
	private Conexion em;

	public static List<Auditoria> list;

	public static List<Usuario> listUsuario;

	public static List<DWauditoria> listaTransformacion;

	public static List<DWusuario> listaTransformacionUsu;

	/**
	 * Lista delos objetos a tratar
	 * 
	 * @return
	 */
	public List<Auditoria> cargarDWAuditoria() {
		em.setBd(1);
		list = (List<Auditoria>) (Object) em.listar(Auditoria.AUDITORIA);
		cargarDWUsuario();
		return list;
	}

	/**
	 * Lista delos objetos a tratar
	 * 
	 * @return
	 */
	public List<Usuario> cargarDWUsuario() {
		em.setBd(1);
		listUsuario = (List<Usuario>) (Object) em.listar(Usuario.USUARIO_I);
		return listUsuario;
	}

	/**
	 * Lista delos objetos a tratar para acumulacion
	 * 
	 * @return
	 */
	public List<Auditoria> cargarDWAuditoriaAcumulacion(Date fechaIni, Date fechaFin) {
		em.setBd(1);
		list = (List<Auditoria>) (Object) em.listarConDosParametros(Auditoria.POR_AUDITORIA_FECHA, fechaIni, fechaFin);
		cargarDWUsuario();
		return list;

	}

	public Usuario buscarUsuario(Object numero) {
		em.setBd(1);
		return (Usuario) em.buscar(Usuario.class, numero);
	}

	/**
	 * Transformacion de datos
	 * 
	 * @param bd
	 * @return
	 * @throws ParseException
	 */
	public List<DWauditoria> transformacionAuditoria(int bd) throws ParseException {

		listaTransformacion = new ArrayList<DWauditoria>();
		listaTransformacionUsu = new ArrayList<DWusuario>();

		// list = (List<Auditoria>) (Object) em.listar(Auditoria.AUDITORIA);

		String[] nav = new String[5];
		String[] dat = new String[2];

		for (int i = 0; i < list.size(); i++) {

			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			// paso date a String
			String fecha = formatter.format(list.get(i).getFecha());

			nav = list.get(i).getNavegador().split(".0");
			dat = fecha.split(" ");

			// Buscar un usuario por cedula de persona
			Usuario us = null;
			for (int j = 0; j < listUsuario.size(); j++) {
				if (!list.get(i).getUsuarioSe().equalsIgnoreCase("N/A")) {
					if (listUsuario.get(j).getPersona().getCedula() == (Integer.parseInt(list.get(i).getUsuarioSe()))) {
						us = buscarUsuario(listUsuario.get(j).getCodigo());
					}
				} else {
					us = buscarUsuario(1000000);
				}
			}

			DWusuario dWusuario = new DWusuario();
			if (us != null) {

				dWusuario.setNombreUs(us.getNombre());
			}

			DWauditoria au = new DWauditoria();
			au.setNavegador(nav[0]);
			au.setFecha(dat[0]);
			au.setOrigen(list.get(i).getOrigen());
			au.setAccion(list.get(i).getAccion());
			au.setNombre(list.get(i).getNombre());
			au.setUsuarioSe(list.get(i).getUsuarioSe());
			au.setUsuarioAfectado(list.get(i).getUsuarioAfectado());
			au.setDwUsuario(dWusuario);

			listaTransformacion.add(au);
			listaTransformacionUsu.add(dWusuario);

		}
		return listaTransformacion;
	}

	/**
	 * Enviar datos transformados al dw
	 * 
	 * @throws ParseException
	 */
	public void enviarTransformacionDatos() throws ParseException {

		for (int j = 0; j < listaTransformacionUsu.size(); j++) {
			List<DWusuario> dwUsuariosActuales = new ArrayList<DWusuario>();
			em.setBd(3);

			em.consultaNativa("INSERT INTO cloud.dw_usuario (nombre_us) VALUES ('"
					+ listaTransformacionUsu.get(j).getNombreUs() + "');");

		}

		for (int i = 0; i < listaTransformacion.size(); i++) {

			em.consultaNativa(
					"INSERT INTO cloud.dw_auditoria (accion, fecha, navegador, nombre, origen, usuarioAfectado, usuarioSe, DW_usuario) "
							+ "VALUES ('" + listaTransformacion.get(i).getAccion() + "', '"
							+ listaTransformacion.get(i).getFecha() + "', '" + listaTransformacion.get(i).getNavegador()
							+ "', '" + listaTransformacion.get(i).getNombre() + "', '"
							+ listaTransformacion.get(i).getOrigen() + "', '"
							+ listaTransformacion.get(i).getUsuarioAfectado() + "', '"
							+ listaTransformacion.get(i).getUsuarioSe()
							+ "', (SELECT id FROM cloud.dw_usuario where nombre_us='"
							+ listaTransformacion.get(i).getDwUsuario().getNombreUs() + "' limit 1));");
		}

	}

	public void enviarTransformacionDatosRolling() throws ParseException {
		em.consultaNativa("TRUNCATE TABLE cloud.dw_auditoria;");
		em.consultaNativa("delete from cloud.dw_usuario where id between 1 and 1000000000;");
				
		for (int j = 0; j < listaTransformacionUsu.size(); j++) {
			List<DWusuario> dwUsuariosActuales = new ArrayList<DWusuario>();
			em.setBd(3);

			em.consultaNativa("INSERT INTO cloud.dw_usuario (nombre_us) VALUES ('"
					+ listaTransformacionUsu.get(j).getNombreUs() + "');");

		}
		
		for (int i = 0; i < listaTransformacion.size(); i++) {

			em.consultaNativa(
					"INSERT INTO cloud.dw_auditoria (accion, fecha, navegador, nombre, origen, usuarioAfectado, usuarioSe, DW_usuario) "
							+ "VALUES ('" + listaTransformacion.get(i).getAccion() + "', '"
							+ listaTransformacion.get(i).getFecha() + "', '" + listaTransformacion.get(i).getNavegador()
							+ "', '" + listaTransformacion.get(i).getNombre() + "', '"
							+ listaTransformacion.get(i).getOrigen() + "', '"
							+ listaTransformacion.get(i).getUsuarioAfectado() + "', '"
							+ listaTransformacion.get(i).getUsuarioSe()
							+ "', (SELECT id FROM cloud.dw_usuario where nombre_us='"
							+ listaTransformacion.get(i).getDwUsuario().getNombreUs() + "' limit 1));");
		}
	}
}
