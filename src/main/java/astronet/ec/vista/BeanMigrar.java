package astronet.ec.vista;

import java.io.IOException;

import java.io.Serializable;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import astronet.ec.modelo.Agendamiento;
import astronet.ec.modelo.Cliente;
import astronet.ec.modelo.Empleado;
import astronet.ec.modelo.Equipo;
import astronet.ec.modelo.EquipoServicio;
import astronet.ec.modelo.Instalacion;
import astronet.ec.modelo.Plan;
import astronet.ec.modelo.Registro;
import astronet.ec.modelo.Servicio;
import astronet.ec.modelo.Telefono;
import astronet.ec.modelo.Visita;
import astronet.ec.on.AgendamientoON;
import astronet.ec.on.ClienteON;
import astronet.ec.on.EmpleadoON;
import astronet.ec.on.EquipoOn;
import astronet.ec.on.EquipoServicioON;
import astronet.ec.on.InstalacionON;
import astronet.ec.on.PlanON;
import astronet.ec.on.RegistroON;
import astronet.ec.on.ServicioON;
import astronet.ec.on.TelefonoON;
import astronet.ec.on.VisitaON;
import astronet.ec.util.SessionUtils;
import astronet.ec.vista.InstalacionController.ServicioFA;

//SICHA IMPORT

import java.util.Locale;

@ManagedBean
@SessionScoped
public class BeanMigrar implements Serializable {

	// private static final long serialVersionUID = 8799656478674716638L;
	private static final long serialVersionUID = 1L;
	private Cliente cliente = new Cliente();
	private EmpleadoController ubean = new EmpleadoController();
	private List<Cliente> listadoCliente;
	private List<Servicio> servicios;
	private List<Registro> registros;
	private List<Empleado> empleados;
	private List<Instalacion> listaInstalaciones;
	private List<Equipo> listadoEquiposFibra;
	private Registro registro = new Registro();
	private Empleado empleado = new Empleado();
	private Servicio servicio = new Servicio();
	private Instalacion instalacion = new Instalacion();
	private Agendamiento agendamiento = new Agendamiento();
	private Visita visita = new Visita();

	public EmpleadoController getUbean() {
		return ubean;
	}

	public void setUbean(EmpleadoController ubean) {
		this.ubean = ubean;
	}

	public Visita getVisita() {
		return visita;
	}

	public void setVisita(Visita visita) {
		this.visita = visita;
	}

	public List<String> getListaSugerencias() {
		return listaSugerencias;
	}

	public void setListaSugerencias(List<String> listaSugerencias) {
		this.listaSugerencias = listaSugerencias;
	}

	public VisitaON getVisitaOn() {
		return visitaOn;
	}

	public void setVisitaOn(VisitaON visitaOn) {
		this.visitaOn = visitaOn;
	}



	private Equipo equipo = new Equipo();
	private Servicio servicioTmp;
	private Telefono telefono;
	private List<Telefono> telefonos;
	private Telefono nuevoTelefono;

	private String nuevoNumero;
	private String nuevoTipoTelefono;
	private List<EquipoServicio> serviciosCliente;

	private List<Registro> registrosvisita;
	private List<Agendamiento> agendamientos;
	private List<Empleado> tecnicos;
	private String tecnicoElegido;
	private List<Registro> listadoFiltrado;
	private String problemaElegido;
	private List<String> ejemploLista;
	/**
	 * Declaraacion de variables
	 */
	private int id;
	private int idR;
	private String cedula;
	private String nombre;
	private String apellidos;

	private String ip;

	private String tipoServicio;
	private String ipcallcenter;

	private String password;

	private String serial;
	private String email;
	private String convencional;
	private String celular;
	private String direccionPrincipal;

	public EmpleadoController getUbn() {
		return ubn;
	}

	public void setUbn(EmpleadoController ubn) {
		this.ubn = ubn;
	}

	public String getProblemaElegido() {
		return problemaElegido;
	}

	public List<String> getEjemploLista() {
		return ejemploLista;
	}

	public void setEjemploLista(List<String> ejemploLista) {
		this.ejemploLista = ejemploLista;
	}

	public void setProblemaElegido(String problemaElegido) {
		this.problemaElegido = problemaElegido;
	}

	private String direccionSecundaria;
	private String direccionReferencia;
	private String latitud;
	private String longitud;
	private String jj;
	private String antenaC;
	public String problemas;
	public String soluciones;
	private String empleados1;
	private String servicioRB;
	private EmpleadoController ubn;
	public List<String> listaSugerencias;

	private String servicioElegido;
	private String numContrato;
	private String fecha;
	private String item;
	private String antenaElegida;
	private String planElegida;
	private String observaciones;
	private String routerVendido;

	private Telefono telefonoConveEdit;
	private Telefono telefonoMovilEdit;
	private Servicio servicioEdit;
	private EquipoServicio eqServEdit;

	private String antenaTmp;
	private String planTmp;
	private String router;
	private boolean rendered;
	private String equipoElegido;
	private List<String> opciones;
	public List<Cliente> filtradoCliente;

	private List<String> tipoServicios;
	private List<Equipo> listadoAntenas;
	private List<Plan> listadoPlanes;
	private List<Plan> listadoPlanesFibra;
	private List<Plan> listadoPlanesTmp;
	private String planElegidoFibra;
	public int idEmpl;

	public String inputName;

	private int codigoReg;

	@PostConstruct
	public void init() {
		cliente = new Cliente();
		telefono = new Telefono();
		registro = new Registro();
		instalacion = new Instalacion();
		servicio = new Servicio();
		agendamiento = new Agendamiento();
		empleados = empon.getListadoEmpleado();
		listadoCliente = clion.getListadoCliente();
		listaInstalaciones = inson.getListadoInstalacion();
		telefonos = new ArrayList<Telefono>();
		equipo = new Equipo();
		serviciosCliente = new ArrayList<EquipoServicio>();
		ubn = new EmpleadoController();
		ejemploLista= regon.problemitas();
		registros = regon.getListadoRegistro();

		nuevoTelefono = new Telefono();

		servicioTmp = new Servicio();
		telefonoConveEdit = new Telefono();
		telefonoMovilEdit = new Telefono();
		servicioEdit = new Servicio();
		eqServEdit = new EquipoServicio();

		telefonos = new ArrayList<Telefono>();
		listadoPlanesTmp = new ArrayList<Plan>();

		listadoPlanes = new ArrayList<Plan>();
		listadoPlanesFibra = new ArrayList<Plan>();
		equipo = new Equipo();
		servicioElegido = "Fibra";
		if (servicioElegido.equals("Radio")) {

			listadoAntenas = eqOn.getListadoAntenas();
		} else {
			listadoAntenas = eqOn.getListadoEquiposFibra();
		}
		System.out.println("Servicio: " + servicioElegido);
		listadoEquiposFibra = eqOn.getListadoEquiposFibra();
		listadoPlanesTmp = planOn.getListadoPlan();
		opciones = new ArrayList<String>();
		tipoServicios = new ArrayList<String>();

		listadoPlanes.add(listadoPlanesTmp.get(0));
		listadoPlanes.add(listadoPlanesTmp.get(1));
		listadoPlanes.add(listadoPlanesTmp.get(2));
		
		listadoPlanesFibra.add(listadoPlanesTmp.get(3));
		listadoPlanesFibra.add(listadoPlanesTmp.get(4));
		listadoPlanesFibra.add(listadoPlanesTmp.get(5));

		opciones.add("Si");
		opciones.add("No");

		tipoServicios.add("Radio");
		tipoServicios.add("Fibra");
		listadoAntenas = eqOn.getListadoAntenas();
		listadoPlanes = planOn.getListadoPlan();
		registrosvisita = regon.listadoRegistrosVT();
		tecnicos = empon.getListadoTecnico();
		agendamientos = agon.getAgenda();
		visita = new Visita();

		System.out.println("Si tomoo las antenaas" + listadoAntenas.size());
		listaSugerencias = new ArrayList<String>();
		this.ipcallcenter = "";

	}

	public String getIpcallcenter() {
		return ipcallcenter;
	}

	public void setIpcallcenter(String ipcallcenter) {
		this.ipcallcenter = ipcallcenter;
	}

	public List<Agendamiento> getAgendamientos() {
		return agendamientos;
	}

	public void setAgendamientos(List<Agendamiento> agendamientos) {
		this.agendamientos = agendamientos;
	}

	public List<Registro> getRegistrosvisita() {
		return registrosvisita;
	}

	public List<Empleado> getTecnicos() {
		return tecnicos;
	}

	public List<Registro> getListadoFiltrado() {
		return listadoFiltrado;
	}

	public void setListadoFiltrado(List<Registro> listadoFiltrado) {
		this.listadoFiltrado = listadoFiltrado;
	}

	public void setTecnicos(List<Empleado> tecnicos) {
		this.tecnicos = tecnicos;
	}

	public String getTecnicoElegido() {
		return tecnicoElegido;
	}

	public void setTecnicoElegido(String tecnicoElegido) {
		this.tecnicoElegido = tecnicoElegido;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setRegistrosvisita(List<Registro> registrosvisita) {
		this.registrosvisita = registrosvisita;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public EquipoServicio clienteip;

	@Inject
	private ClienteON clion;

	@Inject
	private RegistroON regon;

	@Inject
	private EmpleadoON empon;

	@Inject
	private InstalacionON inson;

	@Inject
	private ServicioON seron;

	@Inject
	private FacesContext fc;

	@Inject
	private AgendamientoON agon;

	@Inject
	private EquipoOn eqOn;

	@Inject
	private TelefonoON telOn;

	@Inject
	private PlanON planOn;

	@Inject
	private VisitaON visitaOn;

	@Inject
	private EquipoServicioON eqServOn;
	
	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public String getDireccionPrincipal() {
		return direccionPrincipal;
	}

	public void setDireccionPrincipal(String direccionPrincipal) {
		this.direccionPrincipal = direccionPrincipal;
	}

	public String getDireccionSecundaria() {
		return direccionSecundaria;
	}

	public void setDireccionSecundaria(String direccionSecundaria) {
		this.direccionSecundaria = direccionSecundaria;
	}

	public String getDireccionReferencia() {
		return direccionReferencia;
	}

	public void setDireccionReferencia(String direccionReferencia) {
		this.direccionReferencia = direccionReferencia;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public ClienteON getClion() {
		return clion;
	}

	public void setClion(ClienteON clion) {
		this.clion = clion;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getAntenaElegida() {
		return antenaElegida;
	}

	public void setAntenaElegida(String antenaElegida) {

		this.antenaElegida = antenaElegida;
	}

	public List<Equipo> getListadoAntenas() {
		if (servicioEdit.getTipoServicio() != null) {
			if (servicioEdit.getTipoServicio().equals("radio")) {
				return eqOn.getListadoAntenas();
			} else {
				System.out.println("Servicio: " + servicioEdit.getTipoServicio());
				return eqOn.getListadoEquiposFibra();
			}
		} else {
			return null;
		}
	}

	public void setListadoAntenas(List<Equipo> listadoAntenas) {
		if (servicioElegido.equals("Radio")) {

			this.listadoAntenas = eqOn.getListadoAntenas();

		} else {
			this.listadoAntenas = eqOn.getListadoEquiposFibra();
			System.out.println("Size=" + listadoAntenas.size());
		}
	}

	public String getEmail() {
		return email;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public PlanON getPlanOn() {
		return planOn;
	}

	public void setPlanOn(PlanON planOn) {
		this.planOn = planOn;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConvencional() {
		return convencional;
	}

	public void setConvencional(String convencional) {
		this.convencional = convencional;
	}

	public String getCelular() {
		return celular;
	}

	public List<EquipoServicio> getServiciosCliente() {
		return serviciosCliente;
	}

	public void setServiciosCliente(List<EquipoServicio> serviciosCliente) {
		this.serviciosCliente = serviciosCliente;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getJj() {
		return jj;
	}

	public void setJj(String jj) {
		this.jj = jj;
	}

	public String getProblemas() {
		return problemas;
	}

	public void setProblemas(String problemas) {
		this.problemas = problemas;
	}

	public List<Plan> getListadoPlanesTmp() {
		return listadoPlanesTmp;
	}

	public void setListadoPlanesTmp(List<Plan> listadoPlanesTmp) {
		this.listadoPlanesTmp = listadoPlanesTmp;
	}

	public RegistroON getRegon() {
		return regon;
	}

	public void setRegon(RegistroON regon) {
		this.regon = regon;
	}

	public EmpleadoON getEmpon() {
		return empon;
	}

	public void setEmpon(EmpleadoON empon) {
		this.empon = empon;
	}

	public InstalacionON getInson() {
		return inson;
	}

	public void setInson(InstalacionON inson) {
		this.inson = inson;
	}

	public ServicioON getSeron() {
		return seron;
	}

	public void setSeron(ServicioON seron) {
		this.seron = seron;
	}

	public FacesContext getFc() {
		return fc;
	}

	public void setFc(FacesContext fc) {
		this.fc = fc;
	}

	public AgendamientoON getAgon() {
		return agon;
	}

	public void setAgon(AgendamientoON agon) {
		this.agon = agon;
	}

	public EquipoOn getEqOn() {
		return eqOn;
	}

	public void setEqOn(EquipoOn eqOn) {
		this.eqOn = eqOn;
	}

	public TelefonoON getTelOn() {
		return telOn;
	}

	public void setTelOn(TelefonoON telOn) {
		
		this.telOn = telOn;
	}

	public ServicioFA[] getServicioLista() {
		return servicioLista;
	}

	public void setServicioLista(ServicioFA[] servicioLista) {
		this.servicioLista = servicioLista;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/*
	 * Creacion de getters and setters
	 */
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getListadoCliente() {
		return listadoCliente;
	}

	public void setListadoCliente(List<Cliente> listadoCliente) {
		this.listadoCliente = listadoCliente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getAntenaC() {
		return antenaC;
	}

	public void setAntenaC(String antenaC) {
		this.antenaC = antenaC;
	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public String getSoluciones() {
		return soluciones;
	}

	public void setSoluciones(String soluciones) {
		this.soluciones = soluciones;
	}

	public String getEmpleados1() {
		return empleados1;
	}

	public void setEmpleados1(String empleados1) {
		this.empleados1 = empleados1;
	}

	public List<Registro> getRegistros() {
		return registros;
	}

	public void setRegistros(List<Registro> registros) {
		this.registros = registros;
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public Registro getRegistro() {
		return registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

	public Instalacion getInstalacion() {
		return instalacion;
	}

	public void setInstalacion(Instalacion instalacion) {
		this.instalacion = instalacion;
	}

	public int getCodigoReg() {
		return codigoReg;
	}

	public void setCodigoReg(int codigoReg) {
		this.codigoReg = codigoReg;
	}

	public String getServicioRB() {
		return servicioRB;
	}

	public void setServicioRB(String servicioRB) {
		this.servicioRB = servicioRB;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public List<Instalacion> getListaInstalaciones() {
		return listaInstalaciones;
	}

	public void setListaInstalaciones(List<Instalacion> listaInstalaciones) {
		this.listaInstalaciones = listaInstalaciones;
	}

	public int getIdEmpl() {
		return idEmpl;
	}

	public void setIdEmpl(int idEmpl) {
		this.idEmpl = idEmpl;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public Agendamiento getAgendamiento() {
		return agendamiento;
	}

	public void setAgendamiento(Agendamiento agendamiento) {
		this.agendamiento = agendamiento;
	}

	public int getIdR() {
		return idR;
	}

	public void setIdR(int idR) {
		this.idR = idR;
	}

	/*
	 * Hasta aqui llega la creacion de los getters and setters
	 */

	public String getInputName() {
		return inputName;
	}

	public void setInputName(String inputName) {
		this.inputName = inputName;
	}

	public List<Telefono> getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(List<Telefono> telefonos) {
		this.telefonos = telefonos;
	}

	/**
	 * Metodo para dirigirnos a la pagina editarClientes
	 *
	 * @param codigo
	 * @return
	 */

	public String editar(int codigo) {

		return "editarClientes?faces-redirect=true&id=" + codigo;
	}

	public String editarRegistro(int codigo, int idUser) {
		return "agendamiento?faces-redirect=true&id=" + codigo + "&idUser=" + idUser;
	}

	public String editarRegistro1(int codigo) {
		return "solucionar?faces-redirect=true&id=" + codigo;
	}

	/**
	 * Metodo para la creacion de los clientes
	 *
	 * @return
	 */
	public String guardarCliente() {

		try {
			clion.guardarCliente(cliente);
			// servicio.setIdClienteTemp(cliente.getId());
			// seron.guardar(servicio);
			init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}



	public String getNuevoNumero() {
		return nuevoNumero;
	}

	public void setNuevoNumero(String nuevoNumero) {
		this.nuevoNumero = nuevoNumero;
	}

	public String getNuevoTipoTelefono() {
		return nuevoTipoTelefono;
	}

	public void setNuevoTipoTelefono(String nuevoTipoTelefono) {
		this.nuevoTipoTelefono = nuevoTipoTelefono;
	}

	public Telefono getTelefono() {
		return telefono;
	}

	public void setTelefono(Telefono telefono) {
		this.telefono = telefono;
	}

	public List<Telefono> getTelefonos1(Cliente cliente) {

		return telefonos;
	}


	
	/**
	 * Metodo para guadar el agendamiento
	 *
	 * @return
	 */

	public String listAntena() {
		// antenaC = "" + anton.getListadoAntena();
		return antenaC;
	}

	/**
	 * Metod para guardar los registros
	 *
	 * @return"
	 */
//	public String cargarDatosRegistro() {
//		try {
//			System.out.println("Llegando:::::111");
//			cliente.setId(registro.getCliente().getId());
//			registro.getCliente().setId(cliente.getId());
//			System.out.println("cliente id " + cliente.getId());
//			empleado.setId(registro.getEmpleado().getId());
//			registro.getEmpleado().setId(empleado.getId());
//			regon.guardar(registro);
//			System.out.println("imprime esto:   " + registro.getFechaHora());
//			init();
//
//			/*
//			 * System.out.println("Llegando:::::111");
//			 * cliente.setId(registro.getCliente().getId());
//			 * registro.getCliente().setId(cliente.getId());
//			 * System.out.println("cliente id " + cliente.getId());
//			 * empleado.setId(registro.getEmpleado().getId());
//			 * registro.getEmpleado().setId(codigo); System.out.println("imprime esto:   " +
//			 * registro.getFechaHora()); Cliente cli =
//			 * clion.getCliente(registro.getCliente().getId()); Empleado em=
//			 * empon.getEmpleado(codigo); registro.setCliente(cli);
//			 * registro.setEmpleado(em);
//			 * System.out.println("Se guardo correcto correctamente"); init();
//			 * 
//			 * 
//			 */
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return null;
//		// return "registro?faces-redirect=true";
//	}

	// Matriz de objetos

	/*
	 * Metodo para de radioButton del Modo de Servicio
	 */
	public static class ServicioFA {
		public String servicioLabel;
		public String servicioValue;

		public ServicioFA(String servicioLabel, String servicioValue) {
			this.servicioLabel = servicioLabel;
			this.servicioValue = servicioValue;
		}

		public String getServicioLabel() {
			return servicioLabel;
		}

		public void setServicioLabel(String servicioLabel) {
			this.servicioLabel = servicioLabel;
		}

		public String getServicioValue() {
			return servicioValue;
		}

		public void setServicioValue(String servicioValue) {
			this.servicioValue = servicioValue;
		}

	}

	public ServicioFA[] servicioLista;



	public List<Plan> getListadoPlanes() {
		if (servicioEdit.getTipoServicio() != null) {
			if (servicioEdit.getTipoServicio().equals("radio")) {
				List<Plan> planRadio = new ArrayList<Plan>();
				planRadio.add(listadoPlanesTmp.get(0));
				planRadio.add(listadoPlanesTmp.get(1));
				planRadio.add(listadoPlanesTmp.get(2));

				return planRadio;
			} else {
				List<Plan> planFibra = new ArrayList<Plan>();
				planFibra.add(listadoPlanesTmp.get(3));
				planFibra.add(listadoPlanesTmp.get(4));
				planFibra.add(listadoPlanesTmp.get(5));

				return planFibra;
			}
		} else
			return null;
	}

	public void setListadoPlanes(List<Plan> listadoPlanes) {
		this.listadoPlanes = listadoPlanes;
	}

	public EquipoServicioON getEqServOn() {
		return eqServOn;
	}

	public void setEqServOn(EquipoServicioON eqServOn) {
		this.eqServOn = eqServOn;
	}

	public Telefono getTelefonoMovilEdit() {
		return telefonoMovilEdit;
	}

	public void setTelefonoMovilEdit(Telefono telefonoMovilEdit) {
		this.telefonoMovilEdit = telefonoMovilEdit;
	}

	public Servicio getServicioEdit() {
		return servicioEdit;
	}

	public void setServicioEdit(Servicio servicioEdit) {
		this.servicioEdit = servicioEdit;
	}

	public String getPlanElegida() {
		return planElegida;
	}

	public String getServicioElegido() {
		return servicioElegido;
	}

	public void setServicioElegido(String servicioElegido) {
		this.servicioElegido = servicioElegido;
	}

	public void setPlanElegida(String planElegida) {
		this.planElegida = planElegida;
	}

	public String getRouterVendido() {
		return routerVendido;
	}

	public void setRouterVendido(String routerVendido) {
		this.routerVendido = routerVendido;
	}

	public List<String> getOpciones() {
		return opciones;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setOpciones(List<String> opciones) {
		this.opciones = opciones;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getAntenaTmp() {
		return antenaTmp;
	}

	public boolean isRendered() {
		return rendered;
	}

	public void setRendered(boolean rendered) {
		this.rendered = rendered;
	}

	public void setAntenaTmp(String antenaTmp) {
		this.antenaTmp = antenaTmp;
	}

	public Servicio getServicioTmp() {
		return servicioTmp;
	}

	public Telefono getTelefonoConveEdit() {
		return telefonoConveEdit;
	}

	public void setTelefonoConveEdit(Telefono telefonoConveEdit) {
		this.telefonoConveEdit = telefonoConveEdit;
	}

	public void setServicioTmp(Servicio servicioTmp) {
		this.servicioTmp = servicioTmp;
	}

	public String getEquipoElegido() {
		return equipoElegido;
	}

	public void setEquipoElegido(String equipoElegido) {
		this.equipoElegido = equipoElegido;
	}

	public String getNumContrato() {
		return numContrato;
	}

	public void setNumContrato(String numContrato) {
		this.numContrato = numContrato;
	}

	public String getPlanElegidoFibra() {
		return planElegidoFibra;
	}

	public void setPlanElegidoFibra(String planElegidoFibra) {
		this.planElegidoFibra = planElegidoFibra;
	}

	public EquipoServicio getEqServEdit() {
		return eqServEdit;
	}

	public List<String> getTipoServicios() {
		return tipoServicios;
	}

	public List<Plan> getListadoPlanesFibra() {
		return listadoPlanesFibra;
	}

	public void setListadoPlanesFibra(List<Plan> listadoPlanesFibra) {
		this.listadoPlanesFibra = listadoPlanesFibra;
	}

	public void setTipoServicios(List<String> tipoServicios) {
		this.tipoServicios = tipoServicios;
	}

	public void setEqServEdit(EquipoServicio eqServEdit) {
		this.eqServEdit = eqServEdit;
	}

	public List<Equipo> getListadoEquiposFibra() {
		return listadoEquiposFibra;
	}

	public void setListadoEquiposFibra(List<Equipo> listadoEquiposFibra) {
		this.listadoEquiposFibra = listadoEquiposFibra;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	// Metodo para actualizar los telefonos;

	public String getRouter() {
		return router;
	}

	public void setRouter(String router) {
		this.router = router;
	}

	public String getPlanTmp() {
		return planTmp;
	}

	public void setPlanTmp(String planTmp) {
		this.planTmp = planTmp;
	}


	public String tecnicoCampo() {
		empleados1 = "" + empon.getListadoEmpleado();

		return empleados1;
	}

	
	/*
	 * 
	 * 
	 * public void datoR() { System.out.println("datos locos " + empCon.getId());
	 * registro.setIdEmpleadoTemp(empCon.getId()); }
	 * 
	 * public String datoI() { System.out.println("Datos Instalacion " +
	 * empCon.getId()); instalacion.setCodigoEmpTemp(empCon.getId()); return
	 * "instalacion"; }
	 */
	public boolean validadorDeCedula(String cedula) {
		boolean cedulaCorrecta = false;

		try {

			if (cedula.length() == 10) // ConstantesApp.LongitudCedula
			{
				int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
				if (tercerDigito < 6) {
					// Coeficientes de validación cédula
					// El decimo digito se lo considera dígito verificador
					int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
					int verificador = Integer.parseInt(cedula.substring(9, 10));
					int suma = 0;
					int digito = 0;
					for (int i = 0; i < (cedula.length() - 1); i++) {
						digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
						suma += ((digito % 10) + (digito / 10));
					}

					if ((suma % 10 == 0) && (suma % 10 == verificador)) {
						cedulaCorrecta = true;
					} else if ((10 - (suma % 10)) == verificador) {
						cedulaCorrecta = true;
					} else {
						cedulaCorrecta = false;
					}
				} else {
					cedulaCorrecta = false;
				}
			} else {
				cedulaCorrecta = false;
			}
		} catch (NumberFormatException nfe) {
			cedulaCorrecta = false;
		} catch (Exception err) {
			System.out.println("Una excepcion ocurrio en el proceso de validadcion");
			cedulaCorrecta = false;
		}

		if (!cedulaCorrecta) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Cedula Incorrecta"));

		}
		return cedulaCorrecta;
	}

	public EquipoServicio getClienteip() {
		return clienteip;
	}

	public void setClienteip(EquipoServicio clienteip) {
		this.clienteip = clienteip;
	}


	public String cargarDatos() {
		try {

			clion.guardar(cliente);
			Plan planTmp = new Plan();
			Equipo equipo = new Equipo();

			if (planElegida != null)

				planTmp = planOn.buscarPlan(Integer.parseInt(planElegida));
			else
				planTmp = planOn.getPlanByName(this.planTmp);

			if (antenaElegida != null)

				equipo = eqOn.buscarAntena(Integer.parseInt(this.antenaElegida));
			else
				equipo = eqOn.getAntenaByName(this.antenaTmp);
			System.out.println("marron");
			this.servicioEdit.setRouterVendido(this.router);
			this.servicioEdit.setPlan(planTmp);

			// seron.guardar(servicioEdit);

			seron.update(this.servicioEdit);

			this.eqServEdit.setEquipo(equipo);

			eqServOn.actualizar(this.eqServEdit);

			this.antenaTmp = "";
			this.planTmp = "";
			this.router = "";
			init();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se edito corectamente el cliente"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public void validarCedula(FacesContext context, UIComponent comp, Object value) {
		System.out.println("inside validate method");

		String mno = (String) value;
		try {
			Integer.parseInt(mno);
		} catch (Exception e) {
			((UIInput) comp).setValid(false);
			FacesMessage message = new FacesMessage("Favor ingrese solo numeros");
			context.addMessage(comp.getClientId(context), message);
		}
		int i;
		int vect[] = new int[13];
		if (mno.equals("0000000000")) {
			FacesMessage message = new FacesMessage("No es una cedula valida");
			context.addMessage(comp.getClientId(context), message);
		} else if (mno.length() == 10) {
			System.out.println("Cedula");
			for (i = 0; i < mno.length(); i++) {
				vect[i] = Integer.parseInt(Character.toString(mno.charAt(i)));
			}
			if (vect[2] <= 6 && vect[2] >= 0) {
				int comprobar[] = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
				int suma = 0;
				for (i = 0; i < comprobar.length; i++) {
					vect[i] *= comprobar[i];
					if (vect[i] >= 10) {
						vect[i] -= 9;
					}
					suma += vect[i];
				}
				suma += vect[i];
				suma %= 10;
				if (suma != 0) {
					((UIInput) comp).setValid(false);
					FacesMessage message = new FacesMessage("No es una cedula valida");
					context.addMessage(comp.getClientId(context), message);
				}
			}
		} else {
			((UIInput) comp).setValid(false);
			FacesMessage message = new FacesMessage("Favor ingrese los 10 digitos");
			context.addMessage(comp.getClientId(context), message);
		}
	}
	public void loadData() {
		if (id == 0)
			return;
		cliente = clion.getCliente(id);


		List<Servicio> servicios = seron.getServicios(cliente);
		setServicioEdit(servicios.get(0));

		List<EquipoServicio> equipoServicios = eqServOn.getServicios(servicioEdit);
		setEqServEdit(equipoServicios.get(0));

		this.antenaTmp = eqServEdit.getEquipo().getModelo();
		this.planTmp = servicioEdit.getPlan().getTipoPlan();
		this.router = servicioEdit.getRouterVendido();

		if (servicioEdit.getTipoServicio().equals("radio")) {
			setListadoAntenas(eqOn.getListadoAntenas());
		} else {
			System.out.println("Servicio: " + servicioEdit.getTipoServicio());
			setListadoAntenas(eqOn.getListadoEquiposFibra());

		}
	}
	
	public String migrarDatos() {
		try {
			cliente.setMigrado(true);
			clion.guardar(cliente);
			Plan planTmp = new Plan();
			Equipo equipo = new Equipo();


				planTmp = planOn.buscarPlan(Integer.parseInt(this.planElegidoFibra));


				equipo = eqOn.buscarAntena(Integer.parseInt(this.equipoElegido));
			

			this.servicioEdit.setRouterVendido(this.router);
			this.servicioEdit.setPlan(planTmp);
			this.servicioEdit.setTipoServicio("fibra");

			// seron.guardar(servicioEdit);

			seron.update(this.servicioEdit);

			this.eqServEdit.setEquipo(equipo);

			eqServOn.actualizar(this.eqServEdit);

			this.antenaTmp = "";
			this.planTmp = "";
			this.router = "";
			System.out.println("si se migroo");
			init();

			String direccion="listCliente?faces-redirect=true";
			 //cedula = " ";
			return direccion;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public void validarNombre(FacesContext context, UIComponent componentToValidate, Object value)
			throws ValidatorException {
		FacesMessage message = null;
		// Retrieve the temporary value from the password field

		String mNombre = (String) value;

		/* Verificamos que no sea null */
		if (mNombre != "") {

			String[] parts = mNombre.split(" ");

			if (parts.length > 2) {
				message = new FacesMessage("No puede tener mas de dos nombres");
				throw new ValidatorException(message);
			}

			for (int i = 0; i < parts.length; i++) {
				int stringSize = parts[i].length();
				boolean isValidSize = (stringSize >= 3 && stringSize <= 30);

				if (!isValidSize && i == 0) {
					message = new FacesMessage("El primer nombre debe tener un minimo de 3 caracteres");
					throw new ValidatorException(message);
				}

				if (!isValidSize && i == 1) {
					message = new FacesMessage("El segundo nombre debe tener un minimo de 3 caracteres");
					throw new ValidatorException(message);
				}
			}

			/* 2ª Condición: que el tamaño sea >= 3 y <= 15 */

		}
	}

	public void validarApellido(FacesContext context, UIComponent componentToValidate, Object value)
			throws ValidatorException {
		FacesMessage message = null;
		// Retrieve the temporary value from the password field

		String mNombre = (String) value;

		/* Verificamos que no sea null */
		if (mNombre != "") {

			String[] parts = mNombre.split(" ");

			if (parts.length == 1) {
				message = new FacesMessage("No puede tener un apellido");
				throw new ValidatorException(message);
			}

			if (parts.length > 2) {
				message = new FacesMessage("No puede tener mas de dos apellidos");
				throw new ValidatorException(message);
			}

			for (int i = 0; i < parts.length; i++) {
				int stringSize = parts[i].length();
				boolean isValidSize = (stringSize >= 3 && stringSize <= 20);

				if (!isValidSize && i == 0) {
					message = new FacesMessage("El primer apellido debe tener entre 3 y 20 caracteres");
					throw new ValidatorException(message);
				}

				if (!isValidSize && i == 1) {
					message = new FacesMessage("El segundo apellido debe tener entre 3 y 20 caracteres");
					throw new ValidatorException(message);
				}
			}

			/* 2ª Condición: que el tamaño sea >= 3 y <= 15 */

		}
	}

	
	public void validarIp(FacesContext context, UIComponent componentToValidate, Object value)
			throws ValidatorException {

		FacesMessage message = null;
		// Retrieve the temporary value from the password field
		String ip = null;
		ip = (String) value;
		try {
			if (ip == null || ip.isEmpty()) {
				message = new FacesMessage("Ip vacio");
				throw new ValidatorException(message);
			}

			String[] parts = ip.split("\\.");
			if (parts.length != 4) {

				message = new FacesMessage("Rango invalido");
				throw new ValidatorException(message);
			}
			int aux = 0;
			for (String s : parts) {
				int i = Integer.parseInt(s);
				if (aux == 3) {
					if ((i < 2) || (i > 254)) {

						message = new FacesMessage("Ip Invalida, deberia ser de un host");
						throw new ValidatorException(message);

					}
				} else {
					if ((i < 0) || (i > 255)) {
						message = new FacesMessage("Ip Invalida");
						throw new ValidatorException(message);
					}

				}
				aux++;
			}
			if (ip.endsWith(".")) {
				message = new FacesMessage("Ip Invalida");
				throw new ValidatorException(message);
			}
		} catch (NumberFormatException nfe) {
			message = new FacesMessage("Ingrese numeros por favor");
			throw new ValidatorException(message);

		}

	}


	public List<Equipo> listarAntenas(String antenaElegido) {
		if (antenaElegido.equals("Radio")) {
			return eqOn.getListadoAntenas();
		} else {
			return eqOn.getListadoEquiposFibra();
		}
	}

	public List<Plan> listarPlan(String antenaElegido) {
		if (antenaElegido.equals("Radio")) {
			List<Plan> planRadio = new ArrayList<Plan>();
			planRadio.add(listadoPlanesTmp.get(0));
			planRadio.add(listadoPlanesTmp.get(1));
			planRadio.add(listadoPlanesTmp.get(2));

			return planRadio;
		} else {
			List<Plan> planFibra = new ArrayList<Plan>();
			planFibra.add(listadoPlanesTmp.get(3));
			planFibra.add(listadoPlanesTmp.get(4));
			planFibra.add(listadoPlanesTmp.get(5));

			return planFibra;
		}

	}


	

}