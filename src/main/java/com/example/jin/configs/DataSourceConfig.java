package com.example.jin.configs;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
// @ConfigurationProperties(prefix="spring.datasource")
public class DataSourceConfig {

    ///// Data Source Config Database /////
    @Bean
    public DataSource dataSource() {
        System.out.println("JIN WORK ON DATASOURCE");
        
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/jin_spring_boot");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;

    }
}

