package co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name="DW_USUARIO")
@NamedQuery(name = DWusuario.TRAER_USUARIOS, query = "SELECT A FROM DWusuario A where A.nombreUs=?1")
public class DWusuario implements Serializable{
	
	public static final String TRAER_USUARIOS = "DWauditoria.listDWUsu";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int codigoUs;
	
	@Column(name="nombre_us")
	private String nombreUs;

	public DWusuario() {
		super();
	}

	public DWusuario(int codigoUs, String nombreUs) {
		super();
		this.codigoUs = codigoUs;
		this.nombreUs = nombreUs;
	}

	public int getCodigoUs() {
		return codigoUs;
	}

	public void setCodigoUs(int codigoUs) {
		this.codigoUs = codigoUs;
	}

	public String getNombreUs() {
		return nombreUs;
	}

	public void setNombreUs(String nombreUs) {
		this.nombreUs = nombreUs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigoUs;
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
		DWusuario other = (DWusuario) obj;
		if (codigoUs != other.codigoUs)
			return false;
		return true;
	}	
	
}
