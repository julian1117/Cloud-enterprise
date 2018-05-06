package co.edu.eam.ingesoft.bi.cloud.persistencia.entidades;

import java.io.Serializable;

public class AccesoPK implements Serializable{

	private Integer usuario;

	private Integer paginas;

	public AccesoPK() {
		super();
	}

	public AccesoPK(Integer usuario, Integer paginas) {
		super();
		this.usuario = usuario;
		this.paginas = paginas;
	}

	public Integer getUsuario() {
		return usuario;
	}

	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}

	public Integer getPaginas() {
		return paginas;
	}

	public void setPaginas(Integer paginas) {
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
		AccesoPK other = (AccesoPK) obj;
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
