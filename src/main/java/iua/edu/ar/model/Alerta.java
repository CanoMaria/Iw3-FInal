package iua.edu.ar.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


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
	
	@ApiModelProperty(notes = "Estado de la alerta (si se envio o no el email)", required = false)
	@Column(length = 100)
	private EstadoAlerta estado= EstadoAlerta.NO_ENVIADO;
	
	@ApiModelProperty(notes = "Mails a los que enviar", required = false)
	@Column(length = 100)
	private String mail;
	
	@ApiModelProperty(notes = "Temperatura maxima permitida", required = false)
	@Column(length = 100)
	private double temperaturaMax;
	
	@ApiModelProperty(notes = "Datos en caso de alerta", required = false)
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orden_id")
	private Orden orden;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EstadoAlerta getEstado() {
		return estado;
	}

	public void setEstado(EstadoAlerta estado) {
		this.estado = estado;
	}


	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public double getTemperaturaMax() {
		return temperaturaMax;
	}

	public void setTemperaturaMax(double temperaturaMax) {
		this.temperaturaMax = temperaturaMax;
	}

	public Orden getOrden() {
		return orden;
	}

	public void setOrden(Orden orden) {
		this.orden = orden;
	}




	
	
	
}
