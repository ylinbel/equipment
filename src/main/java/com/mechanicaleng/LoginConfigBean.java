package com.mechanicaleng;

import com.mechanicaleng.login.LoginHandler;
import com.mechanicaleng.login.ServerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginConfigBean implements WebMvcConfigurer {

	@Autowired
	private LoginHandler loginHandler;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginHandler).addPathPatterns("/**")
				.excludePathPatterns(ServerConstants.LOGIN_PATH)
				.excludePathPatterns(ServerConstants.REGISTRY_PATH);
	}
}