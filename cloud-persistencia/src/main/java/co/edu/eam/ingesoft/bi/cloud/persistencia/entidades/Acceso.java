package co.edu.eam.ingesoft.bi.cloud.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@IdClass(AccesoPK.class)
@Entity
@Table(name="Acceso")
@NamedQuery(name = Acceso.LISTA_ACCESO, query = "SELECT a FROM Acceso a where a.usuario.nombre=?1")
public class Acceso implements Serializable{
	
	public static final String LISTA_ACCESO = "Acceso.listaAcc";

	
	@Id
	@ManyToOne
	@JoinColumn(name="USUARIO")
	private Usuario usuario;
	
	@Id
	@ManyToOne
	@JoinColumn(name="PAGINA")
	private Paginas paginas;
	
	public Acceso() {
		super();
	}

	public Acceso(Usuario usuario, Paginas paginas) {
		super();
		this.usuario = usuario;
		this.paginas = paginas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Paginas getPaginas() {
		return paginas;
	}

	public void setPaginas(Paginas paginas) {
		this.paginas = paginas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((paginas == null) ? 0 : paginas.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Acceso other = (Acceso) obj;
		if (paginas == null) {
			if (other.paginas != null)
				return false;
		} else if (!paginas.equals(other.paginas))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
	
	

}
