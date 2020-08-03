package pe.joedayz.springboot.servicio.productos.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import pe.joedayz.springboot.servicio.productos.models.entity.Producto;
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
	
}
