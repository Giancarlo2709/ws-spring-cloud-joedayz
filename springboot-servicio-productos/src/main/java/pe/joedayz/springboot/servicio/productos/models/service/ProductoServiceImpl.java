package pe.joedayz.springboot.servicio.productos.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.joedayz.springboot.servicio.productos.models.dao.ProductoRepository;
import pe.joedayz.springboot.servicio.commons.models.entity.Producto;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository productoRepository;
	
	@Override
	public List<Producto> findAll() {
		return (List<Producto>) this.productoRepository.findAll();
	}

	@Override
	public Optional<Producto> findById(Long id) {
		return this.productoRepository.findById(id);
	}

	@Override
	public Producto save(Producto producto) {
		return this.productoRepository.save(producto);
	}

	@Override
	public void deleteById(Long id) {
		this.productoRepository.deleteById(id);		
	}

}
