package co.edu.eam.ingesoft.bi.cloud.web.convertidores;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Departamento;
import co.edu.eam.ingesoft.bi.negocio.beans.General_EJB;

/**
 * 
 * @author CAMILO
 *
 */
@FacesConverter(value = "departamentoConverter", forClass = Departamento.class)
@Named("departamentoConverter")
public class ConvertidorDepartamento implements Converter {

	@EJB
	private General_EJB generalEJB;

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value == null || value.trim().length() == 0 || value.equals("Seleccione...")) {
			return null;
		}
		return generalEJB.buscarDepartamento(Integer.parseInt(value));
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value instanceof Departamento) {
			Departamento departamento = (Departamento) value;
			return String.valueOf(departamento.getIdDepartamento());
		}
		return null;
	}

}
