package com.qr.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobClientBuilder;
import com.google.zxing.*; //usamos libreria ZXING de google
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.qr.model.GenerateQrRequest;
import com.qr.repository.QrRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class QrCodeService { //MÉTODOS A INTERACTUAR CON LA BASE DE DATOS

	@Autowired
	private QrRepository repository;
	
	
	//GUARDAR QR
	public Long guardarQR(GenerateQrRequest qr) {
        try {
           repository.save(qr);
           return qr.getId();
        } catch (Exception e) {
            // Manejar la excepción
            e.printStackTrace();
            throw new RuntimeException("Error al guardar el QR: " + e.getMessage());
        }
    }
	
	// LISTAR QRS
	public List<GenerateQrRequest> listarQRs() {
		return repository.findAll();
	}
	
	//BUSCAR QR por ID:
	public GenerateQrRequest buscarQrPorId(Long id) {
			return repository.findById(id).get();
	}
			
	//BUSCAR QR por ID:
	public GenerateQrRequest buscarQrPorIdObjeto(Long idObjeto) {
			return repository.findByIdObjeto(idObjeto);
	}

	// EDITAR QRS
	public GenerateQrRequest actualizarQr(GenerateQrRequest qr) {
		return repository.save(qr);
	}
	
	// BORRAR QRS
	public void eliminarQRPorId(Long id) {
		System.out.println("service" + id);
		repository.deleteById(id);
	}
	
	//GENERAR QR
	/*OutputStream es una clase abstracta en Java que representa un flujo de salida de bytes.
	 *  Sirve como una interfaz para escribir datos de bytes, lo que significa que se puede utilizar para enviar datos desde un programa a algún destino externo,*/
	
	public void generateQr(Long id, OutputStream outputStream) throws WriterException, IOException {
		
		//2.Generamos QR con la URL asociada al ID
		 Long qrId = id;
		 String url = "https://myinventoryweb.azurewebsites.net/"; // futura URL
		 String url2= url.concat(qrId.toString());
		 System.out.println("EL ID QUE LE HA ASOCIADO LA BASE DE DATOS ES "+ qrId);
		
	     BitMatrix bitMatrix = new QRCodeWriter().encode(url2, BarcodeFormat.QR_CODE, 200, 200); // representa la matriz de bits del código QR generado. Esta matriz contiene la información necesaria para representar el diseño del código QR, como la posición de los módulos negros y blancos que forman el código QR.
	     MatrixToImageWriter.writeToStream(bitMatrix, "jpeg", outputStream ); //matriz de bits + formato + flujo salida(http)
	   
	    //3.Guardamos la imgen del QR en azure Blob Storage
	     String blobUrl = saveToAzureBlob(bitMatrix, qrId.toString());
	     
	    //4. Actualizamos el QR en la base de datos, con la referencia al Blob de azure

	     GenerateQrRequest qr = buscarQrPorId(id);
	     qr.setQrImagePath(blobUrl);
	     
	     guardarQR(qr);	
	}

	private String saveToAzureBlob(BitMatrix bitMatrix, String id) {
	    try {
	        // Convertir la imagen del QR a un arreglo de bytes
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        MatrixToImageWriter.writeToStream(bitMatrix, "jpeg", outputStream);
	        byte[] qrImageBytes = outputStream.toByteArray();

	        // Construir el cliente del blob
	        BlobClient blobClient = new BlobClientBuilder()
	            .connectionString("DefaultEndpointsProtocol=https;" +
	                              "AccountName=tfg2024;" +
	                              "AccountKey=bf50owRWR/TXjBI7jxNcpCecJzNDvzzKiJ2XCV3qg7x1OxnKVH0MIHQ/Lc+/ARyZPatgQXRiR1AT+AStb1rlXg==;" +
	                              "BlobEndpoint=https://tfg2024.blob.core.windows.net/;")
	            .containerName("imagenesqr")
	            .blobName("qr_" + id + ".jpeg")
	            .buildClient();

	        // Subir la imagen del QR al blob
	        blobClient.upload(new ByteArrayInputStream(qrImageBytes), qrImageBytes.length, true);

	        // Devolver la URL del blob creado
	        return blobClient.getBlobUrl();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	//
	
	
}
