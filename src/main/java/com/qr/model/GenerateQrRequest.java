package com.qr.model;

import java.io.Serializable;

import org.hibernate.annotations.Table;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity	
public class GenerateQrRequest implements Serializable{
	
	// VARIABLES	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 private String nombreQR; 
	 private String propietario; 
	 private String descripcion ="";
	 private String emailCliente ="";
	 private String telefono;
	 private String direccion;
	 private Double recompensa;
	 private Long idObjeto; // Objeto al que estará asociado
	 private String qrImagePath;
	 
	//CONSTRUCTORES
	 public GenerateQrRequest() {
	}
	public GenerateQrRequest(String nombreQR, String propietario,String descripcion, String emailCliente, String telefono,
			String direccion, Double recompensa, Long idObjeto, String qrImagePath) {
		super();
		this.nombreQR = nombreQR;
		this.propietario=propietario;
		this.descripcion = descripcion;
		this.emailCliente = emailCliente;
		this.telefono = telefono;
		this.direccion = direccion;
		this.recompensa = recompensa;
		this.idObjeto = idObjeto;
		this.qrImagePath = qrImagePath;
	}

	public GenerateQrRequest(Long id, String nombreQR,String propietario, String descripcion, String emailCliente, String telefono,
			String direccion, Double recompensa, Long idObjeto, String qrImagePath) {
		super();
		this.id = id;
		this.nombreQR = nombreQR;
		this.propietario = propietario;
		this.descripcion = descripcion;
		this.emailCliente = emailCliente;
		this.telefono = telefono;
		this.direccion = direccion;
		this.recompensa = recompensa;
		this.idObjeto = idObjeto;
		this.qrImagePath = qrImagePath;
	}

	//GETTERS Y SETTERS
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreQR() {
		return nombreQR;
	}

	public void setNombreQR(String nombreQR) {
		this.nombreQR = nombreQR;
	}

	public String getPropietario() {
		return propietario;
	}


	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}


	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Double getRecompensa() {
		return recompensa;
	}

	public void setRecompensa(Double recompensa) {
		this.recompensa = recompensa;
	}

	public Long getidObjeto() {
		return idObjeto;
	}

	public void setidObjeto(Long idObjeto) {
		this.idObjeto = idObjeto;
	}
	
	public String getQrImagePath() {
		return qrImagePath;
	}

	public void setQrImagePath(String qrImagePath) {
		this.qrImagePath = qrImagePath;
	}

	// TOSTRING
	@Override
	public String toString() {
		return "GenerateQrRequest [id=" + id + ", nombreQR=" + nombreQR + ", propietario=" + propietario
				+ ", descripcion=" + descripcion + ", emailCliente=" + emailCliente + ", telefono=" + telefono
				+ ", direccion=" + direccion + ", recompensa=" + recompensa + ", objeto=" + idObjeto + ", qrImagePath="
				+ qrImagePath + "]";
	}

	
   
}
