package pe.joedayz.springboot.servicio.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pe.joedayz.springboot.servicio.usuarios.commons.models.entity.Usuario;

@FeignClient(name = "servicio-usuarios")
public interface UsuarioFeignClient {
	
	@GetMapping("/usuarios/search/buscar-username")
	public Usuario findByUsername(@RequestParam("username") String username);

}
