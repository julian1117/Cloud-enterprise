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

	public static List<Auditoria> listaTransformacion;

	/**
	 * Lista d elos objetos a tratar
	 * 
	 * @return
	 */
	public List<Auditoria> cargarDWAuditoria() {
		em.setBd(1);
		return (List<Auditoria>) (Object) em.listar(Auditoria.AUDITORIA);
	}

	public List<Auditoria> transformacionAuditoria(int bd) throws ParseException {

		listaTransformacion = new ArrayList<Auditoria>();
		
		em.setBd(bd);
		List<Auditoria> list = (List<Auditoria>) (Object) em.listar(Auditoria.AUDITORIA);

		String[] nav = new String[5];

		for (int i = 0; i < list.size(); i++) {

		
			SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
			
			
			int dia = list.get(i).getFecha().getDate(); 
			int mes = list.get(i).getFecha().getMonth(); 
			int an = list.get(i).getFecha().getYear()+1900;
			
			String fecha = dia + "-" + mes + "-" + an;
			 
			Date fech = formatter.parse(fecha);
			 
			nav = list.get(i).getNavegador().split(".0");

			Auditoria au = new Auditoria();
			au.setNavegador(nav[0]);
			au.setFecha(fech);
			au.setOrigen(list.get(i).getOrigen());
			au.setAccion(list.get(i).getAccion());
			au.setNombre(list.get(i).getNombre());

			listaTransformacion.add(au);

		}

		return listaTransformacion;

	}

}
