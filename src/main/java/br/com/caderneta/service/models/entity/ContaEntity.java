package br.com.caderneta.service.models.entity;


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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "TB_CONTA", schema = "SGCP")
public class ContaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Getter
	@Setter
    @Column(name = "ID")
    @GeneratedValue(generator = "CONTA_ID_SEQ",  strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "CONTA_ID_SEQ", sequenceName = "SGCP.CONTA_ID_SEQ", allocationSize = 1)
    private Long codigo;

    @Getter
	@Setter
    @Column(name = "VALOR_CONTA")
    private BigDecimal valorConta;

    @Getter
	@Setter
    @Temporal(TemporalType.DATE)
    @Column(name = "DT_VENCIMENTO")
    private Date dataVencimento;

    @Getter
	@Setter
    @Temporal(TemporalType.DATE)
    @Column(name = "DT_PAGAMENTO")
    private Date dataPagamento;
    
    @Getter
	@Setter
	@Column(name = "QTD_PARCELAS")
    private Integer qtdParcelas;

    @Getter
	@Setter
    @Column(name = "COMENTARIO")
    private String comentario;

    @Getter
	@Setter
    @JsonBackReference
    @JoinColumn(name = "ID_MES", nullable = false)
    @ManyToOne(targetEntity = MesEntity.class, fetch = FetchType.LAZY)
    private MesEntity mes;
    
    @Getter
	@Setter
	@JoinColumn(name = "ID_STATUS_CONTA", nullable = false)
    @ManyToOne(targetEntity = StatusContaEntity.class,fetch = FetchType.LAZY)
    private StatusContaEntity status;

    @Getter
	@Setter
    @JoinColumn(name = "ID_TIPO_CONTA", nullable = false)
    @ManyToOne(targetEntity = TipoContaEntity.class,fetch = FetchType.LAZY)
    private TipoContaEntity tipoConta;
    
    @Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinTable(
			name = "TB_USUARIO_CONTA", 
			joinColumns = @JoinColumn(name = "ID_CONTA", referencedColumnName = "ID"),
			inverseJoinColumns = @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID"))
	private UsuarioEntity usuario;
     
    @Getter
	@Setter
    @Temporal(TemporalType.DATE)
    @Column(name = "DT_CADASTRO", updatable = false)
    private Date createdAt; 
}
