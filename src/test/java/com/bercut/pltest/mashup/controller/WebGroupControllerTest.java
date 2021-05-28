package com.bercut.pltest.mashup.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

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
}