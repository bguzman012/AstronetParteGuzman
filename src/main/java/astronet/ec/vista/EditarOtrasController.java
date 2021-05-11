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
import astronet.ec.modelo.RegistroActividadesTecnicas;
import astronet.ec.on.ActividadesTecnicasON;
import astronet.ec.on.EmpleadoON;
import astronet.ec.on.OtrasON;
import astronet.ec.on.RegistroActividadesTecnicasOn;
import astronet.ec.util.SessionUtils;

@ManagedBean
@ViewScoped
public class EditarOtrasController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Empleado> tecnicos;
	private int tecnicoElegido;
	private int id_actividad;
	private String actividad;
	private String mes;
	private int dia;
	private int year;


	private String antenaElegida;
	private List<OtrasActividades> otras;
	private List<ActividadesTecnicas> listarActividadesTecnicas;
	private OtrasActividades otra;
	@Inject
	private EmpleadoON empon;
	
	@Inject
	private ActividadesTecnicasON actTecON;
	
	@Inject
	private OtrasON otrasOn;
	
	@Inject
	private RegistroActividadesTecnicasOn regActTecON;
	
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

	public String getAntenaElegida() {
		return antenaElegida;
	}

	public void setAntenaElegida(String antenaElegida) {
		this.antenaElegida = antenaElegida;
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


	


	public EmpleadoON getEmpon() {
		return empon;
	}


	public void setEmpon(EmpleadoON empon) {
		this.empon = empon;
	}
	public List<ActividadesTecnicas> getListarActividadesTecnicas() {
		return listarActividadesTecnicas;
	}


	public int getTecnicoElegido() {
		return tecnicoElegido;
	}

	public void setTecnicoElegido(int tecnicoElegido) {
		this.tecnicoElegido = tecnicoElegido;
	}

	public void setListarActividadesTecnicas(List<ActividadesTecnicas> listarActividadesTecnicas) {
		this.listarActividadesTecnicas = listarActividadesTecnicas;
	}

	@PostConstruct
	public void init() {
		HttpSession session = SessionUtils.getSession();
		int id = (int) session.getAttribute("id");
		System.out.println("HolaAmigo: " + id);
		otra = otrasOn.getOtrasId(id);
		System.out.println("Memento" + otra.getId());
		listarActividadesTecnicas = actividadOn.getlistarActividadesTecnicas();
		tecnicos = empon.getListadoTecnico();
		otras = otrasOn.getListadoOtras();		
		
		
	}

	public String getMes() {
		return mes;
	}

	public OtrasActividades getOtra() {
		return otra;
	}

	public void setOtra(OtrasActividades otra) {
		this.otra = otra;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public String editar() {
		
		if(dia!=0) {
			otra.setDia(dia);
		}
		
		if(mes!=null) {
			otra.setMes(mes);
		}
		
		if(year!=0) {
			otra.setYear(year);
		}
		if(tecnicoElegido!=0) {
			Empleado empleado = new Empleado();
			empleado.setId(tecnicoElegido);
			otra.setTecnico(empleado);
		}
		if(id_actividad!=0) {
			ActividadesTecnicas actividadTmp = new ActividadesTecnicas();
			actividadTmp.setId_actividad(id_actividad);
			otra.setActividadTecnica(actividadTmp);
			
		}
		otrasOn.actualizar(otra);
		String direccion = "listaOtrasActividades?faces-redirect=true";
		return direccion;
	}

	
	
	



	
	
}
