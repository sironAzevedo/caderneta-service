package br.com.caderneta.service.models.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "ALL_USER_ACCOUNTS", schema = "SGCP")
public class DashboardEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @Setter
    @Column(name = "MES_ID")
    private Long codigo;

    @Getter
    @Setter
    @Column(name = "MES")
    private String mes;
    
    @Getter
    @Setter
    @Column(name = "ANO")
    private Integer ano;

    @Getter
    @Setter
    @Column(name = "SALARIO")
    private BigDecimal salario;

    @Getter
    @Setter
    @Column(name = "QTD_CONTAS")
    private Integer qtdConta;

    @Getter
    @Setter
    @Column(name = "TOTAL_GASTOS")
    private BigDecimal totalGastos;

    @Getter
    @Setter
    @Column(name = "SALDO_FINAL")
    private BigDecimal saldoFinal;
    
    @Getter
    @Setter
    @Column(name = "USUARIO")
    private BigDecimal idUsuario;
}
