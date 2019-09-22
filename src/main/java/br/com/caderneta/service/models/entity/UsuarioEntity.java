package br.com.caderneta.service.models.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.caderneta.service.models.dto.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "TB_USUARIO", schema = "SGCP")
public class UsuarioEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Getter
	@Setter
	@Column(name = "ID")
	@GeneratedValue(generator = "USUARIO_ID_SEQ", strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "USUARIO_ID_SEQ", sequenceName = "SGCP.USUARIO_ID_SEQ", allocationSize = 1)
	private Long codigo;

	@Getter
	@Setter
	@Column(name = "NOME")
	private String nome;
 
	@Email
	@Getter
	@Setter
	@Column(name = "EMAIL")
	private String email;

	@Getter
	@Setter
	@JsonIgnore
	@Column(name = "SENHA")
	private String senha;
	
	@Getter
	@Setter
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinTable(name = "TB_USUARIO_ROLE", joinColumns = @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID"), 
										 inverseJoinColumns = @JoinColumn(name = "ID_ROLE", referencedColumnName = "ID"))
	private List<Role> roles;
	
	@Getter
	@Setter
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<ContaEntity> contas;
	
	@Getter
	@Setter
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<MesSalarioEntity> salario;

	@Getter
	@Setter
	@CreatedDate
	@Temporal(TemporalType.DATE)
	@Column(name = "DT_CADASTRO", updatable = false)
	private Date createdAt;

	@Getter
	@Setter
	@LastModifiedDate
	@Column(name = "DT_UPDATE")
	@Temporal(TemporalType.DATE)
	private Date updatedAt;

	public UsuarioEntity(UsuarioDTO user) {
		this.codigo = user.getCodigo();
		this.nome = user.getNome();
		this.email = user.getEmail();
		this.senha = user.getSenha();
	} 
}
