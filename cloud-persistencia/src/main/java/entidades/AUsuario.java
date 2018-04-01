package entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="AUsuario")
public class AUsuario implements Serializable {

	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@Column(name="ingreso")
	private boolean ingreso;
	
	@ManyToOne
	@JoinColumn(name="Usuario_id")
	private Usuario usuario;

	public AUsuario() {
		super();
	}

	public AUsuario(Integer id, Date fecha, boolean ingreso, Usuario usuario) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.ingreso = ingreso;
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public boolean isIngreso() {
		return ingreso;
	}

	public void setIngreso(boolean ingreso) {
		this.ingreso = ingreso;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
