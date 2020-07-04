package jp.mirageworld.spring.terasoluna.demo.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import lombok.extern.slf4j.Slf4j;

/**
 * JPA の設定.
 */
@Slf4j
@Configuration
@EnableJpaAuditing
@EnableJpaRepositories
@PropertySource("database")
public class JpaConfig {

	@Autowired
	Environment env;

	@Autowired
	DataSource dataSource;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		log.debug("bean");

		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();

		bean.setPackagesToScan("jp.mirageworld.spring.terasoluna.demo");
		bean.setDataSource(dataSource);
		bean.setJpaVendorAdapter(jpaVendorAdapter());
		bean.setJpaPropertyMap(properties());

		return bean;

	}

	private JpaVendorAdapter jpaVendorAdapter() {

		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();

		String driver = env.getProperty("database.driver.class");
		if (StringUtils.containsIgnoreCase(driver, "com.mysql")) {
			adapter.setDatabase(Database.MYSQL);
		} else {
			adapter.setDatabase(Database.H2);
		}

		adapter.setShowSql(env.getProperty("hibernate.show_sql", Boolean.class, false));
		adapter.setGenerateDdl(env.getProperty("hibernate.generate.ddl", Boolean.class, false));

		return adapter;

	}

	private Map<String, String> properties() {

		Map<String, String> properties = new HashMap<>();

		config(properties, "hibernate.hbm2ddl.auto", "create");
		config(properties, "hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
		config(properties, "hibernate.connection.charSet", "UTF-8");
		config(properties, "hibernate.show_sql", "false");
		config(properties, "hibernate.format_sql", "false");
		config(properties, "hibernate.use_sql_comments", "false");
		config(properties, "hibernate.jdbc.batch_size", "30");
		config(properties, "hibernate.jdbc.fetch_size", "100");

		if (log.isTraceEnabled()) {
			properties.entrySet().forEach(e -> {
				log.trace("hibernate.properties {} = {}", e.getKey(), e.getValue());
			});
		}

		return properties;
	}

	private void config(Map<String, String> properties, String key, String value) {
		properties.put(key, env.getProperty(key, value));
	}

}
