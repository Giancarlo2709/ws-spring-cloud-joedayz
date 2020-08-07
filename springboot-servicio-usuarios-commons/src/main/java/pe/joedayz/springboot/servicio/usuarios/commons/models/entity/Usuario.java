package pe.joedayz.springboot.servicio.usuarios.commons.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = -6921301781757488974L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, length = 20)
	private String username;
	
	@Column(length = 60)
	private String password;
	
	private Boolean enabled;
	
	private String nombres;
	private String apellidos;
	
	@Column(unique = true, length = 100)
	private String email;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id"),
	inverseJoinColumns = @JoinColumn(name="role_id"),
	uniqueConstraints = {@UniqueConstraint (columnNames = {"usuario_id", "role_id"})})
	private List<Role> roles;
	
}
