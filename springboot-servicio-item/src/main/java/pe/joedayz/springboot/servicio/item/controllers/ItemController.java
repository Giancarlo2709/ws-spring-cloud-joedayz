package pe.joedayz.springboot.servicio.item.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import pe.joedayz.springboot.servicio.item.models.Item;
import pe.joedayz.springboot.servicio.item.models.Producto;
import pe.joedayz.springboot.servicio.item.models.service.ItemService;

@RestController
public class ItemController {
	
	private static Logger logger = LoggerFactory.getLogger(ItemController.class);

	@Autowired
	private Environment env;
	
	@Value("${configuracion.texto}")
	private String texto;
	
	@Autowired
	@Qualifier("serviceFeign")
	private ItemService itemService;
	
	@GetMapping("/listar")
	public List<Item> listar() {
		return this.itemService.findAll();
	}
	
	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return this.itemService.findById(id, cantidad);
	}
	public Item metodoAlternativo(Long id, Integer cantidad) {
		logger.info("llamada a metodo alternativo");
		return Item.builder()
				.producto(
					Producto.builder()
						.id(id)
						.createdAt(new Date())
						.nombre("Producto Por Defecto")
						.precio(200d)
						.build()
				)
				.cantidad(3)
				.build();
	}
	
	@GetMapping("/obtener-configuracion")
	public ResponseEntity<?> obtenerConfiguracion(@Value("${server.port}") String puerto) {
		logger.info(texto);
		
		Map<String, String> json = new HashMap<>();
		json.put("texto", texto);
		json.put("puerto", puerto);
		
		if(env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")) {
			json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
			json.put("autor.email", env.getProperty("configuracion.autor.email"));
		}
		
		return new ResponseEntity<Map<String,String>>(json, HttpStatus.OK);
	}

}
