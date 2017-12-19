package dev.paie.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import dev.paie.spring.DataSourceMySQLConfig;
import dev.paie.spring.JpaConfig;

@Configuration
@ComponentScan({"dev.paie.service","dev.paie.util"})
@Import({JpaConfig.class,DataSourceMySQLConfig.class})
public class ServicesConfig {
}