package jp.mirageworld.spring.terasoluna.demo.config;

import javax.validation.Validator;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ValidatorConfig {

	@Bean
	public Validator validator(MessageSource messageSource) {
		log.debug("bean {}", "validator");
		Validator validator = new LocalValidatorFactoryBean();
		return validator;
	}
}
