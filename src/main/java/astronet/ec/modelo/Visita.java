package astronet.ec.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity implementation class for Entity: Visita
 *
 */
@Entity
@Table(name = "Visita")
public class Visita implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "vis_id")
	@NotNull
	private int id;

	public Visita(Cliente cliente, Registro registro, Empleado empleado) {
		super();
		this.cliente = cliente;
		this.registro = registro;
		this.empleado = empleado;
	}

	@OneToOne
	@JoinColumn(name="clivis_fk")
	//@JsonIgnore
	private Cliente cliente;
	
	@OneToOne
	@JoinColumn(name="agregistro_fk")
	@JsonIgnore
	private Registro registro ;
	

	public Registro getRegistro() {
		return registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}
	@Column(name = "vis_observaciones")
	@NotNull
	private String observaciones;
		
	@Column(name = "vis_chequear")
	private boolean chequeo= false;

	
	
	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public boolean isChequeo() {
		return chequeo;
	}

	public void setChequeo(boolean chequeo) {
		this.chequeo = chequeo;
	}

	@OneToOne
	@JoinColumn(name="empvis_fk")
	//@JsonIgnore
	private Empleado empleado;
	
	
	@OneToOne
	@JoinColumn(name="activadotros_fk")
	//@JsonIgnore
	private ActividadesTecnicas actividadestecnicas;
	
	
	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
	}





	public Cliente getCliente() {
		return cliente;
	}





	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}









	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}


	public ActividadesTecnicas getActividadestecnicas() {
		return actividadestecnicas;
	}

	public void setActividadestecnicas(ActividadesTecnicas actividadestecnicas) {
		this.actividadestecnicas = actividadestecnicas;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Visita other = (Visita) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Visita() {
		super();
	}
   
}