package pe.joedayz.springboot.servicio.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import pe.joedayz.springboot.servicio.commons.models.entity.Producto;


@FeignClient(name = "servicio-productos")
public interface ProductoClientRest {
	
	@GetMapping("/listar")
	List<Producto> findAll();
	
	@GetMapping("/ver/{id}")
	Producto detalle(@PathVariable Long id);
	
	@PostMapping("/crear")
	Producto crear(@RequestBody Producto producto);
	
	@PutMapping("/editar/{id}")
	Producto update(@RequestBody Producto producto, @PathVariable Long id);
	
	@DeleteMapping("/eliminar/{id}")
	void delete(@PathVariable Long id);
}
