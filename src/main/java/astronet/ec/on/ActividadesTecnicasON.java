package astronet.ec.on;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import astronet.ec.dao.ActividadesTecnicasDAO;
import astronet.ec.dao.RolDAO;
import astronet.ec.modelo.ActividadesTecnicas;
import astronet.ec.modelo.Equipo;
import astronet.ec.modelo.EquipoServicio;
import astronet.ec.modelo.RolEmpleado;

@Stateless // estable una peticion independiente 
public class ActividadesTecnicasON {
	@Inject
	private ActividadesTecnicasDAO act_tec_dao;
	
	public void guardar(ActividadesTecnicas actTecnicas) {
		act_tec_dao.save(actTecnicas);
	}
	
	public ActividadesTecnicas read(int id) {
		return act_tec_dao.read(id);
	}
	
	public void eliminar(int id) {
		act_tec_dao.deleteActividadTecnica(act_tec_dao.read(id));
	}
	
	public void editar(ActividadesTecnicas actTecnicas) {
		act_tec_dao.update(actTecnicas);
	}
	
	public List<ActividadesTecnicas> getlistarActividadesTecnicas(){
		return act_tec_dao.find();
	}

	
}