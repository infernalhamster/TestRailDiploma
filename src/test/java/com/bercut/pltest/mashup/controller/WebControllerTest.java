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
class WebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void users() {
        try {
            mockMvc.perform(get("/user")).andExpect(status().isOk())
                    .andExpect(model().attributeExists("users"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getUserRuns() {
        try {
            mockMvc.perform(get("/user/runs/1")).andExpect(status().isOk())
                    .andExpect(model().attributeExists("yourMap"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getUserCases() {
        try {
            mockMvc.perform(get("/user/cases/1")).andExpect(status().isOk())
                    .andExpect(model().attributeExists("mapCases"))
                    .andExpect(model().attributeExists("mapAllCases"))
                    .andExpect(model().attributeExists("search"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void search() {
        try {
            mockMvc.perform(get("/search")).andExpect(status().isOk())
                    .andExpect(model().attributeExists("users"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}