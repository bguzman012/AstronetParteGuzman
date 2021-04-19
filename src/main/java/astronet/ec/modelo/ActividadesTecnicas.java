package astronet.ec.modelo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ActividadesTecnicas")


public class ActividadesTecnicas implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id_actividad")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private int id_actividad;
	
	@Column(name = "nombre_actividad")
	private String nombre_actividad;
	
	@Column(name = "puntaje_actividad")
	private double puntaje_actividad;

	
	
	public int getId_actividad() {
		return id_actividad;
	}
	public void setId_actividad(int id_actividad) {
		this.id_actividad = id_actividad;
	}
	public String getNombre_actividad() {
		return nombre_actividad;
	}
	public void setNombre_actividad(String nombre_actividad) {
		this.nombre_actividad = nombre_actividad;
	}
	public double getPuntaje_actividad() {
		return puntaje_actividad;
	}
	public void setPuntaje_actividad(double puntaje_actividad) {
		this.puntaje_actividad = puntaje_actividad;
	}
	@Override
	public String toString() {
		return "Actividad_Tecnica [id=" + id_actividad +" nombre "+nombre_actividad+" puntaje "+puntaje_actividad+"]";
	}
	@Override
	public int hashCode() {		
		return Objects.hash(this.id_actividad);
	}	
	
	/*
	 * RElacion Empleado con Visita
	 */
	
}