package br.com.caderneta.service.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.caderneta.service.models.dto.MesDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "TB_MES", schema = "SGCP")
public class MesEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @Setter
    @Column(name = "ID")
    @GeneratedValue(generator = "MES_ID_SEQ", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "MES_ID_SEQ", sequenceName = "SGCP.MES_ID_SEQ", allocationSize = 1)
    private Long codigo;

    @Getter
    @Setter
    @Column(name = "DESC_MES")
    private String dsMes;
    
    @JsonIgnore
    @OneToOne(mappedBy = "mes")
    private MesSalarioEntity salario;

    public MesEntity(MesDTO dto) {
        this.codigo = dto.getCodigo();
        this.dsMes = dto.getDsMes();
    } 
}
