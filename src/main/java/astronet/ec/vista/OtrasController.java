package astronet.ec.vista;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import astronet.ec.modelo.Empleado;
import astronet.ec.modelo.OtrasActividades;
import astronet.ec.on.EmpleadoON;
import astronet.ec.on.OtrasON;
import astronet.ec.util.SessionUtils;

@ManagedBean
@ViewScoped
public class OtrasController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Empleado> tecnicos;
	private String tecnicoElegido;
	private String actividad;
	private String fecha;
	private List<OtrasActividades> otras;
	@Inject
	private EmpleadoON empon;
	
	@Inject
	private OtrasON otrasOn;

	@PostConstruct
	public void init() {
		
		tecnicos = empon.getListadoTecnico();
		otras = otrasOn.getListadoOtras();
	}


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


	public String getTecnicoElegido() {
		return tecnicoElegido;
	}
	
	public OtrasON getOtrasOn() {
		return otrasOn;
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
	
	public String crearActividad() {
		OtrasActividades otraActividad = new OtrasActividades();
		Empleado tecnicoCampo = new Empleado();
		HttpSession session = SessionUtils.getSession();
		Empleado empleadoAgenda = (Empleado) session.getAttribute("username");
		tecnicoCampo = empon.getEmpleadobyName(tecnicoElegido);
		otraActividad.setTecnico(tecnicoCampo);
		otraActividad.setFecha(fecha);
		otraActividad.setActividad(actividad);
		otraActividad.setEmpleadoRegistra(empleadoAgenda);
		otrasOn.guardar(otraActividad);
		return null;
	}
	
}
