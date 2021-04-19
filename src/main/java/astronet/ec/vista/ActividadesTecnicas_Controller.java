package astronet.ec.vista;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import astronet.ec.modelo.ActividadesTecnicas;
import astronet.ec.modelo.Equipo;
import astronet.ec.modelo.RolEmpleado;
import astronet.ec.on.ActividadesTecnicasON;

@ManagedBean(name = "actividadesTecnicasControllerBean")
@ViewScoped

public class ActividadesTecnicas_Controller {
	@Inject ActividadesTecnicasON actividadOn;
	private int id_actividad;
	private String nombre_actividad;
	private double puntaje_actividad;
	private ActividadesTecnicas actividades;
	private List<ActividadesTecnicas> listarActividadesTecnicas;
	private Map<String, Integer> mapaRoles;
		
	public ActividadesTecnicas getActividades() {
		return actividades;
	}

	public void setActividades(ActividadesTecnicas actividades) {
		this.actividades = actividades;
	}

	public List<ActividadesTecnicas> getListarActividadesTecnicas() {
		return listarActividadesTecnicas;
	}

	public void setListarActividadesTecnicas(List<ActividadesTecnicas> listarActividadesTecnicas) {
		this.listarActividadesTecnicas = listarActividadesTecnicas;
	}

	public void cargarMapaActividades(List<ActividadesTecnicas> actividades) {
		this.mapaRoles = new HashMap<>();
		for (ActividadesTecnicas act : actividades) {
			mapaRoles.put(act.getNombre_actividad(), act.getId_actividad());
		}
	}
	
	
	public int getId_actividad() {
		return id_actividad;
	}

	public void setId_actividad(int id_actividad) {
		this.id_actividad = id_actividad;
	}

	public String getNombre_actividad() {
		return nombre_actividad;
	}

	public void setNombre_actividad(String nombre_actividad) {
		this.nombre_actividad = nombre_actividad;
	}

	public double getPuntaje_actividad() {
		return puntaje_actividad;
	}

	public void setPuntaje_actividad(double puntaje_actividad) {
		this.puntaje_actividad = puntaje_actividad;
	}

	public List<ActividadesTecnicas> listarActividadesTecnicas(){
		return actividadOn.getlistarActividadesTecnicas();
	}
	
	
	@PostConstruct
	public void init() {
		System.out.println("----- REGISTRO DE ACTIVIDADES TECNICAS -----");
		try {
			listarActividadesTecnicas = actividadOn.getlistarActividadesTecnicas();
			this.cargarMapaActividades(this.listarActividadesTecnicas);
			System.out.println("IMprimir actividadeds "+listarActividadesTecnicas.size());
			actividades = new ActividadesTecnicas();
		}catch (Exception e) {
			System.out.println("CONTROL DE ERRORES EN EMPLEADOCONTROLLER");
		}
	}
	
	// Metodo para guardar una nueva actividad tecnica
	public String guardarActividadTecnica() {
		this.actividades.setNombre_actividad(this.actividades.getNombre_actividad().toUpperCase());
		this.actividadOn.guardar(this.actividades);
		this.actividades = new ActividadesTecnicas();
		this.listarActividadesTecnicas = null;
		this.listarActividadesTecnicas = this.actividadOn.getlistarActividadesTecnicas();
		return "registrarActividad?faces-redirect=true";
	}
	
	//metodo para eliminar una actividad
	public String eliminarActividadTecnica(int codigo) {
		actividadOn.eliminar(codigo);
		init();
		return null;
	}
	
	
}