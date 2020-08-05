package pe.joedayz.springboot.servicio.productos.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pe.joedayz.springboot.servicio.commons.models.entity.Producto;
import pe.joedayz.springboot.servicio.productos.models.service.ProductoService;

@RestController
public class ProductoController {
	
	@Value("${server.port}")
	private Integer port;

	@Autowired
	private ProductoService productoService;
	
	@GetMapping("/listar")
	public List<Producto> findAll(){
		return this.productoService.findAll().stream().map(producto -> {
			producto.setPort(port);
			return producto;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id){
		Optional<Producto> productoOptional = this.productoService.findById(id);
		
		Producto producto = null;
		
		if(productoOptional.isPresent()) {
			producto = productoOptional.get();
			producto.setPort(port);
		}
		return producto;
	}
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		return this.productoService.save(producto);
	}
	
	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto, @PathVariable Long id) {
		Optional<Producto> productoOptional = this.productoService.findById(id);
		
		if(productoOptional.isPresent()) {
			Producto productoDb = productoOptional.get();
			productoDb.setNombre(producto.getNombre());
			productoDb.setPrecio(producto.getPrecio());
			
			return this.productoService.save(productoDb);
		}
		return producto;
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable Long id) {
		this.productoService.deleteById(id);
	}
	
}
