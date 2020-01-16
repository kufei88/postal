package com.jishengsoft.postal;

import javax.servlet.MultipartConfigElement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.jishengsoft.mapper")
public class PostalApplication extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer  {
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PostalApplication.class);
    }
	
	@Bean
	public MultipartConfigElement mutipartConfigElement(){
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("500MB");
		factory.setMaxRequestSize("500MB");
		return factory.createMultipartConfig();
	}

	public static void main(String[] args) {
		SpringApplication.run(PostalApplication.class, args);
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		// TODO Auto-generated method stub
		container.setPort(8888);  
	}
}
