package com.qr.controller;

import com.google.zxing.WriterException;
import com.qr.model.Categoria;
import com.qr.model.CategoriaEspacio;
import com.qr.model.Espacio;
import com.qr.model.GenerateQrRequest;
import com.qr.model.Objeto;
import com.qr.service.EspacioService;
import com.qr.service.ObjetoService;
import com.qr.service.QrCodeService;
import com.qr.model.Respuesta;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins ="*")
@RestController
public class QrCodeResource {

	@Autowired
	 private QrCodeService qrService;
	@Autowired
	 private EspacioService espacioService;
	@Autowired
	 private ObjetoService objetoService;

	
	// 1.QRS
	//LISTAR QR'S
	@GetMapping(value="listarQRs",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<GenerateQrRequest> listarQRs() {
		//return service.listarClientes();
		return qrService.listarQRs();
	}
	
	//BORRAR QR
	@DeleteMapping(value="eliminarQR/{id}")
	public void eliminarPorId(@PathVariable("id") Long id) {
		qrService.eliminarQRPorId(id);
	}
	
	//BUSCAR QR POR ID
	@GetMapping(value="QRs/{id}")
		public GenerateQrRequest buscarQR(@PathVariable("id") long id,Model model) {
			GenerateQrRequest qr  =  qrService.buscarQrPorId(id);
			System.out.println(qr.toString());
			return qr;
	}
	
	//BUSCAR QR POR ID OBJETO
		@GetMapping(value="QRIdObjeto/{idObjeto}")
			public GenerateQrRequest buscarQRIdObjeto(@PathVariable("idObjeto") long idObjeto,Model model) {
				GenerateQrRequest qr  =  qrService.buscarQrPorIdObjeto(idObjeto);
				System.out.println(qr.toString());
				return qr;
		}
	
	//GUARDAR QR
	@PostMapping(value="guardarQR",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> guardarQR2(@RequestBody GenerateQrRequest qr, HttpServletResponse response) throws MissingRequestValueException, WriterException, IOException {
	    try {
	        Long id = qrService.guardarQR(qr);
	        return ResponseEntity.ok(id);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}

	
	//GUARDAR Y BORRAR QR
	@PostMapping(value="generarQR/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	 public void generateQr(@PathVariable("id") Long id, HttpServletResponse response) throws MissingRequestValueException, WriterException, IOException {
	     System.out.println("entraste");
	      response.setContentType(MediaType.IMAGE_JPEG_VALUE); //tipo de contenido de respuesta
	      qrService.generateQr(id, response.getOutputStream()); //establecemos una respuesta hhttp como flujo de salida para escribir el qr generado
	      //service.guardarQR(request);
	      response.getOutputStream().flush();
	  }
	   //
	//ACTUALIZAR QR
	@PutMapping(value="actualizarQR",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Respuesta>  guardarQR(@RequestBody GenerateQrRequest qr,HttpServletResponse response) throws MissingRequestValueException, WriterException, IOException {
		if (qr.getId()!=null){
	        // Si el Dni existe
			qrService.guardarQR(qr);
			return ResponseEntity.status(HttpStatus.OK).body(new Respuesta("QR Actualizado correctamente"));
	    } else {
	        // Si no se encuentra ningún cliente, puede guardar el cliente
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Respuesta("No se ha podido actualizar, intente de nuevo mas tarde"));
	    }
	}
	  
	//2.ESPACIOS
	
	//LISTAR ESPACIOS
		@GetMapping(value="listarEspacios",produces=MediaType.APPLICATION_JSON_VALUE)
		public List<Espacio> listarEspacios() {
			//return service.listarClientes();
			return espacioService.listarEspacios();
		}
		
		//LISTAR ESPACIOS POR ID PADRE
		@GetMapping(value="listarEspaciosPadre/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
		public List<Espacio> listarEspaciosPorPadre(@PathVariable("id") Long id) {
			//return service.listarClientes();
			return espacioService.listarEspaciosPorIdPadre(id);
		}
		
		//BORRAR ESPACIO
		@DeleteMapping(value="eliminarEspacio/{id}")
		public void eliminarEspacioPorId(@PathVariable("id") Long id) {
			espacioService.eliminarEspacioPorId(id);
		}
		
		//BUSCAR ESPACIO POR ID
		@GetMapping(value="espacios/{id}")
			public Espacio buscarEspacio(@PathVariable("id") long id,Model model) {
				Espacio espacio =  espacioService.buscarEspacioPorId(id);
				System.out.println(espacio.toString());
				return espacio;
		}
		
		//GUARDAR
		@PostMapping(value="guardarEspacio",produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Respuesta>  guardarEspacio(@RequestBody Espacio espacio,HttpServletResponse response) throws MissingRequestValueException, WriterException, IOException {
			if (espacio.getId()==null){
				espacioService.guardarEspacio(espacio);
				return ResponseEntity.status(HttpStatus.OK).body(new Respuesta("Espacio guardado correctamente"));
		    } else {
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Respuesta("No se ha podido guardar, intente de nuevo mas tarde"));
		    }
		}
		
		//ACTUALIZAR
				@PutMapping(value="actualizarEspacio",produces=MediaType.APPLICATION_JSON_VALUE)
				public ResponseEntity<Respuesta>  actualizarEspacio(@RequestBody Espacio espacio,HttpServletResponse response) throws MissingRequestValueException, WriterException, IOException {
					if (espacio.getId()!=null){
						espacioService.guardarEspacio(espacio);
						return ResponseEntity.status(HttpStatus.OK).body(new Respuesta("Espacio guardado correctamente"));
				    } else {
				        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Respuesta("No se ha podido guardar, intente de nuevo mas tarde"));
				    }
				}
				
		//LISTAR CATEGORIAS ESPACIO
				@GetMapping(value="listarCategoriasEsp",produces=MediaType.APPLICATION_JSON_VALUE)
				public List<CategoriaEspacio> listarCategorias() {
					//return service.listarClientes();
					return espacioService.listarCategorias();
				}
				
	
	//3.OBJETOS
				//LISTAR OBJETOS
				@GetMapping(value="listarObjetos",produces=MediaType.APPLICATION_JSON_VALUE)
				public List<Objeto> listarObjetos() {
					return objetoService.listarObjetos();
				}
				
				//LISTAR OBJETOS POR ID PADRE
				@GetMapping(value="listarObjetosIdEspacio/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
				public List<Objeto> listarObjetosPorPadre(@PathVariable("id") Long id) {
					//return service.listarClientes();
					return objetoService.listarObjetosPorIdEspacio(id);
				}
				
				//LISTAR OBJETOS POR ID CATEGORIA
				@GetMapping(value="listarObjetosIdCategoria/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
				public List<Objeto> listarObjetosPorIdCategoria(@PathVariable("id") Long id) {
					//return service.listarClientes();
					return objetoService.listarObjetosPorIdCategoria(id);
				}
				
				//BORRAR OBJETO
				@DeleteMapping(value="eliminarObjeto/{id}")
				public void eliminarObjetoPorId(@PathVariable("id") Long id) {
					objetoService.eliminarObjetoPorId(id);
				}
				
				//BUSCAR ESPACIO POR ID
				@GetMapping(value="objetos/{id}")
					public Objeto buscarObjeto(@PathVariable("id") long id,Model model) {
						Objeto objeto =  objetoService.buscarObjetoPorId(id);
						System.out.println(objeto.toString());
						return objeto;
				}
				
				//GUARDAR
				@PostMapping(value="guardarObjeto",produces=MediaType.APPLICATION_JSON_VALUE)
				public ResponseEntity<Respuesta>  guardarObjeto(@RequestBody Objeto objeto,HttpServletResponse response) throws MissingRequestValueException, WriterException, IOException {
					try {
						objetoService.guardarObjeto(objeto);
						return ResponseEntity.status(HttpStatus.OK).body(new Respuesta("Objeto guardado correctamente"));
				    } catch(Exception e) {
				        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Respuesta("No se ha podido guardar, intente de nuevo mas tarde"));
				    }
				}
				
				//ACTUALIZAR
						@PutMapping(value="actualizarObjeto",produces=MediaType.APPLICATION_JSON_VALUE)
						public ResponseEntity<Respuesta>  actualizarObjeto(@RequestBody Objeto objeto,HttpServletResponse response) throws MissingRequestValueException, WriterException, IOException {
							if (objeto.getId()!=null){
								objetoService.guardarObjeto(objeto);
								return ResponseEntity.status(HttpStatus.OK).body(new Respuesta("Objeto guardado correctamente"));
						    } else {
						        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Respuesta("No se ha podido guardar, intente de nuevo mas tarde"));
						    }
						}
				//LISTAR CATEGORIAS ESPACIO
				@GetMapping(value="listarCategoriasObj",produces=MediaType.APPLICATION_JSON_VALUE)
				public List<Categoria> listarCategoriasObj() {
					return objetoService.listarCategorias();
				}
				//BUSCAR CATEGORIA POR ID
				@GetMapping(value="categorias/{id}")
					public Categoria buscarCategoria(@PathVariable("id") long id,Model model) {
						Categoria categoria =  objetoService.buscarCategoriaPorId(id);
						System.out.println(categoria.toString());
						return categoria;
				}
}				
			
