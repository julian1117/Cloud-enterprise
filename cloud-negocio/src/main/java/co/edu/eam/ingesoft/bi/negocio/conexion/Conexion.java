package co.edu.eam.ingesoft.bi.negocio.conexion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades.DWauditoria;
import co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades.DWventa;
import co.edu.eam.ingesoft.bi.negocio.excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
public class Conexion implements Serializable {

	/**
	 * Instancia a Oracle (1)
	 */
	@PersistenceContext(unitName = "oracle")
	private EntityManager emO;

	/**
	 * Instancia a postgress (2)
	 */
	@PersistenceContext(unitName = "postgres")
	private EntityManager emP;

	/**
	 * Instancia a mysql (3)
	 */
	@PersistenceContext(unitName = "mysql")
	private EntityManager emM;

	/**
	 * Instancia a mysql WIKI WIKI (4)
	 */
	@PersistenceContext(unitName = "mysql_Wiki")
	private EntityManager emWIKI;

	/**
	 * Es la base de dato en la cual esta funcionando el sistema actualmente
	 */
	private int bd;

	/**
	 * Guarda en la base de datos
	 */
	public void crear(Object objeto) {
		switch (this.bd) {
		case 1:
			emO.persist(objeto);
			break;
		case 2:
			emP.persist(objeto);
			break;
		default:
			throw new ExcepcionNegocio("La base de datos #" + this.bd + " no existe.");
		}
	}

	/**
	 * Edita en la base de datos
	 */
	public void editar(Object objeto) {
		switch (this.bd) {
		case 1:
			emO.merge(objeto);
			break;
		case 2:
			emP.merge(objeto);
			break;
		default:
			throw new ExcepcionNegocio("La base de datos #" + this.bd + " no existe.");
		}
	}

	/**
	 * Elimina de la base de datos
	 */
	public void eliminar(Object objeto) {
		switch (this.bd) {
		case 1:
			emO.remove(objeto);
			break;
		case 2:
			emP.remove(objeto);
			break;
		default:
			throw new ExcepcionNegocio("La base de datos #" + this.bd + " no existe.");
		}
	}

	/**
	 * Busca en una base de datos determinada
	 * 
	 * @param objeto
	 *            el tipo de objeto a buscar
	 * @param pk
	 *            el identificador del registro a buscar
	 * @return retorna el registro encontrado, de lo contrario null
	 */
	public Object buscar(Class type, Object pk) {
		switch (this.bd) {
		case 1:
			return emO.find(type, pk);
		case 2:
			return emP.find(type, pk);
		default:
			throw new ExcepcionNegocio("La base de datos #" + this.bd + " no existe.");
		}
	}

	/**
	 * Metodo que permite buscar en alguna base de datos por un parametro de tipo
	 * Integer
	 * 
	 * @param parametro
	 *            el parametro por ql cual se desea busacar
	 * @return el objeto que se desea buscar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Object buscarXParametro(String sql) {
		switch (this.bd) {
		case 1:
			Query q = emO.createNamedQuery(sql);
			q.executeUpdate();
			return q;
		case 2:
			Query p = emP.createNamedQuery(sql);
			p.executeUpdate();
			return p;
		default:
			throw new ExcepcionNegocio("La base de datos #" + this.bd + " no existe.");
		}
	}

	/**
	 * Listar objetos
	 * 
	 * @param sql
	 *            consulta a ejecutar, nos traera objetos de una determinada tabla
	 * @return lista de los objetos encontrados
	 */
	// @TransactionAttribute
	public List<Object> listar(String sql) {
		switch (this.bd) {
		case 1:
			Query q = emO.createNamedQuery(sql);
			return q.getResultList();
		case 2:
			Query p = emP.createNamedQuery(sql);
			return p.getResultList();
		case 3:
			Query x = emM.createNamedQuery(sql);
			return x.getResultList();
		case 4:
			Query w = emWIKI.createNamedQuery(sql);
			return w.getResultList();
		default:
			throw new ExcepcionNegocio("La base de datos #" + this.bd + " no existe.");
		}
	}

	/**
	 * Listar objetos usando un parametro
	 * 
	 * @param sql
	 *            consulta a ejecutar, nos traera objetos de una determinada tabla
	 * @parametro el parametro necesario para la consulta
	 * @return lista de los objetos encontrados
	 */
	public List<Object> listarConParametroInteger(String sql, Object parametro) {
		switch (this.bd) {
		case 1:
			Query q = emO.createNamedQuery(sql);
			q.setParameter(1, parametro);
			return q.getResultList();
		case 2:
			Query p = emP.createNamedQuery(sql);
			p.setParameter(1, parametro);
			return p.getResultList();
		default:
			throw new ExcepcionNegocio("La base de datos #" + this.bd + " no existe.");
		}
	}

	/**
	 * Listar objetos usando un parametro String
	 * 
	 * @param sql
	 *            consulta a ejecutar, nos traera objetos de una determinada tabla
	 * @parametro el parametro necesario para la consulta
	 * @return lista de los objetos encontrados
	 */
	public List<Object> listarConParametroString(String sql, String parametro) {
		switch (this.bd) {
		case 1:
			Query q = emO.createNamedQuery(sql);
			q.setParameter(1, parametro);
			return q.getResultList();
		case 2:
			Query p = emP.createNamedQuery(sql);
			p.setParameter(1, parametro);
			return p.getResultList();
		case 3:
			Query m = emM.createNamedQuery(sql);
			m.setParameter(1, parametro);
			return m.getResultList();
		default:
			throw new ExcepcionNegocio("La base de datos #" + this.bd + " no existe.");
		}
	}

	/**
	 * Listar objetos usando un parametro String
	 * 
	 * @param sql
	 *            consulta a ejecutar, nos traera objetos de una determinada tabla
	 * @parametro el parametro necesario para la consulta
	 * @return lista de los objetos encontrados
	 */
	public List<Object> listarConDosParametros(String sql, Date parametro, Date paramettro2) {
		switch (this.bd) {
		case 1:
			Query q = emO.createNamedQuery(sql);
			q.setParameter(1, parametro);
			// q.setParameter(2, paramettro2);
			return q.getResultList();
		case 2:
			Query p = emP.createNamedQuery(sql);
			p.setParameter(1, parametro);
			// p.setParameter(2, paramettro2);
			return p.getResultList();
		default:
			throw new ExcepcionNegocio("La base de datos #" + this.bd + " no existe.");
		}
	}

	/**
	 * Listar objetos de una tabla usando las 2 bases de datos
	 * 
	 * @param sql
	 *            consulta a ejecutar, nos traera objetos de una determinada tabla
	 * @return lista de los objetos encontrados
	 */
	public List<Object> listarCon2BasesDatos(String sql) {
		try {
			Query q = emO.createNamedQuery(sql);
			Query p = emP.createNamedQuery(sql);
			List<Object> lista = new ArrayList<Object>(q.getResultList());
			if (lista.addAll(p.getResultList())) {
				return lista;
			} else {
				throw new ExcepcionNegocio("No se pudo unir los dos listados de las bases de datos");
			}
		} catch (Exception ex) {
			throw new ExcepcionNegocio(ex.getMessage());
		}
	}

	/**
	 * Accesores Y Modificadores
	 */

	public int getBd() {
		return bd;
	}

	public void setBd(int bd) {
		this.bd = bd;
	}

	/**
	 * Guarda en la base de datos
	 */
	public void crearDWAuditoria(DWauditoria objeto) {

		emM.persist(objeto);

	}

	public void editarDWVenta(DWventa objeto) {
		// if(this.bd==3) {
		emM.persist(objeto);
		// }
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void consultaNativa(String Consulta) {

		Query q = emM.createNativeQuery(Consulta);
		q.executeUpdate();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void consultaNativaConBD(String Consulta) {

		switch (this.bd) {
		case 1:
			Query q = emO.createNativeQuery(Consulta);
			q.executeUpdate();
		case 2:
			Query p = emP.createNativeQuery(Consulta);
			p.executeUpdate();
		default:
			throw new ExcepcionNegocio("La base de datos #" + this.bd + " no existe.");
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Object[]> traerDatosWiki(String Consulta) {
		switch (this.bd) {
		case 4:
			List<Object[]> q = emWIKI.createNativeQuery(Consulta).getResultList();
			//System.out.println(q.get(0).g);
			return q;
		}
		return null;
	}

}
