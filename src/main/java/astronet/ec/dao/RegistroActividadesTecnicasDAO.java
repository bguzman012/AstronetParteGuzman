package astronet.ec.dao;

import java.util.ArrayList;
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
import astronet.ec.modelo.Empleado;
import astronet.ec.modelo.EquipoServicio;
import astronet.ec.modelo.OtrasActividades;
import astronet.ec.modelo.Registro;
import astronet.ec.modelo.RegistroActividadesTecnicas;
import astronet.ec.modelo.Servicio;
import astronet.ec.modelo.Telefono;
import astronet.ec.on.ActividadesTecnicasON;

@Stateless
public class RegistroActividadesTecnicasDAO {
	@Inject
	private EntityManager em;
	
	@Inject 
	private EmpleadoDAO empleadoDAO;
	
	public void save(RegistroActividadesTecnicas reg) {
		if (this.read(reg.getId())!=null) {
			this.update(reg);
		}else 
			this.create(reg);
	}
	
	public void create(RegistroActividadesTecnicas reg) {
		em.persist(reg);
	}
	
	public void update(RegistroActividadesTecnicas reg) {
		em.merge(reg);
	}
	
	public RegistroActividadesTecnicas read(int id) {
		return em.find(RegistroActividadesTecnicas.class, id);
	}
	
	// Elimina un registro
	public void deleteRegistroActividadTecnica(RegistroActividadesTecnicas reg_actividad_tec) {
		em.remove(reg_actividad_tec);
	}
	
	public List<RegistroActividadesTecnicas> listarRegistrosActividades(){
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<RegistroActividadesTecnicas> criteriaQuery = criteriaBuilder.createQuery(RegistroActividadesTecnicas.class);
		Root<RegistroActividadesTecnicas> root = criteriaQuery.from(RegistroActividadesTecnicas.class);
		criteriaQuery.select(root); 		
		return em.createQuery(criteriaQuery).getResultList();
	}
	
	// Verificar si el empleado tiene un registro guardado de ese anio y mes
	public RegistroActividadesTecnicas readRegistro(RegistroActividadesTecnicas regis) {
		System.out.println("---- CODIGO DEL EMPLEADO ----"+regis.getTecnico());
		String jpql = "SELECT reg FROM RegistroActividadesTecnicas reg WHERE reg.mes = :a"+" and reg.year = :b"+" and reg.tecnico = :c";
		Query q = em.createQuery(jpql, RegistroActividadesTecnicas.class);
		q.setParameter("a", regis.getMes());
		q.setParameter("b", regis.getYear());
		q.setParameter("c", regis.getTecnico());
		RegistroActividadesTecnicas reg=null;
		
		try {
			reg = (RegistroActividadesTecnicas) q.getSingleResult();	
			double totalPuntaje = reg.getPuntajeTotal()+regis.getPuntajeTotal();
			int totalActividades = reg.getActivadesTotales() + 1;
			reg.setPuntajeTotal(totalPuntaje);
			reg.setActivadesTotales(totalActividades);
			return reg;
		}catch(Exception ex) {
			System.out.println("NO EXISTE REGISTROS");
			return null;
		}	
	}
	
	// Actualizar un registro
	public void actualizarRegistro(RegistroActividadesTecnicas reg) {
		/*String jpql = "UPDATE public.registroactividadestecnicas"
				+ " set actividadesrealizadastotal=:a, mesactividad=:b, puntajetotal=:c, yearactividad=:d, empleadotecnicoact_fk=:e"
				+ " where registrosacttecnica_id=:id";*/
		String jpql = "UPDATE RegistroActividadesTecnicas set actividadesrealizadastotal=:a, mesactividad=:b, puntajetotal=:c, yearactividad=:d, empleadotecnicoact_fk=:e where registrosacttecnica_id=:id";
		Query q = em.createNamedQuery(jpql, RegistroActividadesTecnicas.class);
		q.setParameter("a", reg.getActivadesTotales());
		q.setParameter("b", reg.getMes());
		q.setParameter("c", reg.getPuntajeTotal());
		q.setParameter("d", reg.getYear());
		q.setParameter("e", reg.getTecnico());
		q.setParameter("id", reg.getId());
		try {
			q.executeUpdate();
		}catch(Exception ex) {
			System.out.println("----- NO SE PUDO ACTUALIZAR ------");
		}
	}

	
	// Crea un registro en caso de no encontrar ningun registro de coincidencia	
	public void saveNewRegistro(RegistroActividadesTecnicas reg) {
		RegistroActividadesTecnicas r = this.readRegistro(reg);
		if(r != null){
			this.update(r);
		}else{
			this.create(reg);
		}
	}	
	//Creacion de Registros para manejar las actividades de los tecnicos
}
