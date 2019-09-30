package br.com.caderneta.service.configuracao.seguranca.owasp;

import static br.com.caderneta.service.common.util.CadernetaUtil.unescapeUntilNoHtmlEntityFound;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

@JsonComponent
public class DefaultJsonDeerializer extends JsonDeserializer<String> implements ContextualDeserializer {

	@Override
	public String deserialize(JsonParser jsonParser, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		String value = jsonParser.getValueAsString();
		if (StringUtils.isEmpty(value)) {
			return value;
		} else {
			return unescapeUntilNoHtmlEntityFound(value);
		} 
	}

	@Override
	public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property)
			throws JsonMappingException {
		return this;
	}

}
