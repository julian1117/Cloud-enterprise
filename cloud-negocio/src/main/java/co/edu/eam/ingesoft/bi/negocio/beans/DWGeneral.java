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
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Auditoria;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.TipoUsuario;
import co.edu.eam.ingesoft.bi.negocio.conexion.Conexion;

@Stateless
@LocalBean
public class DWGeneral {

	@EJB
	private Conexion em;

	public static List<Auditoria> list;

	public static List<DWauditoria> listaTransformacion;

	/**
	 * Lista delos objetos a tratar
	 * 
	 * @return
	 */
	public List<Auditoria> cargarDWAuditoria() {
		em.setBd(1);
		list = (List<Auditoria>) (Object) em.listar(Auditoria.AUDITORIA);
		return list;
	}

	/**
	 * Lista delos objetos a tratar para acumulacion
	 * 
	 * @return
	 */
	public List<Auditoria> cargarDWAuditoriaAcumulacion(Date fechaIni, Date fechaFin) {
		System.out.println(
				fechaIni + " " + fechaFin + "------------------------------------------------------------------");
		if (fechaIni.getDate() <= fechaFin.getDate()) {
			em.setBd(1);
			list = (List<Auditoria>) (Object) em.listarConDosParametros(Auditoria.POR_AUDITORIA_FECHA, fechaIni,
					fechaFin);
			return list;
		}
		return null;
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

		// list = (List<Auditoria>) (Object) em.listar(Auditoria.AUDITORIA);

		String[] nav = new String[5];
		String[] dat = new String[2];

		for (int i = 0; i < list.size(); i++) {

			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			// paso date a String
			String fecha = formatter.format(list.get(i).getFecha());

			nav = list.get(i).getNavegador().split(".0");
			dat = fecha.split(" ");

			DWauditoria au = new DWauditoria();
			au.setNavegador(nav[0]);
			au.setFecha(dat[0]);
			au.setOrigen(list.get(i).getOrigen());
			au.setAccion(list.get(i).getAccion());
			au.setNombre(list.get(i).getNombre());
			au.setUsuarioSe(list.get(i).getUsuarioSe());
			au.setUsuarioAfectado(list.get(i).getUsuarioAfectado());

			listaTransformacion.add(au);

		}
		return listaTransformacion;
	}

	/**
	 * Enviar datos transformados al dw
	 * 
	 * @throws ParseException
	 */
	public void enviarTransformacionDatos(int dw) throws ParseException {
		/*if (dw == 1) {
			for (int i = 0; i < listaTransformacion.size(); i++) {
				em.editarDW(listaTransformacion.get(i));
			}
		} else if (dw == 2) {
			for (int i = 0; i < listaTransformacion.size(); i++) {*/
				em.consultaNativa("TRUNCATE TABLE dw_auditoria;");
				//em.editarDW(listaTransformacion.get(i));
			//}
		//}
	}

}
