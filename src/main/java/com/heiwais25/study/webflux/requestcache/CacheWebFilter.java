package com.heiwais25.study.webflux.requestcache;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CacheWebFilter implements WebFilter {
    private static byte[] EMPTY_BODY = new byte[0];

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return DataBufferUtils.join(exchange.getRequest().getBody())
                .map(dataBuffer -> {
                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(bytes);
                    DataBufferUtils.release(dataBuffer);
                    return bytes;
                })
                .defaultIfEmpty(EMPTY_BODY)
                .flatMap(bytes -> {
                    ServerHttpRequestDecorator decorator = new ServerHttpRequestDecorator(exchange.getRequest()) {
                        @Override
                        public Flux<DataBuffer> getBody() {
                            if(bytes.length > 0){
                                DataBufferFactory factory = exchange.getResponse().bufferFactory();
                                return Flux.just(factory.wrap(bytes));
                            }
                            return Flux.empty();
                        }
                    };
                    return chain.filter(exchange.mutate().request(decorator).build());
                });
    }
}
