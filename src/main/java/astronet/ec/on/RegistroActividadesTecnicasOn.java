package astronet.ec.on;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import astronet.ec.dao.RegistroActividadesTecnicasDAO;
import astronet.ec.modelo.ActividadesTecnicas;
import astronet.ec.modelo.Cliente;
import astronet.ec.modelo.Empleado;
import astronet.ec.modelo.RegistroActividadesTecnicas;

@Stateless
public class RegistroActividadesTecnicasOn {
	
	@Inject
	private RegistroActividadesTecnicasDAO regActTecDAO;
	
	public void guardar(RegistroActividadesTecnicas reg) {
		regActTecDAO.save(reg);
	}
	
	public void create(RegistroActividadesTecnicas reg) {
		regActTecDAO.create(reg);
	}
	
	public void update(RegistroActividadesTecnicas reg) {
		regActTecDAO.update(reg);	
	}
	
	public RegistroActividadesTecnicas read(int id) {
		return regActTecDAO.read(id);
	}
	
	public List<RegistroActividadesTecnicas> listarRegistrosActividades(){
		return regActTecDAO.listarRegistrosActividades();
	}
	
	public RegistroActividadesTecnicas readRegistro(RegistroActividadesTecnicas reg) {
		return regActTecDAO.readRegistro(reg);
	}
		
	public void saveNewRegistro(RegistroActividadesTecnicas reg) {
		regActTecDAO.saveNewRegistro(reg);
	}
	
}
