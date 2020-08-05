package pe.joedayz.springboot.servicio.productos.models.service;

import java.util.List;
import java.util.Optional;

import pe.joedayz.springboot.servicio.commons.models.entity.Producto;

public interface ProductoService {
	
	List<Producto> findAll();
	
	Optional<Producto> findById(Long id);
	
	Producto save(Producto producto);
	
	void deleteById(Long id);

}
