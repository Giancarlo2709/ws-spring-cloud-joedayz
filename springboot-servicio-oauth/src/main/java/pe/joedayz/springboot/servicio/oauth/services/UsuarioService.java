package pe.joedayz.springboot.servicio.oauth.services;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pe.joedayz.springboot.servicio.oauth.clients.UsuarioFeignClient;
import pe.joedayz.springboot.servicio.oauth.security.UsuarioAdicionalService;
import pe.joedayz.springboot.servicio.usuarios.commons.models.entity.Usuario;


@Service
public class UsuarioService implements UserDetailsService, UsuarioAdicionalService {
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
	private UsuarioFeignClient usuarioClient;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioClient.findByUsername(username);
		
		if(Objects.isNull(usuario)) {
			logger.error("Error en el login, no existe el usuario " + username + " en el sistema");
			throw new UsernameNotFoundException("Error en el login, no existe el usuario " + username + " en el sistema");
		}
		
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
		
		logger.info("Usuario Autenticado");
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(),
				true, true, true, authorities);		
	}

	@Override
	public Usuario findByUsername(String username) {
		return usuarioClient.findByUsername(username);
	}

}
