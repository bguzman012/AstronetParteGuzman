
package astronet.ec.dao;
import java.util.List;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import astronet.ec.modelo.Cliente;
import astronet.ec.modelo.Empleado;
import astronet.ec.modelo.Equipo;
import astronet.ec.modelo.Plan;
import astronet.ec.modelo.Registro;
import astronet.ec.modelo.Telefono;

@Stateless
public class EquipoDAO {

	@Inject
	private EntityManager em;

	public void save(Equipo equipo) {
		if (this.read(equipo.getId())!=null) {
			this.update(equipo);
		}else
			this.create(equipo);

	}

	public void update(Equipo equipo) {
		//System.out.println("registro "+cli.getRegistro().get(0).toString());
		em.merge(equipo);

	}

	public void delete(int id) {
		Equipo equ = read(id);
		em.remove(equ);
	}

	public Equipo read(int id) {
		return em.find(Equipo.class, id);
	}

	public void create(Equipo equipo) {
		em.persist(equipo);

	}

	public List<Equipo> find(){
		String tipoEquipo = "Antena";
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Equipo> criteriaQuery = criteriaBuilder.createQuery(Equipo.class);
		Root<Equipo> root = criteriaQuery.from(Equipo.class);
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("tipoEquipo"), tipoEquipo)); // criteriaQuery.multiselect(root.get(atr))
		return em.createQuery(criteriaQuery).getResultList();
	}

	public Equipo getAntenaByName(String name) {
		String jpql = "SELECT eq FROM Equipo eq WHERE eq.nombre = :a";
		Query q = em.createQuery(jpql, Equipo.class);
		q.setParameter("a", name);
		Equipo eq = (Equipo) q.getSingleResult();
		return eq;
		
	}

	public List<Equipo> findEquiposFibra(){
		String tipoEquipo = "RouterFibra";
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Equipo> criteriaQuery = criteriaBuilder.createQuery(Equipo.class);
		// Se establece la clausula FROM
		Root<Equipo> root = criteriaQuery.from(Equipo.class);

		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("tipoEquipo"), tipoEquipo)); // criteriaQuery.multiselect(root.get(atr))
		return em.createQuery(criteriaQuery).getResultList();
	}
	//Scorpion code
	public List<String> findAntenasRadio(){
		
		String jpql = "SELECT an.nombre FROM Equipo an";
		Query q = em.createQuery(jpql,String.class);
		
		List<String>antenasradio = q.getResultList();
		
		return antenasradio;

		
	}
	
	public List<String> nombreAntenas(){
		String antena="Antena";
		String jpql = "SELECT equi.modelo FROM Equipo equi WHERE equi.tipoEquipo = :a";
		Query q = em.createQuery(jpql);
		q.setParameter("a", antena);
		List<String> list=q.getResultList();
		return list;
	}
	public List<String> nombreAntenasFibra(){
		String antena="RouterFibra";
		String jpql = "SELECT equi.modelo FROM Equipo equi WHERE equi.tipoEquipo = :a";
		Query q = em.createQuery(jpql);
		q.setParameter("a", antena);
		List<String> list=q.getResultList();
		return list;
	}
	
	

}
