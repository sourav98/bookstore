package com.cg.bookstore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import springfox.documentation.builders.RequestHandlerSelectors;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Sprint1Application extends SpringBootServletInitializer 
{

	final static Logger LOGGER = LogManager.getLogger(Sprint1Application.class);
	public static void main(String[] args)
	{
		SpringApplication.run(Sprint1Application.class, args);
		LOGGER.info("Application Started");
	}

	@Bean
	public Docket productApi()
	{
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.cg.bookstore")).build();
	}

}
