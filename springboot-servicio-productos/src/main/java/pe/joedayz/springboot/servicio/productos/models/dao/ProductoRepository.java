package pe.joedayz.springboot.servicio.productos.models.dao;

import org.springframework.data.repository.CrudRepository;
import pe.joedayz.springboot.servicio.productos.models.entity.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long> {

}
