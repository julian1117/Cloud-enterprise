package co.edu.eam.ingesoft.bi.cloud.web.convertidores;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Genero;
import co.edu.eam.ingesoft.bi.negocio.beans.RegistroNuevosEJB;

@FacesConverter(value = "sexoConverter", forClass = Genero.class)
@Named("sexoConverter")
public class SexoConverter implements Converter {

	@EJB
	private RegistroNuevosEJB registriNuevosEJB;

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value == null || value.trim().length() == 0 || value.equals("Seleccione...")) {
			return null;
		}
		return registriNuevosEJB.buscarGenro(Integer.parseInt(value));
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value instanceof Genero) {
			Genero genero = (Genero) value;
			return String.valueOf(genero.getId());
		}
		return null;
	}

}
