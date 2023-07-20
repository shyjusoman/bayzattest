package com.mycom.platform.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mycom.platform.utils.JsonUtil;

@Configuration
public class PlatformBeanConfiguration {
	
	@Bean
	public JsonUtil jsonUtil(){
		 return new JsonUtil();
	}
} 
