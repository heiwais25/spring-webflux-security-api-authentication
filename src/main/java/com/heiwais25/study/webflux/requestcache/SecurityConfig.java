package com.heiwais25.study.webflux.requestcache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity security) {
        return security
                .csrf().disable()
                .authorizeExchange()
                .anyExchange().permitAll()
                .and()
                .httpBasic().disable()
                .build();
    }
}
