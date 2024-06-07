package com.qr.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity	
public class CategoriaEspacio implements Serializable{

		@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String nombre;
		
		//constructores
		public CategoriaEspacio() {
			
		}

		public CategoriaEspacio(String nombre) {
			super();
			this.nombre = nombre;
		}

		//getters and setters
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

		//toString
		@Override
		public String toString() {
			return "Categoria [id=" + id + ", nombre=" + nombre + "]";
		}
		
		
	}

