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

/**
 * @author bryam
 *
 */
@Entity
@Table(name = "RegistroActividadesTecnicas")

public class RegistroActividadesTecnicas implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name = "registrosActTecnica_id")
	@NotNull
	private int id;
	
	/*
	 * Relacion Registro con Empleado
	 */
	@OneToOne
	@JoinColumn(name="empleadoTecnicoAct_fk")
	//@JsonIgnore
	private Empleado tecnico;
	
	@Column(name="mesActividad")
	@NotNull
	private String mes;
	
	@Column(name="yearActividad")
	@NotNull
	private int year;
	
	@Column(name="puntajeTotal")
	@NotNull
	private double puntajeTotal;
	
	@Column(name="actividadesRealizadasTotal")
	@NotNull
	private int activadesTotales;
	
	public RegistroActividadesTecnicas() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPuntajeTotal() {
		return puntajeTotal;
	}

	public void setPuntajeTotal(double puntajeTotal) {
		this.puntajeTotal = puntajeTotal;
	}

	public int getActivadesTotales() {
		return activadesTotales;
	}

	public void setActivadesTotales(int activadesTotales) {
		this.activadesTotales = activadesTotales;
	}

	public Empleado getTecnico() {
		return tecnico;
	}

	public void setTecnico(Empleado tecnico) {
		this.tecnico = tecnico;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
		
	
}
