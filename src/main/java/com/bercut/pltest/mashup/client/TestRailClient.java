package com.bercut.pltest.mashup.client;

import lombok.Data;
import lombok.NonNull;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Data
@Component
public class TestRailClient {
    @NonNull
    private final RestTemplate restTemplate;

    @Value("${testrail.url}")
    private String urlApi;
    @Value("${testrail.username}")
    private String username;
    @Value("${testrail.password}")
    private String password;

    public <T> T sendGet(ApiName apiName, int parameter, Class<T> clazz) {
        return this.sendRequest(HttpMethod.GET, apiName.getMethod() + "/" + parameter, clazz);
    }

    public <T> T sendGet(ApiName apiName, Class<T> clazz) {
        return this.sendRequest(HttpMethod.GET, apiName.getMethod(), clazz);
    }

    private <T> T sendRequest(HttpMethod method, String apiName, Class<T> clazz) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        String auth = Base64.encodeBase64String((username + ":" + password).getBytes());
        headers.add("Authorization", "Basic " + auth);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<T> exchange = restTemplate.exchange(urlApi + apiName, method, entity, clazz);
        if (exchange.getStatusCodeValue() == 200) {
            return exchange.getBody();
        } else {
            throw new IllegalArgumentException("");
        }
    }
}
