package com.gestion.stock.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = { "com.gestion.stock" })
public final class AppConfig extends WebMvcConfigurerAdapter {

//	@Bean
//	public MessageSource messageSource() {
//		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
//		source.setBasename("classpath:/i18n/applicationRessources");
//		source.setDefaultEncoding("UTF-8");
//		return source;
//	}
//
//	@Override
//	public Validator getValidator() {
//		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
//		validator.setValidationMessageSource(messageSource());
//		return validator;
//	}
	 
//	@Bean
//	public LocalValidatorFactoryBean validator() {
//		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
//		bean.setValidationMessageSource(messageSource());
//		return bean;
//	}
//
//	@Override
//	public Validator getValidator() {
//		return validator();
//	}

}
