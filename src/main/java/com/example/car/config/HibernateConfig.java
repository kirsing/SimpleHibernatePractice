package com.example.car.config;

import org.hibernate.SessionFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {


        //@Bean // ставится над методом который выводит класс, объект этого класса создаётся и хранится в Spring Application
        public DataSource getDataSource() {
            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
            //dataSourceBuilder.driverClassName("org.h2.Driver");
//        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
//        dataSourceBuilder.driverClassName("org.postgresql.Driver");


//        dataSourceBuilder.url("jdbc:mysql://eu-cdbr-west-03.cleardb.net/heroku_bf2e67055ffabdc?reconnect=true");
//        dataSourceBuilder.url("jdbc:mysql://eu-cdbr-west-03.cleardb.net/heroku_bf2e67055ffabdc?reconnect=true");
            dataSourceBuilder.url("jdbc:postgresql://localhost:5432/postgres");
//        dataSourceBuilder.username("bb3444f3a67bf0");
            dataSourceBuilder.username("kirsing");
            dataSourceBuilder.password("219528k");
//        dataSourceBuilder.password("74b0a401");
            return dataSourceBuilder.build();
        }
        public Properties additionalProperties() {
            Properties properties = new Properties();
            properties.setProperty("hibernate.hbm2ddl.auto", "update");
            properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
            properties.setProperty("hibernate.current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
            return properties;
        }

//        @Bean
//        public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//            LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean() ;
//            emfb.setDataSource(getDataSource());
//            emfb.setPackagesToScan("com.example.car.model");
//            emfb.setJpaProperties(additionalProperties());
//            JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter() ;
//            emfb.setJpaVendorAdapter(vendorAdapter);
//            return emfb ;
//        }
@Bean
public SessionFactory getSessionFactory() throws IOException {
    LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
    factoryBean.setDataSource(getDataSource());
    factoryBean.setPackagesToScan("com.example.car.model");
    factoryBean.setHibernateProperties(additionalProperties());
    factoryBean.afterPropertiesSet();
    return factoryBean.getObject();
}

        @Bean
        public PlatformTransactionManager transactionManager(SessionFactory factory){
            HibernateTransactionManager transactionManager = new HibernateTransactionManager(factory);
            return transactionManager;
        }
        }


