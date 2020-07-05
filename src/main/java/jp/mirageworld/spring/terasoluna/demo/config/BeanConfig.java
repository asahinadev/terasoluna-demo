package jp.mirageworld.spring.terasoluna.demo.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.terasoluna.gfw.common.exception.ExceptionLogger;
import org.terasoluna.gfw.common.exception.SimpleMappingExceptionCodeResolver;
import org.terasoluna.gfw.web.exception.ExceptionLoggingFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class BeanConfig {

	private String encoder = "bcrypt";

	@Bean
	public PasswordEncoder passwordEncoder() {
		log.debug("bean {}", "dataSource");

		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("bcrypt", new BCryptPasswordEncoder());
		encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());

		return new DelegatingPasswordEncoder(encoder, encoders);

	}

	@Bean
	public SimpleMappingExceptionCodeResolver exceptionCodeResolver() {
		log.debug("bean {}", "exceptionCodeResolver");

		LinkedHashMap<String, String> exceptions = new LinkedHashMap<>();
		exceptions.put("InvalidTransactionTokenException", "e.xx.fw.7001");
		exceptions.put("BusinessException", "e.xx.fw.8001");
		exceptions.put("DataAccessException", "e.xx.fw.9002");

		SimpleMappingExceptionCodeResolver bean = new SimpleMappingExceptionCodeResolver();
		bean.setExceptionMappings(exceptions);
		bean.setDefaultExceptionCode("e.xx.fw.9001");

		return bean;

	}

	@Bean
	@DependsOn("exceptionCodeResolver")
	public ExceptionLogger exceptionLogger(SimpleMappingExceptionCodeResolver exceptionCodeResolver) {
		log.debug("bean {}", "exceptionLogger");

		ExceptionLogger bean = new ExceptionLogger();
		bean.setExceptionCodeResolver(exceptionCodeResolver);
		return bean;
	}

	@Bean
	@DependsOn("exceptionLogger")
	public ExceptionLoggingFilter exceptionLoggingFilter(ExceptionLogger exceptionLogger) {
		log.debug("bean {}", "exceptionLoggingFilter");

		ExceptionLoggingFilter bean = new ExceptionLoggingFilter();
		bean.setExceptionLogger(exceptionLogger);
		return bean;
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		log.debug("bean {}", "messageSource");

		ResourceBundleMessageSource bean = new ResourceBundleMessageSource();
		bean.setBasename("i18n/application-messages");
		return bean;
	}

}
