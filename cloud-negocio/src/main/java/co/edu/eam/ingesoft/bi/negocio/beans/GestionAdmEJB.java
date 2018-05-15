package co.edu.eam.ingesoft.bi.negocio.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Acceso;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.AccesoPK;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.AreaEmpresa;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Empresa;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Paginas;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Persona;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.TipoUsuario;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.bi.negocio.conexion.Conexion;
import co.edu.eam.ingesoft.bi.negocio.excepciones.ExcepcionNegocio;

@Stateless
@LocalBean
public class GestionAdmEJB {

	@EJB
	private Conexion em;

	/**
	 * Listado de usuarios inactivos
	 * 
	 * @return listado de usuarios
	 */
	public List<Object> listaUsuarioI( int bd) {
		em.setBd(bd);
		return em.listar(Usuario.USUARIO_I);
	}

	/**
	 * Buscar usuario por id
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Usuario buscarUsuario(Integer idUsuario,int bd) {
		em.setBd(bd);
		return (Usuario) em.buscar(Usuario.class, idUsuario);
	}

	/**
	 * Actualizacion de estado del usuario
	 * 
	 * @param usuario
	 */
	public void ActivarUsario(Usuario usuario, int bd) {
		Usuario us = buscarUsuario(usuario.getCodigo(),bd);
		if (us != null) {
			us.setEstado(true);
			em.setBd(bd);
			em.editar(us);
		} else {
			throw new ExcepcionNegocio(
					"No fue posible realizar el cambio de estado del usuario: " + usuario.getNombre());
		}
	}

	/**
	 * Actualizacion de estado del usuario a inactivo
	 * 
	 * @param usuario
	 */
	public void inactivarUsario(Usuario usuario, int bd) {
		Usuario us = buscarUsuario(usuario.getCodigo(), bd);
		if (us != null) {
			us.setEstado(false);
			em.setBd(bd);
			em.editar(us);
		} else {
			throw new ExcepcionNegocio(
					"No fue posible realizar el cambio de estado del usuario: " + usuario.getNombre());
		}
	}

	/**
	 * Crear tipo de usuario
	 * 
	 * @param tipoUsuario
	 */
	public void crearTipoUs(TipoUsuario tipoUsuario, int bd) {
		em.setBd(bd);
		em.crear(tipoUsuario);
	}

	/**
	 * Crear area de la empresa
	 * 
	 * @param areaEmpresa
	 */
	public void crearAreaEmpresa(AreaEmpresa areaEmpresa,int bd) {
		em.setBd(bd);
		em.crear(areaEmpresa);
	}

	/**
	 * Lista de tipos de usuario
	 * 
	 * @param tipoUsuario
	 * @return
	 */
	public List<Object> listaTipoUs( int bd) {
		em.setBd(bd);
		return em.listar(TipoUsuario.TIPO_IS);
	}

	/**
	 * Buscar por tipo de usuario
	 * 
	 * @param idTipo
	 * @return
	 */
	public TipoUsuario buscarTipoUs(Integer idTipo, int bd) {
		em.setBd(bd);
		return (TipoUsuario) em.buscar(TipoUsuario.class, idTipo);
	}

	/**
	 * Buscar area de la empresa
	 * 
	 * @param idArea
	 * @return
	 */
	public AreaEmpresa buscarAreaEmpresa(Integer idArea, int bd) {
		em.setBd(bd);
		return (AreaEmpresa) em.buscar(AreaEmpresa.class, idArea);
	}

	/**
	 * Editar tipo de usuario
	 * 
	 * @param tipoUsuario
	 */
	public void editarTipoUs(TipoUsuario tipoUsuario, int bd) {
		em.setBd(bd);
		em.editar(tipoUsuario);
	}

	/**
	 * Editar area de la empresa
	 * 
	 * @param areaEmpresa
	 */
	public void editarAreaEmpresa(AreaEmpresa areaEmpresa, int bd) {
		em.setBd(bd);
		em.editar(areaEmpresa);
	}

	/**
	 * Eliminar tipo de usuario
	 * 
	 * @param tipoUsuario
	 */
	public void eliminarTipoUs(Integer tipoUsuario, int bd) {
		try {
			TipoUsuario tipo = buscarTipoUs(tipoUsuario, bd);
			if (tipo != null) {
				em.setBd(bd);
				em.eliminar(tipo);
			}
		} catch (ExcepcionNegocio e) {
			throw new ExcepcionNegocio("No fue posible eliminar el tipo de usuario ");
		}
	}

	/**
	 * Eliminar area de la empresa
	 * 
	 * @param tipoUsuario
	 */
	public void eliminarAreaEmpresa(Integer areaEmpresa, int bd) {
		try {
			AreaEmpresa area = buscarAreaEmpresa(areaEmpresa, bd);
			if (area != null) {
				em.setBd(bd);
				em.eliminar(areaEmpresa);
			}
		} catch (

		ExcepcionNegocio e) {
			throw new ExcepcionNegocio("No fue posible eliminar el area de la empresa ");
		}
	}

	/**
	 * Lista de usuarios activos en el sistema
	 * 
	 * @return
	 */
	public List<Object> listaUsuariosAct(int bd) {
		em.setBd(bd);
		return em.listar(Usuario.USUARIOS_ACT);
	}

	/**
	 * Lista de paginas del sistema
	 * 
	 * @return
	 */
	public List<Object> listaPaginas(int bd) {
		em.setBd(bd);
		return em.listar(Paginas.LIST_PAGINAS);
	}

	/**
	 * Buscar pagina por idpagina
	 * 
	 * @param idPagina
	 */
	public Paginas buscarPagina(Integer idPagina, int bd) {
		em.setBd(bd);
		return (Paginas) em.buscar(Paginas.class, idPagina);
	}

	/**
	 * Crear accesos
	 * @param acceso
	 */
	public void crearAcceso(Integer uss,Integer pagg, int bd) {
		
		em.setBd(bd);
		
		//busco en el ejb
		Usuario us = buscarUsuario(uss, bd);
		Paginas pag = buscarPagina(pagg, bd);
		
		//creo el obj
		Acceso acc = new Acceso();
		acc.setUsuario(us);
		acc.setPaginas(pag);
		
		//BUsco si la relacion ya existe
		Acceso buscarAc = buscarAccesos(acc.getUsuario().getCodigo(), acc.getPaginas().getIdPagina(), bd);
		if (buscarAc == null) {
			//creo el objeto si no existe
			em.crear(acc);
		} else {
			throw new ExcepcionNegocio("No fue posible realizar el registro ");
		}
		
	}

	/**
	 * buscar accesos
	 * @param us
	 * @param pag
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Acceso buscarAccesos(Integer us, Integer pag, int bd) {
		em.setBd(bd);
		AccesoPK accesoPK = new AccesoPK(us, pag);

		return (Acceso) em.buscar(Acceso.class, accesoPK);
	}
	
	/**
	 * lista de accesos por usuario
	 * @return
	 */
	public List<Object> listaAcceso(Integer codUser,int bd){
		return em.listarConParametroInteger(Acceso.LISTA_ACCESO_US, codUser);
	}
	
	public void cambiarBDOra(int empresa) {	
		em.setBd(1);
		Empresa emp = buscarEmpresa(1);
		emp.setBd(empresa);
		em.editar(emp);		
	}
	
	public void cambiarBDPost(int empresa) {	
		em.setBd(2);
		Empresa emp = buscarEmpresa(1);
		emp.setBd(empresa);
		em.editar(emp);		
	}
	
	public Empresa buscarEmpresa(int pk) {
		em.setBd(1);
		return (Empresa) em.buscar(Empresa.class, pk);
	}
	
	/**
	 * Cambia el valor de la base de datos
	 * @param empresa
	 */
	public void cambiarValorBD(int empresa) {	
		cambiarBDPost(empresa);
		cambiarBDOra(empresa);
		
	}
}
