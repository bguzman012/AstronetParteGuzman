package astronet.ec.dao;

import java.util.List;


import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import astronet.ec.modelo.Agendamiento;
import astronet.ec.modelo.Cliente;
import astronet.ec.modelo.EquipoServicio;
import astronet.ec.modelo.OtrasActividades;
import astronet.ec.modelo.Plan;
import astronet.ec.modelo.Registro;
import astronet.ec.modelo.RegistroActividadesTecnicas;
import astronet.ec.modelo.Servicio;
import astronet.ec.modelo.Visita;


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
					
		String jpql = "SELECT otra FROM OtrasActividades otra WHERE otra.solucionado=false";
		Query q = em.createQuery(jpql, OtrasActividades.class);
		List<OtrasActividades> otras = q.getResultList();
		return otras;
			/*CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<OtrasActividades> criteriaQuery = criteriaBuilder.createQuery(OtrasActividades.class);
			// Se establece la clausula FROM
			
			criteriaQuery.select(criteriaQuery.from(OtrasActividades.class)).where(criteriaBuilder.equal("false", solucionado));
			
			Root<EquipoServicio> root = criteriaQuery.from(EquipoServicio.class);
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("servicio"), servicio));
			return em.createQuery(criteriaQuery).getResultList();	*/	
	}
	
	
	public void actualizarSolucionado(int id) {
		Query query = em.createQuery("UPDATE OtrasActividades o SET o.solucionado=true WHERE o.id=:id");
		query.setParameter("id", id);
		query.executeUpdate();
	}
	
	
	
}