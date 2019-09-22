package br.com.caderneta.service.configuracao.filters;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CadernetaPageAdapter implements WebMvcConfigurer {

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		Sort defaultSort = Sort.by("codigo").ascending();
		PageRequest defaultPage = PageRequest.of(0, 10, defaultSort);
		PageableHandlerMethodArgumentResolver page = new PageableHandlerMethodArgumentResolver();
		page.setMaxPageSize(20);
		page.setOneIndexedParameters(false);
		page.setPageParameterName("page");
		page.setSizeParameterName("size");
		page.setFallbackPageable(defaultPage);
		resolvers.add(page);

	}
}
