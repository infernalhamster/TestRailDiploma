package com.bercut.pltest.mashup.client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {TestRailClient.class, RestTemplate.class})
@ExtendWith(SpringExtension.class)
class TestRailClientTest {
    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private TestRailClient testRailClient;

    @Test
    void testSendGet() throws RestClientException {
        when(this.restTemplate.exchange(anyString(), any(),
                any(), (Class<Object>) any(), (Object[]) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        assertThrows(IllegalArgumentException.class,
                () -> this.testRailClient.sendGet(ApiName.GET_USERS, 1, Object.class));
        verify(this.restTemplate).exchange(anyString(), any(),
                any(), (Class<Object>) any(), (Object[]) any());
    }

    @Test
    void testSendGet2() throws RestClientException {
        ResponseEntity<Object> responseEntity = (ResponseEntity<Object>) mock(ResponseEntity.class);
        when(responseEntity.getStatusCodeValue()).thenReturn(1);
        when(this.restTemplate.exchange(anyString(), any(),
                any(), (Class<Object>) any(), (Object[]) any()))
                .thenReturn(responseEntity);
        assertThrows(IllegalArgumentException.class,
                () -> this.testRailClient.sendGet(ApiName.GET_USERS, 1, Object.class));
        verify(this.restTemplate).exchange(anyString(), any(),
                any(), (Class<Object>) any(), (Object[]) any());
        verify(responseEntity).getStatusCodeValue();
    }

    @Test
    void testSendGet3() throws RestClientException {
        when(this.restTemplate.exchange(anyString(), any(),
                any(), (Class<Object>) any(), (Object[]) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        assertThrows(IllegalArgumentException.class,
                () -> this.testRailClient.sendGet(ApiName.GET_USERS, Object.class));
        verify(this.restTemplate).exchange(anyString(), any(),
                any(), (Class<Object>) any(), (Object[]) any());
    }

    @Test
    void testSendGet4() throws RestClientException {
        ResponseEntity<Object> responseEntity = (ResponseEntity<Object>) mock(ResponseEntity.class);
        when(responseEntity.getStatusCodeValue()).thenReturn(1);
        when(this.restTemplate.exchange(anyString(), any(),
                any(), (Class<Object>) any(), (Object[]) any()))
                .thenReturn(responseEntity);
        assertThrows(IllegalArgumentException.class,
                () -> this.testRailClient.sendGet(ApiName.GET_USERS, Object.class));
        verify(this.restTemplate).exchange(anyString(), any(),
                any(), (Class<Object>) any(), (Object[]) any());
        verify(responseEntity).getStatusCodeValue();
    }
}

