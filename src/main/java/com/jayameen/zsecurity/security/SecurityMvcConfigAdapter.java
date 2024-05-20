package com.jayameen.zsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Madan KN
 */
@Configuration
public class SecurityMvcConfigAdapter implements WebMvcConfigurer {

        @Autowired AuthorizationHandlerInterceptor authorizationHandlerInterceptor;

        @Autowired AuthExcludeUrls authExcludeUrls;

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
               registry.addInterceptor(authorizationHandlerInterceptor).excludePathPatterns(authExcludeUrls.getExcludeUrls());
        }

}
