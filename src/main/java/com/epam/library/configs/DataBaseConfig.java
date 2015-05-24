package com.epam.library.configs;

import java.util.Locale;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.epam.library.repository")
public class DataBaseConfig {

    @Value("${db.driver}")
    private String PROPERTY_NAME_DATABASE_DRIVER;

    @Value("${db.password}")
    private String PROPERTY_NAME_DATABASE_PASSWORD;
    @Value("${db.url}")
    private String PROPERTY_NAME_DATABASE_URL;
    @Value("${db.username}")
    private String PROPERTY_NAME_DATABASE_USERNAME;

    @Value("${hibernate.dialect}")
    private String PROPERTY_NAME_HIBERNATE_DIALECT ;
    @Value("${hibernate.show_sql}")
    private String PROPERTY_NAME_HIBERNATE_SHOW_SQL;
    @Value("${entitymanager.packages.to.scan}")
    private String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN;
    @Value("${hibernate.hbm2ddl.auto}")
    private String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO;
    @Value("${hibernate.connection.useUnicode}")
    private String PROPERTY_NAME_HIBERNATE_CONNECTION_USEUNICODE;
    @Value("${hibernate.connection.characterEncoding}")
    private String PROPERTY_NAME_HIBERNATE_CONNECTION_CHARACTERENCODING;
    @Value("${hibernate.connection.charSet}")
    private String PROPERTY_NAME_HIBERNATE_CONNECTION_CHARSET;

    @Bean
    public DataSource dataSource() {
        Locale.setDefault(new Locale("en"));
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(PROPERTY_NAME_DATABASE_DRIVER);
        dataSource.setUrl(PROPERTY_NAME_DATABASE_URL);
        dataSource.setUsername(PROPERTY_NAME_DATABASE_USERNAME);
        dataSource.setPassword(PROPERTY_NAME_DATABASE_PASSWORD);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);
        entityManagerFactoryBean.setPackagesToScan(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN);

        entityManagerFactoryBean.setJpaProperties(hibProperties());

        return entityManagerFactoryBean;
    }

    private Properties hibProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", PROPERTY_NAME_HIBERNATE_DIALECT);
        properties.put("hibernate.show_sql", PROPERTY_NAME_HIBERNATE_SHOW_SQL);

        properties.put("hibernate.connection.useUnicode",
                PROPERTY_NAME_HIBERNATE_CONNECTION_USEUNICODE);
        properties.put("hibernate.connection.characterEncoding",
                PROPERTY_NAME_HIBERNATE_CONNECTION_CHARACTERENCODING);
        properties.put("hibernate.connection.charSet",
                PROPERTY_NAME_HIBERNATE_CONNECTION_CHARSET);

        properties.put("hibernate.hbm2ddl.auto", PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO);
        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}