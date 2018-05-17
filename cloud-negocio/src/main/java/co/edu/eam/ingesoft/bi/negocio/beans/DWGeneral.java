package co.edu.eam.ingesoft.bi.negocio.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.edu.eam.ingesoft.bi.cloud.persistencia.dwentidades.DWauditoria;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.Auditoria;
import co.edu.eam.ingesoft.bi.cloud.persistencia.entidades.TipoUsuario;
import co.edu.eam.ingesoft.bi.negocio.conexion.Conexion;

@Stateless
@LocalBean
public class DWGeneral {

	@EJB
	private Conexion em;

	/**
	 * Lista d elos objetos a tratar
	 * @return
	 */
	public List<Auditoria> cargarDWAuditoria() {
		em.setBd(1);
		return (List<Auditoria>)(Object)em.listar(Auditoria.AUDITORIA);
		
		/**for (int i = 0; i < listAuditoria.size(); i++) {
			
			System.out.println("-------------------------------------------------------------------");
		System.out.println(listAuditoria.size()+" ---------------"+listAuditoria.get(0).getAccion());
			DWauditoria dWauditoria = new DWauditoria();
			dWauditoria.setNombre(listAuditoria.get(i).getNombre());
			dWauditoria.setFecha(listAuditoria.get(i).getFecha());
			
			//em.setBd(3);
			em.editarDW(DWauditoria.class);
		}
		*/
	}

}
