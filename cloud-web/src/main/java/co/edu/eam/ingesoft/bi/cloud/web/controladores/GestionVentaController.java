package co.edu.eam.ingesoft.bi.cloud.web.controladores;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Empleado;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.GestionVenta;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Inventario;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Persona;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Venta;
import co.edu.eam.ingesoft.bi.negocio.beans.AuditoriaEJB;
import co.edu.eam.ingesoft.bi.negocio.beans.GestionarVentaEJB;
import co.edu.eam.ingesoft.bi.negocio.beans.InventarioEJB;
import co.edu.eam.ingesoft.bi.negocio.beans.RecursosHumanosEJB;
import co.edu.eam.ingesoft.bi.negocio.beans.RegistroNuevosEJB;
import co.edu.eam.ingesoft.bi.negocio.beans.VentaEJB;

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
	
	private List<Inventario> listaInventario;
	
	private String idInventario;

	private Integer cantidad;
	
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
	
	@EJB
	private InventarioEJB inventarioEJB;
	
	@EJB
	private VentaEJB ventaEJB;
	

	public List<Inventario> getListaInventario() {
		return listaInventario;
	}

	public void setListaInventario(List<Inventario> listaInventario) {
		this.listaInventario = listaInventario;
	}

	public String getIdInventario() {
		return idInventario;
	}

	public void setIdInventario(String idInventario) {
		this.idInventario = idInventario;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

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
	
	List<Venta> listVent;
	
	@PostConstruct
	public void inicializar() {
		listarFacturas = gestionEJB.listaFacturas();
		listaInventario = inventarioEJB.listarInventario();
		listVent = gestionEJB.listaVent();
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
		
		int a= Integer.parseInt(idInventario);
		int b = Integer.parseInt(idFacturaLista);
		int c = cantidad;
		
		Inventario buscaCan = inventarioEJB.buscarInventario(Integer.parseInt(idInventario));
		
		
		
		if(c <= buscaCan.getCantidad()) {
			ventaEJB.prueba(a, b, c);
			
			int resta = buscaCan.getCantidad() - c;
			
			if(buscaCan != null) {
				Inventario inventarioEditado = new Inventario(buscaCan.getIdInventario(), resta, buscaCan.getFechaIngreso(), buscaCan.getProducto(), buscaCan.getIdPersona());
				
				inventarioEJB.editarInventario(inventarioEditado);
				registrarAuditoria("EDITAR", "Edito la cantidad del producto");
			}else {
				Messages.addFlashGlobalInfo("error");
			}
			
			Messages.addFlashGlobalInfo("CREADO");
			
		}else {
			Messages.addFlashGlobalInfo("La cantidad de la venta es mayor a la que hay en el producto");
		}
		
		
		//Messages.addFlashGlobalInfo(ventaEJB.prueba(a, b, c));

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
