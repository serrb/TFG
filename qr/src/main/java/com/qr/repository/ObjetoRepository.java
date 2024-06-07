package com.qr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qr.model.Objeto;

public interface ObjetoRepository extends JpaRepository<Objeto, Long> {
	List<Objeto> findObjetoByIdEspacio(Long idEspacio);
	List<Objeto> findObjetoByIdCategoria(Long idCategoria);
}
