package co.edu.eam.ingesoft.bi.cloud.web.convertidores;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Pais;
import co.edu.eam.ingesoft.bi.negocio.beans.General_EJB;

@FacesConverter(value = "paisConverter", forClass = Pais.class)
@Named("paisConverter")
public class ConvertidorPais implements Converter{

	@EJB
	private General_EJB generalEJB;
	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value == null || value.trim().length() == 0 || value.equals("Seleccione...")) {
			return null;
		}
		return generalEJB.buscarPais(Integer.parseInt(value));
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if(value instanceof Pais) {
			Pais pais = (Pais) value;
			return String.valueOf(pais.getIdPais());
		}
		return null;
	}

}
