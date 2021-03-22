package astronet.ec.vista;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import astronet.ec.modelo.Cliente;
import astronet.ec.modelo.Telefono;
import astronet.ec.on.ClienteON;

@ManagedBean
@ViewScoped
public class BeanListMigradosClientes implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Cliente cliente;
	public String inputName;
	
	public String getInputName() {
		return inputName;
	}

	public void setInputName(String inputName) {
		this.inputName = inputName;
	}


	private List<Cliente> listadoclientes;
	private List<Cliente> listadoclientesEliminados;
	
	public List<Cliente> getListadoclientesEliminados() {
		return listadoclientesEliminados;
	}

	public void setListadoclientesEliminados(List<Cliente> listadoclientesEliminados) {
		this.listadoclientesEliminados = listadoclientesEliminados;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}


	private List<Cliente> listaFiltrada;
	private String cedula;
	
	   
	   public BeanListMigradosClientes() {
		super();
	}
	   
		public String getName() {
		      return name;
		   }
		   
		   public void setName(String name) {
		      this.name = name;
		   }

		   public String getWelcomeMessage() {
		      return "Hello " + name;
		   }

		public List<Cliente> getListadoclientes() {
			return listadoclientes;
		}

		public void setListadoclientes(List<Cliente> listadoclientes) {
			this.listadoclientes = listadoclientes;
		}

		public List<Cliente> getListaFiltrada() {
			return listaFiltrada;
		}

		public void setListaFiltrada(List<Cliente> listaFiltrada) {
			this.listaFiltrada = listaFiltrada;
		}
		public String actualizar() {
			String direccion=null;
			 listadoclientesEliminados = clion.getListadoClienteMigrado();
			 direccion="listCliente?faces-redirect=true";
			 //cedula = " ";
			 return direccion;
		}

	   
	   @Inject
		private ClienteON clion;
	   
	   @PostConstruct
	   public void init() {
		   listadoclientes = clion.getListadoCliente();
		   listadoclientesEliminados = clion.getListadoClienteMigrado();
		  
	   }
	   
		public String buscarCedula() {
			try {
				if (this.cedula != null) {

					cliente = clion.getClienteCedula(this.cedula);
					listadoclientesEliminados.clear();
					listadoclientesEliminados.add(cliente);
					System.out.println("cliente cedula --> " + cliente.getCedula());
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Credenciales Correctas"));

				}
			} catch (Exception e) {
				// TODO: handle exception
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Credenciales Incorrectas"));

			}
			return null;

		}
		
		public List<String> getSugerencias(String enteredValue) {
			List<String> coincidencias = new ArrayList<String>();
			System.out.println(enteredValue);
			Cliente clie;

			for (int i = 0; i < listadoclientesEliminados.size(); i++) {

				clie = (Cliente) listadoclientesEliminados.get(i);
				String nombre = clie.getApellidos() + "/" + clie.getNombre();
				String apellido = clie.getApellidos();
				String nombres = clie.getNombre();

				try {
					if (nombres.toLowerCase().startsWith(enteredValue.toLowerCase())
							|| apellido.toLowerCase().startsWith(enteredValue.toLowerCase())) {

						coincidencias.add(nombre);
					}

				} catch (Exception e) {
					System.out.println("Exception " + e);
				}

			}

			return coincidencias;

		}
		
		public String findByNames() {

			try {
				String[] credenciales = inputName.split("/");
				String nombres = credenciales[1];
				String apellidos = credenciales[0];
				cliente = new Cliente();
				cliente = clion.buscarNombreApellido(nombres, apellidos);
				listadoclientes.clear();
				listadoclientes.add(cliente);

			} catch (Exception e) {
				// TODO: handle exception
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "NO SE PUDO ENCONTRAR AL CLIENTE"));
			}
			return null;

		}
		
		public String eliminarCliente(int id) {
			try {
				System.out.println("Este es el id a eliminar Cliente ---- " + id);
				String id2 = Integer.toString(id);
				cliente = clion.getClienteId(id2);
				cliente.setEliminado(true);
				clion.guardar(cliente);
				actualizar();
			} catch (Exception e) {
				// TODO: handle exception
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "NO SE PUDO eliminar AL CLIENTE"));
			}
			return null;

		}




}
