package br.com.caderneta.service.common.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

public enum AtivoEnum {
	SIM("S", "ATIVO"), 
	NAO("N", "DESATIVADO");

	private String codigo;
	private String descricao;

	private AtivoEnum(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static AtivoEnum from(String cod) {
		if (cod == null) {
			return null;
		}

		for (AtivoEnum p : values()) {
			if (cod.equals(p.getCodigo())) {
				return p;
			}
		}

		return null;
	}

	@Converter(autoApply = true)
	public static class Mapeador implements AttributeConverter<AtivoEnum, String> {

		@Override
		public String convertToDatabaseColumn(AtivoEnum valor) {
			return valor.getCodigo();
		}

		@Override
		public AtivoEnum convertToEntityAttribute(String valor) {
			if (valor.equals("S")) {
				return SIM;
			} else if (valor.equals("N")) {
				return NAO;
			} else {
				return null;
			}

		}
	}

	@Override
	public String toString() {
		return codigo;
	}
}
