package com.qr.model;

import java.io.Serializable;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity	
public class Objeto implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String foto;
	private String color;
	private Long idCategoria;
	private String marca;
	private int cantidad;
	private double precio;
	private String descripcion;
	private Long idEspacio; // 
	
	
	public Objeto() {
		
	}
	
	public Objeto(String nombre, String foto, String color, Long idCategoria, String marca, int cantidad,
			double precio, String descripcion, Long idEspacio) {
		super();
		this.nombre = nombre;
		this.foto = foto;
		this.color = color;
		this.idCategoria = idCategoria;
		this.marca = marca;
		this.cantidad = cantidad;
		this.precio = precio;
		this.descripcion = descripcion;
		this.idEspacio = idEspacio;
	}

	

	public Objeto(Long id, String nombre, String foto, String color, Long idCategoria, String marca, int cantidad,
			double precio, String descripcion, Long idEspacio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.foto = foto;
		this.color = color;
		this.idCategoria = idCategoria;
		this.marca = marca;
		this.cantidad = cantidad;
		this.precio = precio;
		this.descripcion = descripcion;
		this.idEspacio = idEspacio;
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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Long getIdEspacio() {
		return idEspacio;
	}


	public void setIdEspacio(Long idEspacio) {
		this.idEspacio = idEspacio;
	}

	@Override
	public String toString() {
		return "Objeto [id=" + id + ", nombre=" + nombre + ", foto=" + foto + ", color=" + color + ", idCategoria="
				+ idCategoria + ", marca=" + marca + ", cantidad=" + cantidad + ", precio=" + precio + ", descripcion="
				+ descripcion + ", idEspacio=" + idEspacio + "]";
	}

}






