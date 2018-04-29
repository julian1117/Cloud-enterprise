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

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Producto;
import co.edu.eam.ingesoft.bi.negocio.beans.AuditoriaEJB;
import co.edu.eam.ingesoft.bi.negocio.beans.InventarioEJB;
import co.edu.eam.ingesoft.bi.negocio.beans.ProductoEJB;

@Named("productoControlador")
@ViewScoped
public class ProductoController implements Serializable {

	@Pattern(regexp = "[0-9]*", message = "Solo numeros")
	@Length(min = 5, max = 10, message = "Campo id -Longitud de 5 a 10")
	private String id;

	@Pattern(regexp = "[A-Za-z ]*", message = "nombre solo permites caracteres alfabetico")
	@Length(min = 5, max = 50, message = "Nombre - longitud entre 5 y 20")
	private String nombre;

	private Date fechaIngreso;

	private String descirpcion;

	private String cantidad;

	private Integer codigoLote;

	private String peso;

	private String dimensiones;

	private double valor;

	private List<Producto> listaProducto;

	@EJB
	private ProductoEJB productoEJB;

	@EJB
	private AuditoriaEJB auditoriaEJB;
	
	@EJB
	private InventarioEJB inventarioEJB;
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getDescirpcion() {
		return descirpcion;
	}

	public void setDescirpcion(String descirpcion) {
		this.descirpcion = descirpcion;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getCodigoLote() {
		return codigoLote;
	}

	public void setCodigoLote(Integer codigoLote) {
		this.codigoLote = codigoLote;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getDimensiones() {
		return dimensiones;
	}

	public void setDimensiones(String dimensiones) {
		this.dimensiones = dimensiones;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public List<Producto> getListaProducto() {
		return listaProducto;
	}

	public void setListaProducto(List<Producto> listaProducto) {
		this.listaProducto = listaProducto;
	}

	public ProductoEJB getProductoEJB() {
		return productoEJB;
	}

	public void setProductoEJB(ProductoEJB productoEJB) {
		this.productoEJB = productoEJB;
	}
	
	@PostConstruct
	public void inicializar() {
	listaProducto = inventarioEJB.listarProductos();
	}

	public void crearProducto() {
		try {
			Producto producto = new Producto(Integer.parseInt(id), nombre, descirpcion, codigoLote, peso, dimensiones, valor);
			productoEJB.crearProducto(producto);
			Messages.addFlashGlobalInfo("Registro Creado Con Exito!!");

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

		}
	}

	public void buscarProducto() {
		Producto pro = productoEJB.buscarProducto(Integer.parseInt(id));
		if (pro != null) {
			nombre = pro.getNombre();
			descirpcion = pro.getDescirpcion();	
			codigoLote= pro.getCodigoLote();
			peso = pro.getPeso();
			dimensiones = pro.getDimensiones();
			valor = pro.getValor();
		} else {
			Messages.addFlashGlobalInfo("El producto no se encuentra registardo");

		}
	}
	
	public void editarProducto() {
		Producto pro = productoEJB.buscarProducto(Integer.parseInt(id));
		if (pro != null) {
			Producto producto = new Producto(Integer.parseInt(id), nombre, descirpcion, codigoLote, peso, dimensiones, valor);
			productoEJB.editar(producto);
			Messages.addFlashGlobalInfo("Registro editado Con Exito!!");

		}
	}
	
	public void eliminarProdducto() {
		Producto pro = productoEJB.buscarProducto(Integer.parseInt(id));
		if (pro != null) {
			productoEJB.eliminarProducto(pro);
			Messages.addFlashGlobalInfo("Producto eliminado Con Exito!!");
		}else {
			Messages.addFlashGlobalInfo("El producto no se encuentra registardo");

		}
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
			auditoriaEJB.crearAuditoria(accion, nombreReg , browserDetails,"N/A");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
