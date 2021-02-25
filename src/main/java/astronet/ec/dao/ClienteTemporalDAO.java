package astronet.ec.dao;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import astronet.ec.modelo.ClienteTemporal;
import astronet.ec.modelo.Empleado;

@Stateless

public class ClienteTemporalDAO {
	
	@Inject
	private EntityManager em;
	
	public void crear(ClienteTemporal cliT) {
		em.persist(cliT);
	}
	
	public ClienteTemporal read(int id) {
		return em.find(ClienteTemporal.class, id);
	}
	
	public void update(ClienteTemporal cliT) {
		em.merge(cliT);
	}
	
	public void delete(int id) {
		ClienteTemporal cli = read(id);
		em.remove(cli);
	}
	
	public void sace(ClienteTemporal cliT) {
		if(this.read(cliT.getId())!=null) {
			this.update(cliT);
		}else {
			this.crear(cliT);
		}
	}
	
	public List<ClienteTemporal> getClientesTemporales() {
		String jpql = "SELECT empleado FROM ClienteTemporal empleado ";
		Query q = em.createQuery(jpql, ClienteTemporal.class);
		List<ClienteTemporal> clientestemp = q.getResultList();
		System.out.println("LONGITUD CLIENTES TEMP "+ clientestemp.size());
		return clientestemp;
	}

	public ClienteTemporalDAO() {
		// TODO Auto-generated constructor stub
		
	}
	
	
	public  ClienteTemporal Filtado(int id) {
		String jpql = "SELECT cliT FROM ClienteTemporal cliT WHERE cliT.cli_fkempleado != :a";
		Query q = em.createQuery(jpql, ClienteTemporal.class);
		q.setParameter("a", id);
		ClienteTemporal cliT = (ClienteTemporal) q.getSingleResult();
		return cliT;	
	}

	public List<ClienteTemporal> filtradoLista(String id){
		String jpql = "SELECT cliT FROM ClienteTemporal cliT WHERE cliT.fk_empleado = :a";
		Query q = em.createQuery(jpql, ClienteTemporal.class);
		q.setParameter("a", id);
		List<ClienteTemporal> listaclientes = q.getResultList();
		return listaclientes;
		
	}
	

}