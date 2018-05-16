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

	// @Pattern(regexp = "[0-9]*", message = "El campo numero de identificacion solo
	// puede llevar caracteres numericos")
	// @Length(min = 4, max = 10, message = "Cedula - longitud entre 5 y 10")
	private String cedula;

	@Pattern(regexp = "[0-9]*", message = "El campo cantidad solo puede llevar caracteres numericos")
	private String cantidad;

	// @Pattern(regexp = "(?=-)*", message = "El campo fecha de ingreso solo puede
	// llevar caracteres numericos")
	private Date fechaIngreso;

	private String productoId;

	private List<Object> listarProducto;

	private List<Object> listaInventario;

	private String idInventariolista;

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

	public List<Object> getListarProducto() {
		return listarProducto;
	}

	public void setListarProducto(List<Object> listarProducto) {
		this.listarProducto = listarProducto;
	}

	public List<Object> getListaInventario() {
		return listaInventario;
	}

	public void setListaInventario(List<Object> listaInventario) {
		this.listaInventario = listaInventario;
	}

	public String getIdInventariolista() {
		return idInventariolista;
	}

	public void setIdInventariolista(String idInventariolista) {
		this.idInventariolista = idInventariolista;
	}

	@EJB
	private InventarioEJB inventarioEJB;

	@EJB
	private ProductoEJB productoEJB;

	@EJB
	private RecursosHumanosEJB recursosEJB;

	@EJB
	private AuditoriaEJB auditoriaEJB;

	@Inject
	private SessionController sesion;

	@PostConstruct
	public void inicializar() {
		listarProducto = inventarioEJB.listarProductos(sesion.getBd());
		listaInventario = inventarioEJB.listarInventario(sesion.getBd());
	}

	public void crearInventario() {
		try {
			String us = String.valueOf(sesion.getUse().getPersona().getCedula());
			Empleado persona = inventarioEJB.buscarEmpleado(Integer.parseInt(us), sesion.getBd());
			Producto producto = productoEJB.buscarProducto(Integer.parseInt(productoId), sesion.getBd());

			if (persona != null) {
				Inventario Inventario = new Inventario(Integer.parseInt(idInventario), Integer.parseInt(cantidad),
						fechaIngreso, producto, persona);

				inventarioEJB.crearInventario(Inventario, sesion.getBd());
				Messages.addFlashGlobalInfo("Registro Creado Con Exito!!");
				registrarAuditoria("CREAR", "NUEVO INVENTARIO");
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
		Inventario inv = inventarioEJB.buscarInventario(Integer.parseInt(idInventario), sesion.getBd());

		if (inv != null) {
			cedula = inv.getIdPersona().getIdPersona().getCedula().toString();
			cantidad = inv.getCantidad().toString();
			fechaIngreso = inv.getFechaIngreso();
			productoId = inv.getProducto().getIdProducto().toString();

			registrarAuditoria("BUSCAR", "fue buscado el inventario numero: " + inv.getIdInventario());
		} else {
			Messages.addFlashGlobalInfo("El Inventario no se encuentra registardo");

		}
	}

	public void editarInventario() {
		Inventario inv = inventarioEJB.buscarInventario(Integer.parseInt(idInventario), sesion.getBd());
		if (inv != null) {
			Empleado persona = inventarioEJB.buscarEmpleado(Integer.parseInt(cedula), sesion.getBd());
			Producto producto = productoEJB.buscarProducto(Integer.parseInt(productoId), sesion.getBd());

			Inventario inventario = new Inventario(Integer.parseInt(idInventario), Integer.parseInt(cantidad),
					fechaIngreso, producto, persona);
			inventarioEJB.editarInventario(inventario, sesion.getBd());
			Messages.addFlashGlobalInfo("Registro editado Con Exito!!");
			registrarAuditoria("EDITAR", "fue editado el inventario numero: " + inv.getIdInventario());

		}
	}

	public void eliminarInventario() {
		try {
			inventarioEJB.eliminarInventario(Integer.parseInt(idInventario), sesion.getBd());
			registrarAuditoria("ELIMINAR", "Elomino producto");
			Messages.addFlashGlobalInfo("Registro Eliminado Con Exito!!");

		} catch (Exception e) {
			Messages.addFlashGlobalError("lo sentimos no se puedo eliminar el registro");
		}
	}

	/**
	 * Metodo para registrar las auditorias generales
	 * 
	 * @param accion
	 *            Crear, Editar, Eliminar o Actualizar
	 * @param nombreReg
	 *            modulo que se esta trabajando
	 */
	public void registrarAuditoria(String accion, String nombreReg) {
		try {
			String browserDetails = Faces.getRequest().getHeader("User-Agent");
			// ----obtengo el usuario que esta en session
			String us = String.valueOf(sesion.getUse().getPersona().getCedula());

			// ----Mando usuario null por que aqui no hay session de usuario
			auditoriaEJB.crearAuditoria(accion, nombreReg, browserDetails, us, "N/A");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
