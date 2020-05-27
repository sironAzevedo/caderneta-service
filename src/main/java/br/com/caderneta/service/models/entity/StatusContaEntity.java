package br.com.caderneta.service.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "TB_STATUS_CONTA", schema = "SGCP")
public class StatusContaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(generator = "STATUS_CONTA_ID_SEQ", strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "STATUS_CONTA_ID_SEQ", sequenceName = "SGCP.STATUS_CONTA_ID_SEQ", allocationSize = 1)
	private Long codigo;

	@Column(name = "DESCRICAO")
	private String descricao;
}
