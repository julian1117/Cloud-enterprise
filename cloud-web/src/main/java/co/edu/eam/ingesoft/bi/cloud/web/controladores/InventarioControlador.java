package co.edu.eam.ingesoft.bi.cloud.web.controladores;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.AreaEmpresa;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Cargo;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Empleado;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Inventario;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Persona;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Producto;
import co.edu.eam.ingesoft.bi.negocio.beans.AuditoriaEJB;
import co.edu.eam.ingesoft.bi.negocio.beans.InventarioEJB;
import co.edu.eam.ingesoft.bi.negocio.beans.ProductoEJB;
import co.edu.eam.ingesoft.bi.negocio.beans.RecursosHumanosEJB;

@Named("inventarioControlador")
@ViewScoped
public class InventarioControlador implements Serializable {

	@Pattern(regexp = "[0-9]*", message = "El campo ID inventario solo puede llevar caracteres numericos")
	@Length(min = 4, max = 10, message = "Id Inventario - longitud entre 5 y 10")
	private String idInventario;

	@Pattern(regexp = "[0-9]*", message = "El campo numero de  identificacion solo puede llevar caracteres numericos")
	@Length(min = 4, max = 10, message = "Cedula - longitud entre 5 y 10")
	private String cedula;

	@Pattern(regexp = "[0-9]*", message = "El campo cantidad solo puede llevar caracteres numericos")
	private String cantidad;

	//@Pattern(regexp = "(?=-)*", message = "El campo fecha de ingreso solo puede llevar caracteres numericos")
	private Date fechaIngreso;

	private String productoId;

	private List<Producto> listarProducto;
	
	private List<Inventario> listaInventario;
	
	private String idInventariolista;

	@EJB
	private InventarioEJB inventarioEJB;

	@EJB
	private ProductoEJB productoEJB;

	@EJB
	private RecursosHumanosEJB recursosEJB;
	
	@EJB
	private AuditoriaEJB auditoriaEJB;
	
	
	

	public List<Inventario> getListaInventario() {
		return listaInventario;
	}

	public void setListaInventario(List<Inventario> listaInventario) {
		this.listaInventario = listaInventario;
	}

	public String getIdInventariolista() {
		return idInventariolista;
	}

	public void setIdInventariolista(String idInventariolista) {
		this.idInventariolista = idInventariolista;
	}

	public AuditoriaEJB getAuditoriaEJB() {
		return auditoriaEJB;
	}

	public void setAuditoriaEJB(AuditoriaEJB auditoriaEJB) {
		this.auditoriaEJB = auditoriaEJB;
	}

	public String getIdInventario() {
		return idInventario;
	}

	public void setIdInventario(String idInventario) {
		this.idInventario = idInventario;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	
	public String getProductoId() {
		return productoId;
	}

	public void setProductoId(String productoId) {
		this.productoId = productoId;
	}

	public RecursosHumanosEJB getRecursosEJB() {
		return recursosEJB;
	}

	public void setRecursosEJB(RecursosHumanosEJB recursosEJB) {
		this.recursosEJB = recursosEJB;
	}

	public List<Producto> getListarProducto() {
		return listarProducto;
	}

	public void setListarProducto(List<Producto> listarProducto) {
		this.listarProducto = listarProducto;
	}

	public InventarioEJB getInventarioEJB() {
		return inventarioEJB;
	}

	public void setInventarioEJB(InventarioEJB inventarioEJB) {
		this.inventarioEJB = inventarioEJB;
	}

	public ProductoEJB getProductoEJB() {
		return productoEJB;
	}

	public void setProductoEJB(ProductoEJB productoEJB) {
		this.productoEJB = productoEJB;
	}
	
	

	@PostConstruct
	public void inicializar() {
		listarProducto = inventarioEJB.listarProductos();
		listaInventario = inventarioEJB.listarInventario();
	}
	
	public void crearInventario() {
		try {
			Empleado persona = inventarioEJB.buscarEmpleado(Integer.parseInt(cedula));
			Producto producto = productoEJB.buscarProducto(Integer.parseInt(productoId));

			if (persona != null) {
				Inventario Inventario = new Inventario(Integer.parseInt(idInventario), Integer.parseInt(cantidad),
						fechaIngreso, producto, persona);

				inventarioEJB.crearInventario(Inventario);
				Messages.addFlashGlobalInfo("Registro Creado Con Exito!!");
			} else {
				Messages.addFlashGlobalInfo("Verifique que la persona exista!!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

		}
	}

	public void buscarInventario() {
		Inventario inv = inventarioEJB.buscarInventario(Integer.parseInt(idInventario));
		
		if(inv != null) {
			cedula = inv.getIdPersona().toString();
			cantidad= inv.getCantidad().toString();
			fechaIngreso= inv.getFechaIngreso();
			productoId = inv.getProducto().getIdProducto().toString();
		}else {
			Messages.addFlashGlobalInfo("El Inventario no se encuentra registardo");

		}
	}

	public void editarInventario() {
		Inventario inv = inventarioEJB.buscarInventario(Integer.parseInt(idInventario));
		if (inv != null) {
			Empleado persona = inventarioEJB.buscarEmpleado(Integer.parseInt(cedula));
			Producto producto = productoEJB.buscarProducto(Integer.parseInt(productoId));

			Inventario inventario = new Inventario(Integer.parseInt(idInventario), Integer.parseInt(cantidad), fechaIngreso, producto, persona);
			inventarioEJB.editarInventario(inventario);
			Messages.addFlashGlobalInfo("Registro editado Con Exito!!");

		}
	}

	public void eliminarInventario() {

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
			//String us = String.valueOf(sesion.getUse().getPersona().getCedula());
			
			//----Mando usuario null por que aqui no hay session de usuario
			auditoriaEJB.crearAuditoria(accion, nombreReg , browserDetails,"N/A","N/A");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
