package pe.joedayz.springboot.servicio.item.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.joedayz.springboot.servicio.item.clientes.ProductoClientRest;
import pe.joedayz.springboot.servicio.item.models.Item;

@Service("serviceFeign")
public class ItemServiceFeign implements ItemService {
	
	@Autowired
	private ProductoClientRest clienteFeign;

	@Override
	public List<Item> findAll() {
		return this.clienteFeign.findAll()
				.stream()
				.map(p-> new Item(p,1))
				.collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		return new Item(this.clienteFeign.detalle(id), cantidad);
	}

}
