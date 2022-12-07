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

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



@ApiModel(value = "Alerta Datos", description = "Esta clase representa los datos necesarios para enviar una alerta por mail")
@Entity
@Table(name = "alerta_datos")
public class AlertaDatos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(notes = "Identificador de la alerta, clave autogenerada", required = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ApiModelProperty(notes = "Mails a los que enviar", required = false)
	@Column(length = 100)
	private String mail;
	
	@ApiModelProperty(notes = "Temperatura maxima permitida", required = false)
	@Column(length = 100)
	private double temperaturaMax;
	
	@ApiModelProperty(notes = "Datos en caso de alerta", required = false)
	@OneToOne(mappedBy="alertaDatos")
	
	private Orden orden;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
