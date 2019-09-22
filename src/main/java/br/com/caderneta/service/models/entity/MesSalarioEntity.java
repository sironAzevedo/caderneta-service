package br.com.caderneta.service.models.entity;

import static br.com.caderneta.service.common.util.CadernetaUtil.formatValor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.caderneta.service.models.dto.MesSalarioDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "TB_MES_SALARIO", schema = "SGCP")
public class MesSalarioEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Getter
	@Setter
	@Column(name = "ID")
	@GeneratedValue(generator = "MES_SALARIO_ID_SEQ", strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "MES_SALARIO_ID_SEQ", sequenceName = "SGCP.MES_SALARIO_ID_SEQ", allocationSize = 1)
	private Long codigo;

	@Getter
	@Setter
	@Column(name = "SALARIO")
	private BigDecimal valorSalario;
	
	@Getter
	@Setter
	@OneToOne(targetEntity = MesEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_MES", nullable = false, updatable = false)
	private MesEntity mes;
	
	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinTable(
			name = "TB_USUARIO_SALARIO", 
			joinColumns = @JoinColumn(name = "ID_SALARIO", referencedColumnName = "ID"),
			inverseJoinColumns = @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID"))
	private UsuarioEntity usuario;

	@Getter
	@Setter
	@Temporal(TemporalType.DATE)
	@Column(name = "DT_CADASTRO", updatable = false)
	private Date dataCadastro;
	
	@Getter
	@Setter
	@Temporal(TemporalType.DATE)
	@Column(name = "DT_UPDATE")
	private Date updateAt;

	public MesSalarioEntity(MesSalarioDTO dto) {
		this.codigo = dto.getCodigo();
		this.valorSalario = formatValor(dto.getValorSalario());
		this.mes = new MesEntity(dto.getMes());
		this.usuario = dto.getUsuario() != null ? new UsuarioEntity(dto.getUsuario()): null;
		this.dataCadastro = new Date();
	}
}
