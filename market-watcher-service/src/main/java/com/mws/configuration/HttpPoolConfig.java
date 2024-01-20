package com.mws.configuration;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class HttpPoolConfig {
    @Bean
    @Scope("singleton")
    public HttpClient httpClient() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(100);
        connectionManager.setDefaultMaxPerRoute(2);

        return HttpClients.custom()
                .setConnectionManager(connectionManager)
                .build();
    }
}

/*
    More about HTTP Client Connection Pooling:
    https://www.baeldung.com/httpclient-connection-management
 */

/*  Reference for RequestInterceptor if needed

public class HTTPClientRequestInterceptor implements RequestInterceptor {

    @Override
    public void intercept(HttpRequest request) throws IOException {
        request.setHeader("User-Agent", "Spring Boot HTTP Connection Pool");
    }
}

 */

