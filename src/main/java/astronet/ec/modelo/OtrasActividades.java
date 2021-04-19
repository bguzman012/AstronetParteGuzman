package astronet.ec.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Otros")

public class OtrasActividades implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "otros_id")
	@NotNull
	private int id;
	
	/*
	 * Relacion Registro con Empleado
	 */
	@OneToOne
	@JoinColumn(name="empotros_fk")
	//@JsonIgnore
	private Empleado empleadoRegistra;
	
	@OneToOne
	@JoinColumn(name="empotrostecnico_fk")
	//@JsonIgnore
	private Empleado tecnico;
	
	
	@Column(name = "otros_actividad")
	@NotNull
	private String actividad;
	
	@Column(name = "otros_fecha")
	@NotNull
	private String fecha;
	
	

	public OtrasActividades() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Empleado getEmpleadoRegistra() {
		return empleadoRegistra;
	}

	public void setEmpleadoRegistra(Empleado empleadoRegistra) {
		this.empleadoRegistra = empleadoRegistra;
	}

	public Empleado getTecnico() {
		return tecnico;
	}

	public void setTecnico(Empleado tecnico) {
		this.tecnico = tecnico;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
	

}
