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
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import lombok.SneakyThrows;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories
@PropertySource("database")
public class BeanConfig {

	@Autowired
	Environment env;

	@Bean
	@SneakyThrows
	public DataSource dataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource(
				env.getProperty("database.url", "jdbc:h2:mem:test"),
				env.getProperty("database.username", "sa"),
				env.getProperty("database.password", ""));

		if (env.containsProperty("database.catalog")) {
			dataSource.setCatalog(env.getProperty("database.catalog"));
		}
		dataSource.setDriverClassName(env.getProperty("database.driver.class", "org.h2.Driver"));

		return dataSource;
	}

	public Map<String, String> jpaProperties() {

		Map<String, String> properties = new HashMap<>();

		defaultValue(properties, "hibernate.hbm2ddl.auto", "create");
		defaultValue(properties, "hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
		defaultValue(properties, "hibernate.connection.charSet", "UTF-8");
		defaultValue(properties, "hibernate.show_sql", "false");
		defaultValue(properties, "hibernate.format_sql", "false");
		defaultValue(properties, "hibernate.use_sql_comments", "false");
		defaultValue(properties, "hibernate.jdbc.batch_size", "30");
		defaultValue(properties, "hibernate.jdbc.fetch_size", "100");

		return properties;
	}

	private void defaultValue(Map<String, String> properties, String key, String value) {
		properties.put(key, env.getProperty(key, value));
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();

		bean.setPackagesToScan("jp.mirageworld.spring.terasoluna.demo");
		bean.setDataSource(dataSource());
		bean.setJpaVendorAdapter(jpaVendorAdapter());
		bean.setJpaPropertyMap(jpaProperties());

		return bean;

	}

	private JpaVendorAdapter jpaVendorAdapter() {

		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();

		String driver = env.getProperty("database.driver.class", "org.h2.Driver");
		if (StringUtils.containsIgnoreCase(driver, "MYSQL")) {
			adapter.setDatabase(Database.MYSQL);
		} else if (StringUtils.containsIgnoreCase(driver, "POSTGRESQL")) {
			adapter.setDatabase(Database.POSTGRESQL);
		} else if (StringUtils.containsIgnoreCase(driver, "ORACLE")) {
			adapter.setDatabase(Database.ORACLE);
		} else {
			adapter.setDatabase(Database.H2);
		}

		adapter.setShowSql(env.getProperty("hibernate.show_sql", Boolean.class, false));
		adapter.setGenerateDdl(env.getProperty("hibernate.generate.ddl", Boolean.class, false));

		return adapter;

	}
}
