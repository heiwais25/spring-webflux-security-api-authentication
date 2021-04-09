package com.heiwais25.study.webflux.requestcache;

import org.springframework.core.ResolvableType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class SampleServerAuthenticationConverter implements ServerAuthenticationConverter {
    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        return exchange.getRequest()
                .getBody()
                .map(Flux::just)
                .map(dataBufferFlux -> {
                    Jackson2JsonDecoder decoder = new Jackson2JsonDecoder();
                    ResolvableType type = ResolvableType.forType(AuthFormData.class);
                    return decoder.decodeToMono(dataBufferFlux, type, null, null).cast(AuthFormData.class);
                })
                .next()
                .flatMap(formData -> formData)
                .map(it -> new UsernamePasswordAuthenticationToken(it.getUser(), it.getPassword()));
    }

    public static class AuthFormData {
        private String user;
        private String password;

        public String getPassword() {
            return password;
        }

        public String getUser() {
            return user;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setUser(String user) {
            this.user = user;
        }
    }
}
