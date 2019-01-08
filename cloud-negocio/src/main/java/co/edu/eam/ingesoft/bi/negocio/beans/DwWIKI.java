package co.edu.eam.ingesoft.bi.negocio.beans;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.hibernate.type.descriptor.sql.VarbinaryTypeDescriptor;

import co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades.DWPaginaWIKI;
import co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades.DWventa;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Auditoria;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Venta;
import co.edu.eam.ingesoft.bi.cloud.persistencia.wiki.CambiosWIKI;
import co.edu.eam.ingesoft.bi.cloud.persistencia.wiki.PaginaWIKI;
import co.edu.eam.ingesoft.bi.cloud.persistencia.wiki.UsuarioWIKI;
import co.edu.eam.ingesoft.bi.negocio.conexion.Conexion;

/**
 * 
 * @author JULIAN CAMILO
 *
 */
@Stateless
@LocalBean
public class DwWIKI {

	@EJB
	private Conexion em;

	public List<Object[]> list;

	public static List<CambiosWIKI> listaDatosWiki;

	public static List<UsuarioWIKI> listaUsWiki;

	public static List<PaginaWIKI> listaPaginaWiki;

	// public

	/**
	 * Lista delos objetos a tratar para acumulacion
	 * 
	 * @return
	 */
	public List<CambiosWIKI> cargarWiki() {

		em.setBd(4);

		listaDatosWiki = (List<CambiosWIKI>) (Object) em.listar(CambiosWIKI.LISTA_CAMB_WIKI);
		listaUsWiki = (List<UsuarioWIKI>) (Object) em.listar(UsuarioWIKI.LISTA_WIKI);
		listaPaginaWiki = (List<PaginaWIKI>) (Object) em.listar(PaginaWIKI.LISTA_PAG_WIKI);

		return listaDatosWiki;

	}

	/**
	 * Se transforman los datos de la wiki para el dw y se envian de una vez
	 */
	public void transformacionWIKI() {

		em.consultaNativa("DELETE FROM cloud.dwpaginawiki WHERE id_pagina between 1 and 1000000000;");
		em.consultaNativa("DELETE FROM cloud.page_prin_wiki WHERE id_pagina_prin between 1 and 1000000000;");
		em.consultaNativa("DELETE FROM cloud.dwuscambioswiki WHERE id_user between 1 and 1000000000;");

		// Insert tabla cloud.DWUsCambiosWIKI
		for (int j = 0; j < listaUsWiki.size(); j++) {

			String nombre = "";
			String correo = "";

			if (!listaUsWiki.get(j).getNombreReal().equalsIgnoreCase("")) {
				nombre = listaUsWiki.get(j).getNombreReal();
			} else {
				nombre = "No hay nombre registrado";
			}

			if (!listaUsWiki.get(j).getEmailUs().equalsIgnoreCase("")) {
				correo = listaUsWiki.get(j).getEmailUs();
			} else {
				correo = "No hay correo registrado";
			}

			em.consultaNativa(
					"INSERT INTO cloud.dwuscambioswiki (id_user, nombre_us, nombre_real, email, cantidad_cambios) VALUES ("
							+ listaUsWiki.get(j).getCodigoUser() + ",'" + listaUsWiki.get(j).getNombre() + "','"
							+ nombre + "','" + correo + "'," + listaUsWiki.get(j).getCantidadCambios() + ");");
		}
		// Insert tabala page_prin_wiki
		for (int k = 0; k < listaPaginaWiki.size(); k++) {

			String redirect = "";

			// if (listaDatosWiki.get(k).isEsNueva()) { permit redirigir la pagina
			if (listaPaginaWiki.get(k).isRedirige()) {
				redirect = "SI";
			} else {
				redirect = "NO";
			}

			em.consultaNativa("INSERT INTO cloud.page_prin_wiki "
					+ "(id_pagina_prin,page_random,page_is_redirect) VALUES (" + listaPaginaWiki.get(k).getIdPagina()
					+ "," + listaPaginaWiki.get(k).getTamanoPagina() + ",'" + redirect + "');");
		}

		// Insert tabla cloud.DWPaginaWIKI
		for (int i = 0; i < listaDatosWiki.size(); i++) {

			String esNueva = "";
			String tipoPagina = "";
			String comentario = "";
			int usuario = 0;
			int pagina = 0;

			if (listaDatosWiki.get(i).isEsNueva()) {
				esNueva = "SI";
			} else {
				esNueva = "NO";
			}

			if (listaDatosWiki.get(i).getTipoPagina() == 3) {
				tipoPagina = "Pagina de imagen";
			} else {
				tipoPagina = "Pagina de texto";
			}

			if (!listaDatosWiki.get(i).getTexto().equalsIgnoreCase("")) {
				comentario = listaDatosWiki.get(i).getTexto();
			} else {
				comentario = "No hay comentarios regsitrados";
			}

			for (int m = 0; m < listaPaginaWiki.size(); m++) {

				if (listaDatosWiki.get(i).getTitulo().equals(listaPaginaWiki.get(m).getTitulo())) {
					
					pagina = listaPaginaWiki.get(m).getIdPagina();

					em.consultaNativa("INSERT INTO cloud.dwpaginawiki "
							+ "(titulo, fecha_modificacion, comentario, ip_edicion, nueva, Tipo_pagina,cambiosPaginaWiki,PaginaPrinWiki) "
							+ "VALUES ('" + listaDatosWiki.get(i).getTitulo() + "','"
							+ listaDatosWiki.get(i).getFechaModificacion() + "','" + comentario + "','"
							+ listaDatosWiki.get(i).getIp() + "','" + esNueva + "','" + tipoPagina + "',"
							+ listaDatosWiki.get(i).getIdUser() + "," + pagina + ");");
				}
			}

		}

	}

	public List<DWPaginaWIKI> cargarWikiDW() {
		em.setBd(3);
		return (List<DWPaginaWIKI>) (Object) em.listar(DWPaginaWIKI.TRAER_WIKI);
	}

}
