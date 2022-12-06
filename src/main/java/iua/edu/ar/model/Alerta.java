package iua.edu.ar.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



@ApiModel(value = "Alerta", description = "Esta clase representa los datos necesarios para enviar una alerta por mail")
@Entity
@Table(name = "alerta")
public class Alerta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(notes = "Identificador de la alerta, clave autogenerada", required = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ApiModelProperty(notes = "Estado de la alarma", required = false)
	@Column(length = 100)
	private EstadoAlerta estado=EstadoAlerta.NO_ENVIADO;
	
	@ApiModelProperty(notes = "Usuario que acepto la alarma", required = false)
	@Column(length = 100)
	private String usrAceptador;
	
	@ApiModelProperty(notes = "Observaciones", required = false)
	@Column(length = 100)
	private String observaciones;
	
	@ApiModelProperty(notes = "Fecha en la que se acepto la alarma", required = false)
	@Column(length = 100)
	private Date fechaDeAceptacion;
	
	@ApiModelProperty(notes = "Identificador de la orden a la que pertenece la alerta", required = false)
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "orden_id")
	private Orden orden;

	

	public Orden getOrden() {
		return orden;
	}

	public void setOrden(Orden orden) {
		this.orden = orden;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsrAceptador() {
		return usrAceptador;
	}

	public void setUsrAceptador(String usrAceptador) {
		this.usrAceptador = usrAceptador;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Date getFechaDeAceptacion() {
		return fechaDeAceptacion;
	}

	public void setFechaDeAceptacion(Date fechaDeAceptacion) {
		this.fechaDeAceptacion = fechaDeAceptacion;
	}

	public EstadoAlerta getEstado() {
		return estado;
	}

	public void setEstado(EstadoAlerta estado) {
		this.estado = estado;
	}



	
}
