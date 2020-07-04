package jp.mirageworld.spring.terasoluna.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * データソースの設定.
 */
@Slf4j
@Configuration
@EnableJpaAuditing
@EnableJpaRepositories
@PropertySource("database")
public class DataSourceConfig {

	@Autowired
	Environment env;

	@Bean
	@SneakyThrows
	public DataSource dataSource() {
		log.debug("bean");
		if (env.containsProperty("database.jndi")) {
			return new JndiDataSourceLookup().getDataSource(env.getProperty("database.jndi", "jdbc/terasoluna"));
		} else {
			DriverManagerDataSource dataSource = new DriverManagerDataSource(
					env.getProperty("database.url"),
					env.getProperty("database.username"),
					env.getProperty("database.password"));

			dataSource.setDriverClassName(env.getProperty("database.driver.class"));

			return dataSource;
		}
	}

}
