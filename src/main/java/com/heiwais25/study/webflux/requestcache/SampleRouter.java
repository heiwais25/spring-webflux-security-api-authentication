package com.heiwais25.study.webflux.requestcache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class SampleRouter {
    @Bean
    public RouterFunction<ServerResponse> route(SampleHandler handler) {
        return RouterFunctions.route(
                RequestPredicates.POST("/hello")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                handler::sample
        );
    }

}
