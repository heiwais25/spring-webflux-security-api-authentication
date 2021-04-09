package com.heiwais25.study.webflux.requestcache;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class SampleHandler {
    public Mono<ServerResponse> sample(ServerRequest request) {
        return request
                .bodyToMono(SampleModel.class)
                .flatMap(it -> ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                                .body(BodyInserters.fromValue(it.getValue())));
    }
}
