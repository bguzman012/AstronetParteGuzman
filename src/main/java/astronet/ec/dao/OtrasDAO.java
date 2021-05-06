package astronet.ec.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import astronet.ec.modelo.Agendamiento;
import astronet.ec.modelo.Cliente;
import astronet.ec.modelo.OtrasActividades;
import astronet.ec.modelo.Plan;
import astronet.ec.modelo.Registro;
import astronet.ec.modelo.Servicio;

@Stateless
public class OtrasDAO {
	
	@Inject
	private EntityManager em;
	
	public void save(OtrasActividades otras) {
		if (this.read(otras.getId())!=null) {
			this.update(otras);
		}else 
			this.create(otras);
	}
	
	public void create(OtrasActividades otras) {
		em.persist(otras);
		
	}
	
	public void update(OtrasActividades otras) {
		em.merge(otras);
		
	}
	
	public OtrasActividades read(int id) {
		return em.find(OtrasActividades.class, id);
		
	}
	
	public List<OtrasActividades> listarOtrasActividades() {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<OtrasActividades> criteriaQuery = criteriaBuilder.createQuery(OtrasActividades.class);
			// Se establece la clausula FROM
			criteriaQuery.select(criteriaQuery.from(OtrasActividades.class));
			return em.createQuery(criteriaQuery).getResultList();		
	}
	
	
	
}