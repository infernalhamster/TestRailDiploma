package com.bercut.pltest.mashup.client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {RestTemplate.class, TestRailClient.class, MsTeamsClient.class})
@ExtendWith(SpringExtension.class)
class MsTeamsClientTest {
    @Autowired
    private MsTeamsClient msTeamsClient;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private TestRailClient testRailClient;

    @Test
    void testSendRequest() {
        when(this.testRailClient.sendGet(any(), any()))
                .thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class,
                () -> this.msTeamsClient.sendRequest("https://example.org/example", "Projects Search"));
        verify(this.testRailClient).sendGet(any(), any());
    }
}

