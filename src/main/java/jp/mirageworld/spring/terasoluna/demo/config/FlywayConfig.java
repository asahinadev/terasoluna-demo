package jp.mirageworld.spring.terasoluna.demo.config;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@DependsOn("dataSource")
public class FlywayConfig implements InitializingBean {

	@Autowired
	DataSource dataSource;

	@Bean
	public Flyway flyway() {
		return Flyway.configure()
				.dataSource(dataSource)
				.locations("classpath:/db/migrations")
				.load();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.debug("{}", "afterPropertiesSet");
		flyway().migrate();
	}

}
