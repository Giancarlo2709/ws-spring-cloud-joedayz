package pe.joedayz.springboot.servicio.item.models.service;

import java.util.List;

import pe.joedayz.springboot.servicio.item.models.Item;
import pe.joedayz.springboot.servicio.commons.models.entity.Producto;

public interface ItemService {
	
	List<Item> findAll();
	
	Item findById(Long id, Integer cantidad);
	
	Producto save(Producto producto);
	Producto update(Producto producto, Long id);
	
	void delete(Long id);

}
