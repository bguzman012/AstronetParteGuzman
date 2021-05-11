package astronet.ec.on;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import astronet.ec.dao.ClienteDAO;
import astronet.ec.dao.EmpleadoDAO;
import astronet.ec.dao.OtrasDAO;
import astronet.ec.dao.RegistroDAO;
import astronet.ec.modelo.Agendamiento;
import astronet.ec.modelo.Cliente;
import astronet.ec.modelo.Empleado;
import astronet.ec.modelo.OtrasActividades;
import astronet.ec.modelo.Registro;

@Stateless
public class OtrasON {

	@Inject
	private OtrasDAO otrasDao;


	public void guardar(OtrasActividades otra) {
		otrasDao.create(otra);
	}

	public void actualizar(OtrasActividades otras) {
		otrasDao.update(otras);
	}
	
	public List<OtrasActividades> getListadoOtras() {
		return otrasDao.listarOtrasActividades();
	}
	
	public OtrasActividades getOtrasId(int id) {
		
		return otrasDao.read(id);
	}

}