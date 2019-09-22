package br.com.caderneta.service.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class CadernetaUtil {

	public static final Date formatDateToDate(Date date) {
		Date today = null;
		String pattern = "dd/MM/yyyy";
		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			String d = format.format(date);
			today = format.parse(d);
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		return today;
	}

	public static final String formatDateToString(Date date) {
		String dateFormat = null;
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		dateFormat = format.format(date);
		return dateFormat;
	}

	public static final String formatValor(BigDecimal valor) {
		if(valor.equals(null) || valor.equals(new BigDecimal(0))) {
			return null;
		}
		
		DecimalFormat df = new DecimalFormat("###,###.00");
		return df.format(valor);
	}
	
	public static final BigDecimal formatValor(String valor) {
		String num = valor.replace(".", "").replace(",", ".");		
		return new BigDecimal(num);
	}
	
	public static final Object parseObject(Object orig, Object dest) {
		Object obj = dest;
		BeanUtils.copyProperties(orig, obj);
		return obj;
	}

	private CadernetaUtil() {
		super();
	}
}
