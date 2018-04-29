package co.edu.eam.ingesoft.bi.cloud.web.controladores;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Empleado;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Persona;

@Named(value = "gestionVentaController")
@ViewScoped
public class GestionVentaController implements Serializable {
	
	@Pattern(regexp = "[0-9]*", message = "El campo ID inventario solo puede llevar caracteres numericos")
	@Length(min = 4, max = 10, message = "Id Inventario - longitud entre 5 y 10")
	private String idFactura;

	private Date fecha;

	private String empleado;
	
	private String persona;
	
	

}
