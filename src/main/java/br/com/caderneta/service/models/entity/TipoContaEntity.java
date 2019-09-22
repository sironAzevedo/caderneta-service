package br.com.caderneta.service.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.caderneta.service.models.dto.TipoContaDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "TB_TIPO_CONTA", schema = "SGCP")
public class TipoContaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @Setter
    @Column(name = "ID")
    @GeneratedValue(generator = "TIPO_CONTA_ID_SEQ", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "TIPO_CONTA_ID_SEQ", sequenceName = "SGCP.TIPO_CONTA_ID_SEQ", allocationSize = 1)
    private Long codigo;
    
    @Getter
    @Setter
    @Column(name = "NOME_TIPO_CONTA")
    private String tipo;

    @Getter
    @Setter
    @Column(name = "DESC_TIPO_CONTA")
    private String descricao;

    public TipoContaEntity(TipoContaDTO dto) {
        this.codigo = dto.getCodigo();
        this.tipo = dto.getTipo();
        this.descricao = dto.getDescricao();
    } 
}
