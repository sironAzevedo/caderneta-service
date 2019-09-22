package br.com.caderneta.service.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.com.caderneta.service.common.enums.Perfil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "TB_ROLE", schema = "SGCP")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Getter
	@Setter
	@Column(name = "ID")
	@Enumerated(EnumType.STRING)
	private Perfil name;
	
	@Getter
	@Setter
	@ManyToMany(mappedBy = "roles")
	private List<UsuarioEntity> usuarios;
}
