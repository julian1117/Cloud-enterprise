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

	public static List<DWauditoria> listaTransformacion;

	/**
	 * Lista d elos objetos a tratar
	 * 
	 * @return
	 */
	public List<Auditoria> cargarDWAuditoria() {
		em.setBd(1);
		return (List<Auditoria>) (Object) em.listar(Auditoria.AUDITORIA);
	}

	/**
	 * Transformacion de datos
	 * @param bd
	 * @return
	 * @throws ParseException
	 */
	public List<DWauditoria> transformacionAuditoria(int bd) throws ParseException {

		listaTransformacion = new ArrayList<DWauditoria>();
		
		em.setBd(bd);
		List<Auditoria> list = (List<Auditoria>) (Object) em.listar(Auditoria.AUDITORIA);

		String[] nav = new String[5];
		String[] dat = new String[2];

		for (int i = 0; i < list.size(); i++) {
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			//paso date a String
			String fecha = formatter.format(list.get(i).getFecha());			 
			
			nav = list.get(i).getNavegador().split(".0");
			dat = fecha.split(" ");
						
			DWauditoria au = new DWauditoria();
			au.setNavegador(nav[0]);
			au.setFecha(dat[0]);
			au.setOrigen(list.get(i).getOrigen());
			au.setAccion(list.get(i).getAccion());
			au.setNombre(list.get(i).getNombre());

			listaTransformacion.add(au);

		}
		return listaTransformacion;
	}
	
	public void enviarTransformacionDatos() throws ParseException{
		for(int i=0;i<listaTransformacion.size();i++) {
			em.editarDW(listaTransformacion.get(i));
		}
	}

}
