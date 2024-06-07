package com.qr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qr.model.Espacio;

public interface EspacioRepository extends JpaRepository<Espacio, Long> {
    List<Espacio> findEspacioByIdEspacioPadre(Long idEspacioPadre);
}
