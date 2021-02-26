package astronet.ec.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ClienteTemporal")
@SequenceGenerator(
	    name="ClienteSeq",
	    sequenceName = "Cliente_SEQ",
	    initialValue = 6000,
	    allocationSize = 1
)
public class ClienteTemporal implements Serializable {

	/**
	 * HolaMundo
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cli_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ClienteSeq")
	@NotNull
	private int id;


	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(name = "cli_nombres")
	private  String nombre;


	@Column(name = "cli_apellidos")
	private  String apellidos;


	@Column(name = "cli_dirPrincipal")
	private String direccionPrincipal;

	@Column(name = "cli_dirSecundaria")
	private String direccionSecundaria;

	@Column(name = "cli_dirReferencia")
	private String direccionReferencia;
	
	@Column(name = "cli_fecha")
	private String fecha;
	
	@Column(name = "cli_Telefono")
	private String telefono;
	
	
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Column(name = "cli_estado")
	private boolean estado;
	
	@Column(name = "cli_fkempleado")
	private String fk_empleado;


	


	public String getFk_empleado() {
		return fk_empleado;
	}

	public void setFk_empleado(String fk_empleado) {
		this.fk_empleado = fk_empleado;
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

	

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
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





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteTemporal other = (ClienteTemporal) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ClienteTemporal [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccionPrincipal="
				+ direccionPrincipal + ", direccionSecundaria=" + direccionSecundaria + ", direccionReferencia="
				+ direccionReferencia + ", fecha=" + fecha + ", telefono=" + telefono + ", estado=" + estado
				+ ", fk_empleado=" + fk_empleado + "]";
	}

}
