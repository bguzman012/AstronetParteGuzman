package astronet.ec.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import astronet.ec.modelo.Cliente;
import astronet.ec.modelo.ClienteTemporal;

@Stateless
public class ClienteTemporalDAO {
	
	@Inject
	private EntityManager em;

	public void save(ClienteTemporal cli) {
		if (this.read(cli.getId())!=null) {
			this.update(cli);
		}else 
			this.create(cli);
		
	}
	public List<ClienteTemporal> filtradoLista(String id){
		String jpql = "SELECT cliT FROM ClienteTemporal cliT WHERE cliT.fk_empleado = :a";
		Query q = em.createQuery(jpql, ClienteTemporal.class);
		q.setParameter("a", id);
		List<ClienteTemporal> listaclientes = q.getResultList();
		return listaclientes;
		
	}
	
public  ClienteTemporal getClienteTemporal(String id) {
		
		String jpql = "SELECT cliT FROM ClienteTemporal cliT WHERE cliT.id != :a";
		Query q = em.createQuery(jpql, ClienteTemporal.class);
		q.setParameter("a",Integer.valueOf(id));
		ClienteTemporal cliT = (ClienteTemporal) q.getSingleResult();
		return cliT;	
	}
	
	
	public void create(ClienteTemporal cli) {
		em.persist(cli);
		
	}
	
	
	public ClienteTemporal read(int id) {
		return em.find(ClienteTemporal.class, id);
	}
	
	
	public ClienteTemporal read3(int id) {
		String jpql = "SELECT cli FROM ClienteTemporal cli   WHERE cli.id = :a";
		Query q = em.createQuery(jpql, ClienteTemporal.class);
		q.setParameter("a", id);
		ClienteTemporal cli = (ClienteTemporal) q.getSingleResult();
				
		return cli;

	}
	
	public void update(ClienteTemporal cli) {
		//System.out.println("registro "+cli.getRegistro().get(0).toString());
		em.merge(cli);
		
	}
	
	public void delete(int id) {
		ClienteTemporal cli = read(id);
		em.remove(cli);
	}
	
	 public List<ClienteTemporal> getCliente() {
		String jpql = "SELECT cliente FROM ClienteTemporal cliente WHERE cliente.estado = false ";
		Query q = em.createQuery(jpql, ClienteTemporal.class);
		List<ClienteTemporal> clientes = q.getResultList();
		return clientes;
	}
	 
		public ClienteTemporal buscarNombre(String nombre) {
			String jpql = "SELECT cli FROM ClienteTemporal cli WHERE cli.nombre = :nombre";
			Query q = em.createQuery(jpql, ClienteTemporal.class);
			q.setParameter("nombre", nombre);
			ClienteTemporal clien = (ClienteTemporal) q.getSingleResult();
			return clien;
		}
	
	
}




