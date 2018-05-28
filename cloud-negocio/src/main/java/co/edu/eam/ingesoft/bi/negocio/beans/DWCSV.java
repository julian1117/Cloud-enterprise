package co.edu.eam.ingesoft.bi.negocio.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades.DWauditoria;
import co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades.DWventa;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Auditoria;
import co.edu.eam.ingesoft.bi.negocio.conexion.Conexion;

@Stateless
@LocalBean
public class DWCSV {

	@EJB
	private Conexion em;
	
	public static List<DWauditoria> list;
	
	public static List<DWventa> listVenta;

	
	public List<DWauditoria> listAuditoriDW() {
		em.setBd(3);
		list = (List<DWauditoria>) (Object) em.listar(DWauditoria.TRAER);
		return list;
	}
	
	public List<DWventa> listVentaDW() {
		em.setBd(3);
		listVenta = (List<DWventa>) (Object) em.listar(DWventa.TRAER_VENTA);
		return listVenta;
	}
}
