package pe.joedayz.springboot.servicio.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pe.joedayz.springboot.servicio.item.models.Producto;


@FeignClient(name = "servicio-productos")
public interface ProductoClientRest {
	
	@GetMapping("/listar")
	List<Producto> findAll();
	
	@GetMapping("/ver/{id}")
	Producto detalle(@PathVariable Long id);
	

}
