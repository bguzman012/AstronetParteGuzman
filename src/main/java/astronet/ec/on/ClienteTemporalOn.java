package astronet.ec.on;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import astronet.ec.dao.ClienteTemporalDAO;
import astronet.ec.modelo.Cliente;
import astronet.ec.modelo.ClienteTemporal;
import astronet.ec.modelo.Empleado;

@Stateless
public class ClienteTemporalOn {
	
	@Inject
	private ClienteTemporalDAO clidao;
	
	//probando
	
	public void guardar(ClienteTemporal cli) {

		clidao.save(cli);
	}
	
	public List<ClienteTemporal> filtradoLista(String id){
		return clidao.filtradoLista(id);
	}
	
	
	public void guardarCliente(ClienteTemporal cli) {
		clidao.create(cli);
	}
	
	public List<ClienteTemporal> getListadoCliente() {
		return clidao.getCliente();
	}
	
	public ClienteTemporal getClienteNombre(String nombre) {
		ClienteTemporal aux = clidao.buscarNombre(nombre);
		return aux;
	}
	
	public 	ClienteTemporal getCliTemId(int id) {
		ClienteTemporal aux = clidao.read3(id);
		System.out.println("Prueba: " + " " + clidao.read3(id));
		return aux;
	}
	
	public void actualizar(ClienteTemporal cli) {
		clidao.update(cli);
	}

	public  ClienteTemporal getClienteTemporal(String id) {
			return clidao.getClienteTemporal(id);
		}

}
