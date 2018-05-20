package co.edu.eam.ingesoft.bi.negocio.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Venta;
import co.edu.eam.ingesoft.bi.negocio.conexion.Conexion;

@Stateless
@LocalBean
public class DWVentaEJB {	

	@EJB
	private Conexion em;
	
	public List<Venta> listaVenta;
	
	public List<Venta> cargarDWventa(){
		em.setBd(1);
		return (List<Venta>) (Object) em.listar(Venta.LISTA_VENT);
	}

}
