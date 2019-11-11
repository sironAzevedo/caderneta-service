package br.com.caderneta.service.common.util;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.jsoup.parser.Parser.unescapeEntities;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.springframework.beans.BeanUtils;

import br.com.caderneta.service.common.exceptions.NotFoundException;
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
		if (valor.equals(null) || valor.equals(new BigDecimal(0))) {
			return null;
		}
		
		DecimalFormat df = new DecimalFormat("###,###.00", new DecimalFormatSymbols(new Locale( "pt", "BR" )));
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

	public static String unescapeUntilNoHtmlEntityFound(final String value) {
		String unescaped = null;

		try {
			PolicyFactory factory = new HtmlPolicyBuilder().toFactory();
			if (isNotBlank(value)) {
				unescaped = unescapeEntities(value, Boolean.TRUE);
				if (!unescaped.equals(value)) {
					return unescapeUntilNoHtmlEntityFound(unescaped);
				} else {
					return unescapeEntities(factory.sanitize(unescaped), Boolean.TRUE);
				}
			}
			return unescaped;
		} catch (Exception e) {
			throw new NotFoundException(e.getLocalizedMessage());
		}
	}

	private CadernetaUtil() {
		super();
	}
}
