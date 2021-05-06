package astronet.ec.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import astronet.ec.modelo.ActividadesTecnicas;
import astronet.ec.modelo.Cliente;
import astronet.ec.modelo.Equipo;
import astronet.ec.modelo.RolEmpleado;

@Stateless
public class ActividadesTecnicasDAO {
	@Inject 
	private EntityManager em;
	
	// Guarda la actividad tecnica, ya sea creando una nueva o actualizando
	public void save(ActividadesTecnicas act) {
		if(this.read(act.getId_actividad()) != null) {
			this.update(act);
		}else {
			this.create(act);
		}
	}
	
	// Crea un nueva actividad tecnica
	public void create(ActividadesTecnicas act) {
		this.em.persist(act);
	}
	
	// Actualiza una actividad tecnica
	public void update(ActividadesTecnicas act) {
		try {
			this.em.merge(act);
		}catch(Exception ex) {
			System.out.println("ERROR: "+ex);
			ex.printStackTrace();
		}
	}
	
	
	// Lee una actividad tecnica por el id
	public ActividadesTecnicas read(int id) {
		return em.find(ActividadesTecnicas.class, id);
	}
	
	// Elimina una actividad tecnica
	public void deleteActividadTecnica(ActividadesTecnicas actividad_tec) {
		em.remove(actividad_tec);
	}
	
	
	public List<ActividadesTecnicas> find(){
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<ActividadesTecnicas> criteriaQuery = criteriaBuilder.createQuery(ActividadesTecnicas.class);
		Root<ActividadesTecnicas> root = criteriaQuery.from(ActividadesTecnicas.class);
		criteriaQuery.select(root); 		
		return em.createQuery(criteriaQuery).getResultList();
	}
	
	
	// Busca el puntaje con el id
	public double obtenerPuntaje(int id) {
		String jpql = "SELECT act FROM ActividadesTecnicas act WHERE act.id_actividad = :a";
		Query q = em.createQuery(jpql, ActividadesTecnicas.class);
		q.setParameter("a", id);
		ActividadesTecnicas act = (ActividadesTecnicas) q.getSingleResult();
		return act.getPuntaje_actividad();
	}	
	
	
	

}