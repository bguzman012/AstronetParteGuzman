package astronet.ec.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import astronet.ec.modelo.Empleado;
import astronet.ec.modelo.Registro;
import astronet.ec.modelo.Visita;

public class VisitaDAO {
	@Inject
	private EntityManager em;
	
	public void save(Visita Visita) {
		if (this.read(Visita.getId())!=null) {
			this.update(Visita);
		}else 
			this.create(Visita);
		
	}
	
	public List<Visita> getVisitaByTecnico(String empleado){
		Empleado e = new Empleado();
		e.setId(Integer.valueOf(empleado));
		System.out.println("Empleado code "+empleado);
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Visita> criteriaQuery = criteriaBuilder.createQuery(Visita.class);
		// Se establece la clausula FROM
		Root<Visita> root = criteriaQuery.from(Visita.class);
		
		Predicate predicateForGradeA=criteriaBuilder.equal(root.get("empleado"), e);

	    // equal
		Predicate predicateForGradeB=criteriaBuilder.equal(root.get("chequeo"), false);
	    Predicate finalPredicate = criteriaBuilder.and(predicateForGradeA, predicateForGradeB);
	    criteriaQuery.where(finalPredicate);
	    
	    
		//criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("empleado"), e)); // criteriaQuery.multiselect(root.get(atr))
		// // Se configuran los predicados,
		List<Visita> visitas= em.createQuery(criteriaQuery).getResultList();
	
		List<Registro> registrofilter= new ArrayList<Registro>();
//		for (Visita visita : visitas) {
//			registrofilter.add(regon.getRegistro(visita.getRegistro().getId()));
//			
//		}
	

		return visitas;
	}
	
	
	public void update(Visita Visita) {
		//System.out.println("registro "+cli.getRegistro().get(0).toString());
		em.merge(Visita);
	}

	public void delete(int id) {
		Visita equ = read(id);
		em.remove(equ);
	}
	
	public Visita read(int id) {
		return em.find(Visita.class, id);
	}
	
	public void create(Visita Visita) {
		em.persist(Visita);
		
	}
	
	public List<Visita> find(){
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Visita> criteriaQuery = criteriaBuilder.createQuery(Visita.class);
		// Se establece la clausula FROM
		criteriaQuery.select(criteriaQuery.from(Visita.class));
		System.out.println("Sech");
		return em.createQuery(criteriaQuery).getResultList();
		
	}

	public List<Visita> listarVisitas() {
		
		//String estado="VISITA TECNICA";
		boolean veri= false;
		String jpql = "SELECT vis FROM Visita vis WHERE vis.chequeo = "+veri;
		Query q = em.createQuery(jpql, Visita.class);
		//q.setParameter("a", veri);
		List<Visita> visitas = q.getResultList();
		return visitas;
	}
	

}