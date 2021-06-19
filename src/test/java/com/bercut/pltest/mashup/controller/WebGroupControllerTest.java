package com.bercut.pltest.mashup.controller;

import com.bercut.pltest.mashup.client.MsTeamsClient;
import com.bercut.pltest.mashup.client.TestRailClient;
import com.bercut.pltest.mashup.entity.Group;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ConcurrentModel;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebGroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void group() {
        try {
            mockMvc.perform(get("/group")).andExpect(status().isOk())
                    .andExpect(model().attributeExists("group"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGroup2() {
        RestTemplate restTemplate = new RestTemplate();
        WebGroupController webGroupController = new WebGroupController(
                new MsTeamsClient(restTemplate, new TestRailClient(new RestTemplate())));
        Group group = mock(Group.class);
        when(group.getCron()).thenReturn("1 1 1 1 1 1");
        when(group.getProjects()).thenReturn("foo");
        when(group.getWebUrl()).thenReturn("https://example.org/example");
        webGroupController.group(group);
        verify(group).getCron();
        verify(group).getProjects();
        verify(group).getWebUrl();
    }

    @Test
    void testGroup3() {
        RestTemplate restTemplate = new RestTemplate();
        WebGroupController webGroupController = new WebGroupController(
                new MsTeamsClient(restTemplate, new TestRailClient(new RestTemplate())));
        assertEquals("group", webGroupController.group(new ConcurrentModel()));
    }

}