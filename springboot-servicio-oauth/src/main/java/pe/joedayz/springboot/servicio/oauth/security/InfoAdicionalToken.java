package pe.joedayz.springboot.servicio.oauth.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import pe.joedayz.springboot.servicio.usuarios.commons.models.entity.Usuario;

@Component
public class InfoAdicionalToken implements TokenEnhancer {
	
	@Autowired
	private UsuarioAdicionalService usuarioService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		// TODO nombres, apellidos, Email, etc
		
		Map<String, Object> infoAdicional = new HashMap<>();
		
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		infoAdicional.put("nombres", usuario.getNombres());
		infoAdicional.put("apellidos", usuario.getApellidos());
		infoAdicional.put("email", usuario.getEmail());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(infoAdicional);
		
		return accessToken;
	}

}
