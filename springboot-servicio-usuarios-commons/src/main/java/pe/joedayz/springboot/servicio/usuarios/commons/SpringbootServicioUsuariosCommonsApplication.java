package pe.joedayz.springboot.servicio.usuarios.commons;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class SpringbootServicioUsuariosCommonsApplication {

}
