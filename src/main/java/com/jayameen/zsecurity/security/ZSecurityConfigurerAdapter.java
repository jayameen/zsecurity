package com.jayameen.zsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Madan KN
 */
@Configuration
public class ZSecurityConfigurerAdapter {

    @Autowired AuthExcludeUrls authExcludeUrls;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http
            .authorizeRequests((authorizeRequests) ->  {
                try {
                    authExcludeUrls.getExcludeUrls().forEach(url -> authorizeRequests.antMatchers(url).permitAll());
                    authorizeRequests.anyRequest().authenticated().and().oauth2ResourceServer().jwt();
                    http.csrf().disable();
                    http.cors().disable();
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
            });
        return http.build();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}