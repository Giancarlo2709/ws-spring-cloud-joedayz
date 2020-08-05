package pe.joedayz.springboot.servicio.item.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import pe.joedayz.springboot.servicio.commons.models.entity.Producto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
	
	private Producto producto;
	private Integer cantidad;
	
	public Double getTotal() {
		return this.producto.getPrecio() * this.cantidad.doubleValue();
	}
	
	

}
