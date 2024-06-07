package com.qr.model;

import java.io.Serializable;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity	
public class Espacio implements Serializable{

	//un espacio puede estar formado por objetos y por otros espacios
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	@Column(name = "id_espacio_padre", nullable = true)
	private Long idEspacioPadre; // espaciopadre (es null si es el raiz)
	private Long idCategoria;
	
	//CONSTRUCTORES
	public Espacio() {
	}
	
	public Espacio(Long id, String nombre, Long idEspacioPadre, Long idCategoria) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.idEspacioPadre = idEspacioPadre;
		this.idCategoria = idCategoria;
	}

	public Espacio(String nombre, Long idEspacioPadre, Long idCategoria) {
		super();
		this.nombre = nombre;
		this.idEspacioPadre = idEspacioPadre;
		this.idCategoria = idCategoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Long getIdEspacioPadre() {
		return idEspacioPadre;
	}


	public void setIdEspacioPadre(Long idEspacioPadre) {
		this.idEspacioPadre = idEspacioPadre;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}


	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}


	@Override
	public String toString() {
		return "Espacio [id=" + id + ", nombre=" + nombre + ", idEspacioPadre=" + idEspacioPadre + ", idCategoria="
				+ idCategoria + "]";
	}

}