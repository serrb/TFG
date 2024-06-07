package com.qr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qr.model.CategoriaEspacio;
import com.qr.model.Espacio;
import com.qr.repository.CategoriaEspacioRepository;
import com.qr.repository.EspacioRepository;

@Service
public class EspacioService {
	@Autowired
	private EspacioRepository repository;
	@Autowired
	private CategoriaEspacioRepository repositoryCat;
	
	//GUARDAR ESPACIO
	public Espacio guardarEspacio(Espacio espacio) {
        try {
            return repository.save(espacio);
        } catch (Exception e) {
            // Manejar la excepción
            e.printStackTrace();
            throw new RuntimeException("Error al guardar el Espacio: " + e.getMessage());
        }
    }
	
	// LISTAR ESPACIOS
	public List<Espacio> listarEspacios() {
		return repository.findAll();
	}
	
	// LISTAR ESPACIOS POR ID PADRE
		public List<Espacio> listarEspaciosPorIdPadre(Long idEspacioPadre) {
			return repository.findEspacioByIdEspacioPadre(idEspacioPadre);
		}
	
	//BUSCAR ESPACIO por ID:
			public Espacio buscarEspacioPorId(Long id) {
				return repository.findById(id).get();
			}

	// EDITAR ESPACIO
	public Espacio actualizarEspacio(Espacio espacio) {
		return repository.save(espacio);
	}
	
	// BORRAR ESPACIO
	public void eliminarEspacioPorId(Long id) {
		System.out.println("service" + id);
		repository.deleteById(id);
	}
	
	//CATEGORIAS
	//LISTAR CATEGORIAS
	public List<CategoriaEspacio> listarCategorias() {
		return repositoryCat.findAll();
	}
}
