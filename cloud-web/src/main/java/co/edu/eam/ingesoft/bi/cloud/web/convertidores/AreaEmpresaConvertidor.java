package co.edu.eam.ingesoft.bi.cloud.web.convertidores;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.AreaEmpresa;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Pais;
import co.edu.eam.ingesoft.bi.negocio.beans.AreaEmpresaEJB;


@FacesConverter(value = "areaConvertidor", forClass = AreaEmpresa.class)
@Named("areaConvertidor")
public class AreaEmpresaConvertidor implements Serializable{
	
	@EJB
	private AreaEmpresaEJB areaEJB;
	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value == null || value.trim().length() == 0 || value.equals("Seleccione...")) {
			return null;
		}
		return areaEJB.buscarArea(Integer.parseInt(value));
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if(value instanceof AreaEmpresa) {
			AreaEmpresa areaEmpresa = (AreaEmpresa) value;
			return String.valueOf(areaEmpresa.getId());
		}
		return null;
	}

}
