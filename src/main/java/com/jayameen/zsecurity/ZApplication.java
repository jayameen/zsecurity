package com.jayameen.zsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@PropertySource({"classpath:/application.yml"})
public class ZApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ZApplication.class, args);
	}

}
