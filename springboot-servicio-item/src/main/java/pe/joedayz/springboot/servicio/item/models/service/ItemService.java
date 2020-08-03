package pe.joedayz.springboot.servicio.item.models.service;

import java.util.List;

import pe.joedayz.springboot.servicio.item.models.Item;

public interface ItemService {
	
	List<Item> findAll();
	
	Item findById(Long id, Integer cantidad);

}
