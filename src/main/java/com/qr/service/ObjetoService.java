package com.qr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qr.model.Categoria;
import com.qr.model.CategoriaEspacio;
import com.qr.model.Objeto;
import com.qr.repository.CategoriaObjetoRepository;
import com.qr.repository.ObjetoRepository;


@Service
public class ObjetoService {

	@Autowired
	private ObjetoRepository repository;
	@Autowired
	private CategoriaObjetoRepository repositoryCat;
	
	//GUARDAR OBJETO
	public Objeto guardarObjeto(Objeto objeto) {
        try {
            return repository.save(objeto);
        } catch (Exception e) {
            // Manejamos la excepción
            e.printStackTrace();
            throw new RuntimeException("Error al guardar el Objeto: " + e.getMessage());
        }
    }
	
	// LISTAR OBJETOS
	public List<Objeto> listarObjetos() {
		return repository.findAll();
	}
	
	// LISTAR OBEJTOS POR ID ESPACIO
	public List<Objeto> listarObjetosPorIdEspacio(Long idEspacio) {
		return repository.findObjetoByIdEspacio(idEspacio);
	}
	
	// LISTAR OBEJTOS POR ID CATEGORIA
		public List<Objeto> listarObjetosPorIdCategoria(Long idCategoria) {
			return repository.findObjetoByIdCategoria(idCategoria);
		}
	
	//BUSCAR OBJETO por ID:
			public Objeto buscarObjetoPorId(Long id) {
				return repository.findById(id).get();
			}

	// EDITAR OBJETOS
	public Objeto actualizarObjeto(Objeto objeto) {
		return repository.save(objeto);
	}
	
	// BORRAR OBJETOS
	public void eliminarObjetoPorId(Long id) {
		System.out.println("service" + id);
		repository.deleteById(id);
	}
	
	//CATEGORIAS
	//LISTAR CATEGORIAS
	public List<Categoria> listarCategorias() {
		return repositoryCat.findAll();
	}
	//BUSCAR CATEGORIA por ID:
	public Categoria buscarCategoriaPorId(Long id) {
		return repositoryCat.findById(id).get();
	}
}
