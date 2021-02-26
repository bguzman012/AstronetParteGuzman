package astronet.servicios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.json.Json;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.mail.imap.Utility;


import astronet.ec.modelo.Agendamiento;

import astronet.ec.modelo.Cliente;
import astronet.ec.modelo.ClienteTemporal;
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
import astronet.ec.on.ClienteTemporalOn;
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
import astronet.ec.vista.ClienteController;

@Path("/astronet")
public class Servicios {

	@Inject
	private EmpleadoON empon;

	@Inject
	private InstalacionON inson;

	@Inject
	private AgendamientoON agon;

	@Inject
	private RegistroON regon;
	@Inject
	private EquipoServicioON eqSerOn;
	@Inject
	private ServicioON seron;

	@Inject
	private ClienteON clion;
	@Inject
	private VisitaON vison;

	// CLASS TO RECOVER DATA FROM TEMPORAL CLIENTS
	@Inject
	private ClienteTemporalOn clitempon;

	@Inject
	private TelefonoON telon;
	@Inject
	private EquipoOn eqOn;
	@Inject
	private PlanON planOn;
	@Inject
	private EquipoServicioON eqServOn;

	@Inject
	private ClienteController clienteController;
	

	Registro registro;

	/**
	 * Metodo del login
	 * 
	 * @param un
	 * @param pass
	 * @return
	 */
	@POST
	@Path("/login")
	@Produces("application/json")
	@Consumes("application/json")
	public Empleado login(Empleado empleado) {
		Empleado u = new Empleado();
		try {
			empleado = empon.login(empleado.getEmail(), empleado.getPassword());
			u.setId(empleado.getId());
			System.out.println("fun ");
			u.setEmail(empleado.getEmail());
			u.setPassword(empleado.getPassword());
			u.setNombre(empleado.getNombre());
			u.setDepartamento(empleado.getDepartamento());
			u.setCelular(empleado.getCelular());

		} catch (Exception e) {
			u.setEmail(e.getMessage());
		}
		return u;
	}

	/**
	 * Metodo para listar todas las visitas tecnicas
	 * 
	 * @return
	 */
	@GET
	@Path("/ip")
	@Produces("application/json")
	public List<String> getIp() {
		List<String> map = new ArrayList<String>();
		List<EquipoServicio> listaServicios = new ArrayList<EquipoServicio>();

		listaServicios = eqSerOn.getPing();

		for (EquipoServicio equipoServicio : listaServicios) {
			Empleado empleado = new Empleado();
			empleado = empon.getEmepleadoByEmail(equipoServicio.getUserEmpleado());
			map.add(empleado.getEmail() + "," + empleado.getPassword() + "," + equipoServicio.getIp());

		}

		return map;
	}

	/**
	 * Method temporal to install clients
	 */
	@GET
	@Path("/instalaciones")
	@Produces("application/json")
	public List<ClienteTemporal> getInstalacionesTecnico(@QueryParam("id") String id) {
		System.out.println("ID IONIC " + id);
		List<ClienteTemporal> clientestemp = new ArrayList<ClienteTemporal>();
		clientestemp = clitempon.filtradoLista(id);
		return clientestemp;

	}

	@GET
	@Path("/NombreAntenas")
	@Produces("application/json")
	public List<String> listarNombres() {
		List<String> listfinal = eqOn.nombreAntenas();
		return listfinal;
	}

	@POST
	@Path("/crearCliente")
	@Produces("application/json")
	@Consumes("application/json")
	public Response AddUser(ClienteTemporal cli) {
		System.out.println("INGRESO A GUARDAR CLIENTE");

		return Response.ok().build();
	}

	@POST
	@Path("/usuario")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> crearUsuario(String data) {
		JSONObject recoData = new JSONObject(data);
		System.out.println(recoData);
		Cliente cliN = new Cliente();
		Telefono tele = new Telefono();
		cliN.setCedula(recoData.getString("cedula"));
		cliN.setApellidos(recoData.getString("apellidos"));
		cliN.setNombre(recoData.getString("nombre"));
		cliN.setDireccionPrincipal(recoData.getString("direccionPrincipal"));
		cliN.setDireccionSecundaria(recoData.getString("direccionSecundaria"));
		cliN.setDireccionReferencia(recoData.getString("direccionReferencia"));
		cliN.setEmail(recoData.getString("email"));
		clion.guardar(cliN);
		// AVOID BUG OF UNSAVED CLIENT
		Cliente clisaved = clion.getClienteCedula(recoData.getString("cedula"));
		// SAVING NUMBERS OF CLIENT
		tele.setTipoTelefono("Convencional");
		tele.setTelNumero(recoData.getString("telefono"));
		tele.setCliente(clisaved);
		telon.guardar(tele);
		
		Plan planTmp = new Plan();
		Equipo antenaTmp = new Equipo();
		EquipoServicio eqServicio = new EquipoServicio();

		antenaTmp = eqOn.getAntenaByName(recoData.getString("antena"));
		System.out.println("ANTENA ENCONTRADA "+antenaTmp.getNombre());
		planTmp = planOn.buscarPlan(Integer.parseInt(recoData.getString("plan")));
		Servicio servicioTmp= new Servicio();
		servicioTmp.setTipoServicio("radio");
		servicioTmp.setNumeroContrato(recoData.getString("numerocontrato"));
		servicioTmp.setCliente(clisaved);
		servicioTmp.setFechaContrato(recoData.getString("fecha"));
		servicioTmp.setRouterVendido(recoData.getString("routervendido"));
		servicioTmp.setObservaciones(recoData.getString("observaciones"));
		servicioTmp.setPlan(planTmp);
		seron.guardar(servicioTmp);

		eqServicio.setSerial(recoData.getString("serial"));
		eqServicio.setPassword(recoData.getString("contrasena"));
		eqServicio.setIp(recoData.getString("ip"));
		eqServicio.setUserEmpleado("no");
		eqServicio.setPing("off");
		eqServicio.setEquipo(antenaTmp);
		eqServicio.setServicio(servicioTmp);
		eqServOn.crearI(eqServicio);
		return null;
	}

	/**
	 * Metodo para listar todas las visitas tecnicas
	 * 
	 * @return
	 */
	@GET
	@Path("/ipWinbox")
	@Produces("application/json")
	public List<String> getIpWinbox() {
		List<String> map = new ArrayList<String>();
		List<EquipoServicio> listaServicios = new ArrayList<EquipoServicio>();

		listaServicios = eqSerOn.getWinbox();
		for (EquipoServicio equipoServicio : listaServicios) {
			Empleado empleado = new Empleado();
			empleado = empon.getEmepleadoByEmail(equipoServicio.getUserEmpleado());
			map.add(empleado.getEmail() + "," + empleado.getPassword() + "," + equipoServicio.getIp() + "," + equipoServicio.getPassword());

		}

		return map;
	}

	@POST
	@Path("/notificar")
	@Produces("application/json")
	@Consumes("application/json")
	public Response createUser(EquipoServicio equipo) {
		if (equipo != null) {

			System.out.println(equipo.getIp());
			System.out.println(equipo.getUserEmpleado());
			EquipoServicio serviEquipo = new EquipoServicio();
			serviEquipo = eqSerOn.findByName(equipo.getIp());
			serviEquipo.setPing("off");
			serviEquipo.setUserEmpleado("no");
			eqSerOn.actualizar(serviEquipo);
		}

		return Response.ok(equipo).build();
	}

	/**
	 * Metodo para ver el listado de todas las instalaciones
	 * 
	 * @return
	 */
	@GET
	@Path("listInst")
	@Produces("application/json")

	public List<Instalacion> listarInstalacion() {
		return inson.getListadoInstalacion();
	}

	/**
	 * Metodo para listar todas las visitas tecnicas
	 * 
	 * @return
	 */
	@GET
	@Path("listRgVT")
	@Produces("application/json")
	public List<Registro> listarRgVT() {
		return regon.listadoRegistrosVT();
	}
//
//	@GET
//	@Path("listAG")
//	@Produces("application/json")
//	public List<Registro> listarAgendamiento(@QueryParam("nombre") String nombre) {
//		System.out.println("nombre IONIC " + nombre);
//		return vison.getVisitaByTecnico(nombre);
//	}
//	@GET
//	@Path("listAG")
//	@Produces("application/json")
//	public List<HashMap<String, String>> listarAgendamiento(@QueryParam("nombre") String nombre) {
//		HashMap<String, String> mapRegistro ;
//		List<HashMap<String, String>> array = new ArrayList<HashMap<String,String>>();
//		
//		
//		
//		
//		List<String> map = new ArrayList<String>();
//		List<Registro> listaServicios = new ArrayList<Registro>();
//		listaServicios = vison.getVisitaByTecnico(nombre);
//		System.out.println("nombre IONIC " + nombre);
//		
//		
//	
//		
//		for (Registro registro : listaServicios) {
//			mapRegistro = new HashMap<>(); 
//			mapRegistro.put("nombre",registro.getCliente().getNombre()); 
//				mapRegistro.put("apellido",registro.getCliente().getApellidos());
//				mapRegistro.put("problema",registro.getProblema());
//				mapRegistro.put("direccionPrincipal",registro.getCliente().getDireccionPrincipal());
//				mapRegistro.put("direccionSecundaria",registro.getCliente().getDireccionSecundaria());		
//				mapRegistro.put("direccionReferencia",registro.getCliente().getDireccionReferencia());
//				mapRegistro.put("latitud",registro.getCliente().getLatitud());
//				mapRegistro.put("longitud",registro.getCliente().getLongitud());
//				mapRegistro.put("id", String.valueOf(registro.getId()));
//				
//				array.add(mapRegistro);
//			  
//
//		}
//		
//		
//		return array;
//	}
	
	//SCORPION METHOD
	@GET
	@Path("listVisTecnicos")
	@Produces("application/json")
	public List<HashMap<String, String>> listarAgendamientoTech(@QueryParam("nombre") String nombre) {
		HashMap<String, String> mapRegistro ;
		List<HashMap<String, String>> array = new ArrayList<HashMap<String,String>>();
		
		List<String> map = new ArrayList<String>();
		List<Visita> listaServicios = new ArrayList<Visita>();
		listaServicios = vison.getVisitaByTecnico(nombre);
		System.out.println("nombre IONIC " + nombre);
			
		for (Visita vis : listaServicios) {
			mapRegistro = new HashMap<>(); 
			mapRegistro.put("nombre",vis.getCliente().getNombre()); 
				mapRegistro.put("apellido",vis.getCliente().getApellidos());
				mapRegistro.put("problema",vis.getRegistro().getProblema());
				mapRegistro.put("direccionPrincipal",vis.getCliente().getDireccionPrincipal());
				mapRegistro.put("direccionSecundaria",vis.getCliente().getDireccionSecundaria());		
				mapRegistro.put("direccionReferencia",vis.getCliente().getDireccionReferencia());
				mapRegistro.put("latitud",vis.getCliente().getLatitud());
				mapRegistro.put("longitud",vis.getCliente().getLongitud());
				mapRegistro.put("idvisita", String.valueOf(vis.getId()));
				mapRegistro.put("idregistro", String.valueOf(String.valueOf(vis.getRegistro().getId())));
				array.add(mapRegistro);
			  

		}
		
		
		return array;
	}
	//SCORPIONMETHOD TO CHANGE STATE OF VISIT AND REGISTER MARKED BY ING
	@POST
	@Path("changeState")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String changeStateJob(String data) {
		JSONObject recoData = new JSONObject(data);
		System.out.println(recoData);
		int idregister=Integer.valueOf(recoData.getString("idregistro"));
		int idvisita=Integer.valueOf(recoData.getString("idvisita"));
		try {
			Registro re= regon.consultarRegistro(idregister);
			re.setId(idregister);
			re.setAccion("SOLUCIONADOF");
			re.setChequeo(false);
			Visita vi = vison.consultarVIsita(idvisita);
			vi.setChequeo(true);		
			
			vison.guardar(vi);
			regon.guardar(re);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("si hizoooo");
		//registro.setCliente(cliente);

		return "Listo";
	}
	//Method to change state of client temp 
	@POST
	@Path("/ActualizarClienteTemporal")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String ActualizarTemporal(String data) {
		JSONObject recoData = new JSONObject(data);
		System.out.println(recoData);
		ClienteTemporal temp=clitempon.getClienteTemporal(recoData.getString("id"));
		temp.setEstado(true);
		clitempon.actualizar(temp);
		System.out.println("Se debe actualizar...");
		return "Actualizado";
		
	}
	

	

	@GET
	@Path("listIns")
	@Produces("application/json")
	public List<Instalacion> listarInstalacion(@QueryParam("nombre") String nombre) {
		return inson.getInstalacion(nombre);
	}

	/**
	 * @GET @Path("/listarAn") @Produces("application/json") public List<Antena>
	 *      getAntena(){ return anton.getListadoAntena(); }
	 * @return
	 */

	@GET
	@Path("/buscarIdVis")
	@Produces("application/json")
	public Registro getBuscarVis(@QueryParam("id") int id) {
		// registro=regon.getListadoClienteId(id);
		System.out.println("id" + id);
		return regon.getListadoClienteId(id);
	}

	@GET
	@Path("/buscarInsId")
	@Produces("application/json")
	public Instalacion getBuscarInsId(@QueryParam("id") int id) {
		return inson.getListadoInstalacionId(id);
	}

	/**
	 * 
	 * @param cliente
	 * @return
	 * 
	 * @PUT @Path("/actualizar") @Produces("application/json") @Consumes("application/json")
	 *      public Response update(Cliente cliente) {
	 * 
	 *      Response.ResponseBuilder builder = null; Map<String, String> data = new
	 *      HashMap<>(); Servicio ser=new Servicio(); Registro reg=new Registro();
	 *      reg.setCliente(cliente);
	 * 
	 * 
	 * 
	 * 
	 *      try {
	 * 
	 *      ser.setId(cliente.getServicio().get(0).getId());
	 *      ser.setNumeroContrato(cliente.getServicio().get(0).getNumeroContrato());
	 *      ser.setFechaContrato(cliente.getServicio().get(0).getFechaContrato());
	 *      ser.setPlan(cliente.getServicio().get(0).getPlan());
	 *      ser.setIp(cliente.getServicio().get(0).getIp());
	 *      ser.setPassword(cliente.getServicio().get(0).getPassword());
	 *      ser.setObservaciones(cliente.getServicio().get(0).getObservaciones());
	 * 
	 * 
	 *      ser.setId(ser.getId()); ser.setNumeroContrato(ser.getNumeroContrato());
	 *      ser.setFechaContrato(ser.getFechaContrato());
	 *      ser.setPlan(ser.getPlan()); ser.setIp(ser.getIp());
	 *      ser.setPassword(ser.getPassword());
	 *      ser.setObservaciones(ser.getObservaciones()); System.out.println("hola
	 *      funciona "+cliente.getId() +" hola 2 "+cliente);
	 *      //ser.getCliente().setId(cliente.getId());
	 * 
	 * 
	 *      seron.actualizar(ser); //regon.actualizar(reg);
	 *      clion.actualizar(cliente); //regon.actualizar(reg);
	 * 
	 *      System.out.println(cliente.getNombre()); data.put("Mensaje: ", "Dato
	 *      actualizado"); builder =
	 *      Response.status(Response.Status.OK).entity(data); } catch (Exception e)
	 *      { // TODO Auto-generated catch block e.printStackTrace();
	 *      data.put("Mensaje: ", e.getMessage()); builder =
	 *      Response.status(Response.Status.BAD_REQUEST).entity(data); }
	 * 
	 *      return builder.build();
	 * 
	 * 
	 *      }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @PUT @Path("/actualizarVisita") @Produces("application/json") @Consumes("application/json")
	 *      public Response updateReg(Agendamiento agendamiento) {
	 * 
	 *      Response.ResponseBuilder builder = null; Map<String, String> data = new
	 *      HashMap<>();
	 * 
	 * 
	 *      try {
	 * 
	 *      agon.actualizar(agendamiento);
	 *      System.out.println(agendamiento.isRealizado()); data.put("Mensaje: ",
	 *      "Dato actualizado"); builder =
	 *      Response.status(Response.Status.OK).entity(data); } catch (Exception e)
	 *      { // TODO Auto-generated catch block e.printStackTrace();
	 *      data.put("Mensaje: ", e.getMessage()); builder =
	 *      Response.status(Response.Status.BAD_REQUEST).entity(data); }
	 * 
	 *      return builder.build();
	 * 
	 * 
	 *      }
	 */

	@PUT
	@Path("/actualizarInstalacion")
	@Produces("application/json")
	@Consumes("application/json")
	public Response updateIns(Instalacion ins) {

		Response.ResponseBuilder builder = null;
		Map<String, String> data = new HashMap<>();

		Empleado empleado = new Empleado();
		try {

			empleado.setId(ins.getEmpleado().getId());
			empleado.setCedula(ins.getEmpleado().getCedula());
			empleado.setNombre(ins.getEmpleado().getNombre());
			empleado.setCelular(ins.getEmpleado().getCelular());
			empleado.setEmail(ins.getEmpleado().getEmail());
			empleado.setPassword(ins.getEmpleado().getPassword());
			empleado.setDepartamento(ins.getEmpleado().getDepartamento());
			System.out.println("empleado id " + empleado.getId());
			ins.getEmpleado().setId(empleado.getId());
			// empon.actualizar(empleado);
			inson.actualizar(ins);
			System.out.println(ins.isRealizado());
			data.put("Mensaje: ", "Dato actualizado");
			builder = Response.status(Response.Status.OK).entity(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			data.put("Mensaje: ", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(data);
		}

		return builder.build();

	}

}