package astronet.ec.on;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import astronet.ec.dao.ClienteTemporalDAO;
import astronet.ec.modelo.ClienteTemporal;

@Stateless

public class ClienteTemporalON {
	
	@Inject
	private ClienteTemporalDAO cliTDAO;
	
	public void guardar(ClienteTemporal cliT) {
		cliTDAO.sace(cliT);
	}
	public void actualizar(ClienteTemporal cliT) {
		cliTDAO.update(cliT);
	}
	public void crear(ClienteTemporal cliT) {
		cliTDAO.crear(cliT);
	}
	public void eliminar(ClienteTemporal cliT) {
		cliTDAO.delete(cliT.getId());
	}
	public void buscar(ClienteTemporal cliT) {
		cliTDAO.read(cliT.getId());
	}
	
	public List<ClienteTemporal> getCLientesTemporales() {
		return cliTDAO.getClientesTemporales();
	}
	public List<ClienteTemporal> filtradoLista(String id){
		return cliTDAO.filtradoLista(id);
	}

	public ClienteTemporalON() {
		// TODO Auto-generated constructor stub
	}

}