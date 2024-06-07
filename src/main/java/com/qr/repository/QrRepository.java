package com.qr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.qr.model.GenerateQrRequest;

public interface QrRepository extends JpaRepository<GenerateQrRequest, Long> {
	List<GenerateQrRequest> findByNombreQR(@Param("nombreQR") String nombreQR);
	GenerateQrRequest findByIdObjeto(Long idObjeto);
}
