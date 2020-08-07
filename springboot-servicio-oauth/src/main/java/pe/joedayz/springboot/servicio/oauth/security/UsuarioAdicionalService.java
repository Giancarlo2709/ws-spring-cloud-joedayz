package pe.joedayz.springboot.servicio.oauth.security;

import pe.joedayz.springboot.servicio.usuarios.commons.models.entity.Usuario;

public interface UsuarioAdicionalService {
	
	Usuario findByUsername(String username);

}
