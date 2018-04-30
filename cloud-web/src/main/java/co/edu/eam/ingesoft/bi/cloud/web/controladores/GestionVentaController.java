package co.edu.eam.ingesoft.bi.cloud.web.controladores;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Empleado;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.GestionVenta;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Persona;
import co.edu.eam.ingesoft.bi.negocio.beans.AuditoriaEJB;
import co.edu.eam.ingesoft.bi.negocio.beans.GestionarVentaEJB;
import co.edu.eam.ingesoft.bi.negocio.beans.RecursosHumanosEJB;
import co.edu.eam.ingesoft.bi.negocio.beans.RegistroNuevosEJB;

@Named(value = "gestionVentaController")
@ViewScoped
public class GestionVentaController implements Serializable {
	
	@Pattern(regexp = "[0-9]*", message = "El campo ID inventario solo puede llevar caracteres numericos")
	@Length(min = 4, max = 10, message = "Id Inventario - longitud entre 5 y 10")
	private String idFactura;

	private Date fecha;

	private String empleado;
	
	private String persona;
	
	private List<GestionVenta> listarFacturas;
	
	private String idFacturaLista;
	
	@EJB
	private GestionarVentaEJB gestionEJB;
	
	@EJB
	private RegistroNuevosEJB registroEJB;
	
	@EJB
	private RecursosHumanosEJB recursosEJB;
	
	@EJB
	private AuditoriaEJB auditoriaEJB;
	
	@Inject
	private SessionController sesion;	
	
	
	

	public List<GestionVenta> getListarFacturas() {
		return listarFacturas;
	}

	public void setListarFacturas(List<GestionVenta> listarFacturas) {
		this.listarFacturas = listarFacturas;
	}

	public String getIdFacturaLista() {
		return idFacturaLista;
	}

	public void setIdFacturaLista(String idFacturaLista) {
		this.idFacturaLista = idFacturaLista;
	}

	public GestionarVentaEJB getGestionEJB() {
		return gestionEJB;
	}

	public void setGestionEJB(GestionarVentaEJB gestionEJB) {
		this.gestionEJB = gestionEJB;
	}

	public RecursosHumanosEJB getRecursosEJB() {
		return recursosEJB;
	}

	public void setRecursosEJB(RecursosHumanosEJB recursosEJB) {
		this.recursosEJB = recursosEJB;
	}

	public AuditoriaEJB getAuditoriaEJB() {
		return auditoriaEJB;
	}

	public void setAuditoriaEJB(AuditoriaEJB auditoriaEJB) {
		this.auditoriaEJB = auditoriaEJB;
	}

	public SessionController getSesion() {
		return sesion;
	}

	public void setSesion(SessionController sesion) {
		this.sesion = sesion;
	}

	public String getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(String idFactura) {
		this.idFactura = idFactura;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEmpleado() {
		return empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	public String getPersona() {
		return persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}

	public RegistroNuevosEJB getRegistroEJB() {
		return registroEJB;
	}

	public void setRegistroEJB(RegistroNuevosEJB registroEJB) {
		this.registroEJB = registroEJB;
	}
	
	@PostConstruct
	public void inicializar() {
		listarFacturas = gestionEJB.listaFacturas();
	}
	
	public void crearFactura() {
		try {
			String us = String.valueOf(sesion.getUse().getPersona().getCedula());
			Empleado em = recursosEJB.buscarEmp(Integer.parseInt(us));
			Persona per = registroEJB.buscarPersona(Integer.parseInt(persona));
			
			if(us != null && per !=null) {
				GestionVenta gestionFactura = new GestionVenta(Integer.parseInt(idFactura), fecha, em , per);
				gestionEJB.crearGestionVenta(gestionFactura);
				Messages.addFlashGlobalInfo("Registro Creado Con Exito!!");
				
			}
			
			
		} catch (Exception e) {
			
		}
		
		
	}
	
	public void buscarFactura() {
		
	}
	
	public void crearVenta() {
		
	}
	
	/**
	 * Metodo para  registrar las auditorias generales
	 * @param accion Crear, Editar, Eliminar o Actualizar
	 * @param nombreReg modulo que se esta trabajando
	 */
	public void registrarAuditoria(String accion, String nombreReg) {
		try {
			String browserDetails = Faces.getRequest().getHeader("User-Agent");
			//----obtengo el usuario que esta en session
			String us = String.valueOf(sesion.getUse().getPersona().getCedula());
			
			//----Mando usuario null por que aqui no hay session de usuario
			auditoriaEJB.crearAuditoria(accion, nombreReg , browserDetails,"N/A","N/A");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
