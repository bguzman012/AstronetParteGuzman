package astronet.ec.vista;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import astronet.ec.modelo.ClienteTemporal;
import astronet.ec.modelo.Empleado;
import astronet.ec.on.ClienteTemporalOn;
import astronet.ec.on.EmpleadoON;


@ManagedBean
@ViewScoped
public class ClienteTemController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ClienteTemporal cliente = new ClienteTemporal();
	private Empleado empleado = new Empleado();
	private List<ClienteTemporal> listaClinetesTemporales;
	private String tecnicoElegido;

	public String getTecnicoElegido() {
		return tecnicoElegido;
	}


	public void setTecnicoElegido(String tecnicoElegido) {
		this.tecnicoElegido = tecnicoElegido;
	}

	@ManagedProperty(value = "#{login}")
	private EmpleadoController empCon;
	
	@Inject
	private ClienteTemporalOn clitemon;
	

	@Inject
	private EmpleadoON empon;
	
	
	@PostConstruct
	public void init() {
		cliente = new ClienteTemporal();
		listaClinetesTemporales = clitemon.getListadoCliente();
		
		
	}


	public EmpleadoController getEmpCon() {
		return empCon;
	}


	public void setEmpCon(EmpleadoController empCon) {
		this.empCon = empCon;
	}


	public ClienteTemporalOn getClitemon() {
		return clitemon;
	}


	public void setClitemon(ClienteTemporalOn clitemon) {
		this.clitemon = clitemon;
	}


	public List<ClienteTemporal> getListaClinetesTemporales() {
		return listaClinetesTemporales;
	}


	public void setListaClinetesTemporales(List<ClienteTemporal> listaClinetesTemporales) {
		this.listaClinetesTemporales = listaClinetesTemporales;
	}


	public ClienteTemporal getCliente() {
		return cliente;
	}


	public void setCliente(ClienteTemporal cliente) {
		this.cliente = cliente;
	}
	
	/***
	 * metodo guardar las instalaciones temporales
	 */
	public void crearCliTemporal() {
		System.out.println("este es  el tecnico asignar:::"+ tecnicoElegido);
		
		if (tecnicoElegido.equals("SinAsignar")) {
			System.out.println("no ha elegido el tecnico");
			cliente.setFk_empleado(tecnicoElegido);
			clitemon.guardar(cliente);
			cliente=null;
			actualizar();
			
		}else {
			System.out.println("ha elegido el tecnico");
				empleado = empon.getEmpleadobyName(tecnicoElegido);
				String id =Integer.toString(empleado.getId()) ;
				cliente.setFk_empleado(id);
				System.out.println("este es el id" + id);
				clitemon.guardar(cliente);
				cliente=null;
				actualizar();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Instalacion previa guardada Correctamente"));
		}
	}
	public void actualizarAsignacion(int id,String clienteSelect) {
		cliente = clitemon.getCliTemId(id);
		if (tecnicoElegido.equals("SinAsignar")) {
			System.out.println("no ha elegido el tecnico");
			cliente.setFk_empleado(tecnicoElegido);	
			clitemon.actualizar(cliente);
			actualizar();
		}else {
			System.out.println("ha elegido el tecnico");
				empleado = empon.getEmpleadobyName(tecnicoElegido);
				String id2 =Integer.toString(empleado.getId()) ;
				cliente.setFk_empleado(id2);	
				clitemon.actualizar(cliente);
				actualizar();
		}	
		
	}
	
	
	public String actualizar() {
		String direccion=null;
		listaClinetesTemporales = clitemon.getListadoCliente();
		 direccion="listClientesTemporales?faces-redirect=true";
		 //cedula = " ";
		 return direccion;
	}
	
	public String extraerNombreTecnico(String id) {
		try {
			int id2 = Integer.parseInt(id);
			empleado = empon.getEmpleado(id2);
			return empleado.getNombre() ;
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	
	}


}
