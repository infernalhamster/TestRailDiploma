package com.bercut.pltest.mashup.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GroupTest {
    @Test
    public void testConstructor() {
        Group actualGroup = new Group();
        actualGroup.setCron("Cron");
        actualGroup.setProjects("Projects");
        actualGroup.setWebUrl("https://example.org/example");
        assertEquals("Cron", actualGroup.getCron());
        assertEquals("Projects", actualGroup.getProjects());
        assertEquals("https://example.org/example", actualGroup.getWebUrl());
    }
}

