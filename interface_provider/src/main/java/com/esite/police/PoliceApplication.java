package com.esite.police;

import com.esite.blank.core.datasources.DynamicDataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ImportResource({"classpath:dubbo-cas-provider.xml"})
@Import({DynamicDataSourceConfig.class})
@ServletComponentScan
@EnableTransactionManagement
public class PoliceApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PoliceApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(PoliceApplication.class, args);
    }
}