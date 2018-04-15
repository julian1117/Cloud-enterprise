package co.edu.eam.ingesoft.bi.cloud.web.controladores;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Genero;
import co.edu.eam.ingesoft.pa.negocio.beans.RegistroNuevosEJB;


@Named(value = "gestionNuevosController")
@ViewScoped
public class RegistroNuevosController implements Serializable {

	private Date fechaNacimiento;
	
	private List<Genero> listGeneros;
	
	private Genero genero;

	public List<Genero> getListGeneros() {
		return listGeneros;
	}


	
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}



	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}



	public Genero getGenero() {
		return genero;
	}



	public void setGenero(Genero genero) {
		this.genero = genero;
	}



	public void setListGeneros(List<Genero> listGeneros) {
		this.listGeneros = listGeneros;
	}



	@EJB
	private RegistroNuevosEJB registroNuevosEJB;
	
	

	@PostConstruct
	public void inicializar() {
		listGeneros = registroNuevosEJB.listaGeneros();
		System.out.println("---------------------------------------------------------------"
				+ ""+listGeneros.get(0));
	}
	
		
	
	

}
