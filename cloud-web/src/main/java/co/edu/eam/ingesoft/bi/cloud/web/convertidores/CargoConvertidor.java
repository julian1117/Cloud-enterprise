package co.edu.eam.ingesoft.bi.cloud.web.convertidores;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Cargo;
import co.edu.eam.ingesoft.bi.negocio.beans.RecursosHumanosEJB;

@FacesConverter(value = "cargoConverter", forClass = Cargo.class)
@Named("cargoConverter")
public class CargoConvertidor implements Converter{
	
	@EJB
	private RecursosHumanosEJB cargoEJB;
	
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value == null || value.trim().length() == 0 || value.equals("Seleccione...")) {
			return null;
		}
		return cargoEJB.buscarCargo(Integer.parseInt(value));
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if(value instanceof Cargo) {
			Cargo cargo = (Cargo) value;
			return String.valueOf(cargo.getId());
		}
		return null;
	}

}
