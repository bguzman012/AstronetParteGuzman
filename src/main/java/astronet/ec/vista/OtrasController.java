package astronet.ec.vista;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import astronet.ec.modelo.ActividadesTecnicas;
import astronet.ec.modelo.Empleado;
import astronet.ec.modelo.OtrasActividades;
import astronet.ec.on.ActividadesTecnicasON;
import astronet.ec.on.EmpleadoON;
import astronet.ec.on.OtrasON;
import astronet.ec.util.SessionUtils;

@ManagedBean
@ViewScoped
public class OtrasController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Empleado> tecnicos;
	private String tecnicoElegido;
	private int id_actividad;
	private String actividad;
	private String fecha;
	private String antenaElegida;
	private List<OtrasActividades> otras;
	private List<ActividadesTecnicas> listarActividadesTecnicas;
	@Inject
	private EmpleadoON empon;
	
	@Inject
	private OtrasON otrasOn;
	
	@Inject 
	private ActividadesTecnicasON actividadOn;
	
	public List<Empleado> getTecnicos() {
		return tecnicos;
	}

	public void setTecnicos(List<Empleado> tecnicos) {
		this.tecnicos = tecnicos;
	}

	

	public List<OtrasActividades> getOtras() {
		return otras;
	}


	public void setOtras(List<OtrasActividades> otras) {
		this.otras = otras;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public String getAntenaElegida() {
		return antenaElegida;
	}

	public void setAntenaElegida(String antenaElegida) {
		this.antenaElegida = antenaElegida;
	}

	public String getTecnicoElegido() {
		return tecnicoElegido;
	}
	
	public OtrasON getOtrasOn() {
		return otrasOn;
	}


	public int getId_actividad() {
		return id_actividad;
	}

	public void setId_actividad(int id_actividad) {
		this.id_actividad = id_actividad;
	}

	public void setOtrasOn(OtrasON otrasOn) {
		this.otrasOn = otrasOn;
	}


	public String getActividad() {
		return actividad;
	}


	public void setActividad(String actividad) {
		this.actividad = actividad;
	}


	public void setTecnicoElegido(String tecnicoElegido) {
		this.tecnicoElegido = tecnicoElegido;
	}


	public EmpleadoON getEmpon() {
		return empon;
	}


	public void setEmpon(EmpleadoON empon) {
		this.empon = empon;
	}
	public List<ActividadesTecnicas> getListarActividadesTecnicas() {
		return listarActividadesTecnicas;
	}


	public void setListarActividadesTecnicas(List<ActividadesTecnicas> listarActividadesTecnicas) {
		this.listarActividadesTecnicas = listarActividadesTecnicas;
	}

	@PostConstruct
	public void init() {
		listarActividadesTecnicas = actividadOn.getlistarActividadesTecnicas();
		tecnicos = empon.getListadoTecnico();
		otras = otrasOn.getListadoOtras();
	}



	
	public String crearActividad() {
		OtrasActividades otraActividad = new OtrasActividades();
		Empleado tecnicoCampo = new Empleado();
		HttpSession session = SessionUtils.getSession();
		Empleado empleadoAgenda = (Empleado) session.getAttribute("username");
		ActividadesTecnicas actividadTecnica = new ActividadesTecnicas();
		
		
		
		actividadTecnica.setId_actividad(Integer.parseInt(this.antenaElegida));
		System.out.println("Hola33sss " + actividadTecnica.getId_actividad());

		tecnicoCampo = empon.getEmpleadobyName(tecnicoElegido);
		otraActividad.setTecnico(tecnicoCampo);
		otraActividad.setFecha(fecha);
		otraActividad.setActividad(actividad);
		otraActividad.setEmpleadoRegistra(empleadoAgenda);
		otraActividad.setActividadTecnica(actividadTecnica);
		
		otrasOn.guardar(otraActividad);
		String direccion = "listaOtrasActividades?faces-redirect=true";
		return direccion;
	}


	
	
}
