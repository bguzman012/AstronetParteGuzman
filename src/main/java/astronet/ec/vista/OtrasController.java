package astronet.ec.vista;

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

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
public class OtrasController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Empleado> tecnicos;
	private String tecnicoElegido;
	private int id_actividad;
	private String actividad;
	private String mes;
	private int dia;
	private int year;
	private boolean solucionado;
	private Date date;


	private String antenaElegida;
	private List<OtrasActividades> otras;
	private List<ActividadesTecnicas> listarActividadesTecnicas;
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

	public String getMes() {
		return mes;
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

	public boolean isSolucionado() {
		return solucionado;
	}

	public void setSolucionado(boolean solucionado) {
		this.solucionado = solucionado;
	}
	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String crearActividad() {
		OtrasActividades otraActividad = new OtrasActividades();
		Empleado tecnicoCampo = new Empleado();
		HttpSession session = SessionUtils.getSession();
		Empleado empleadoAgenda = (Empleado) session.getAttribute("username");
		ActividadesTecnicas actividadTecnica = new ActividadesTecnicas();
				
		actividadTecnica.setId_actividad(Integer.parseInt(this.antenaElegida));
		System.out.println("Hola33sss " + actividadTecnica.getId_actividad());
		solucionado = false;
		tecnicoCampo = empon.getEmpleadobyName(tecnicoElegido);
		otraActividad.setTecnico(tecnicoCampo);
		otraActividad.setFecha(date);
		otraActividad.setActividad(actividad);
		otraActividad.setEmpleadoRegistra(empleadoAgenda);
		otraActividad.setActividadTecnica(actividadTecnica);
		otraActividad.setSolucionado(solucionado);

		otrasOn.guardar(otraActividad);	
		System.out.println("------ REGISTRO GUARDADO ------- ");
		String direccion = "listaOtrasActividades?faces-redirect=true";
		return direccion;
	}
	
	public String editarActividad(int id) {
		
		HttpSession session = SessionUtils.getSession();
		session.setAttribute("id", id);
		
		String direccion = "editarActividades?faces-redirect=true";
		return direccion;
	}

	public String actividadSolucionada(int id) {
		otrasOn.actualizarSolucionado(id);	
		
		OtrasActividades otraActividad = new OtrasActividades();
		otraActividad = otrasOn.getOtrasId(id);
		
		Date fecha = otraActividad.getFecha();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
		String cadena_fecha = formato.format(fecha);
		String cad[] = cadena_fecha.split("/");
		
		
		int num_mes = Integer.parseInt(cad[1]);
		String meses[] = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
		String mes_select = meses[num_mes-1];
		
		RegistroActividadesTecnicas registroActividad = new RegistroActividadesTecnicas();
		registroActividad.setMes(mes_select);
		registroActividad.setYear(Integer.parseInt(cad[0]));
		
		
		
		ActividadesTecnicas act = new ActividadesTecnicas();
		act = otraActividad.getActividadTecnica();
		registroActividad.setPuntajeTotal(actividadOn.obtenerPuntaje(act.getId_actividad()));
		
		registroActividad.setActivadesTotales(1);
		
		Empleado tecnicoCampo = new Empleado();
		tecnicoCampo = otraActividad.getTecnico();
		registroActividad.setTecnico(tecnicoCampo);
		regActTecON.saveNewRegistro(registroActividad);
		
		String direccion = "listaOtrasActividades?faces-redirect=true";
		return direccion;
	}
	
}
