package com.bercut.pltest.mashup.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProjectTest {
    @Test
    void testConstructor() {
        Project actualProject = new Project("Announcement", 1, 1, true, "Name", true, "https://example.org/example");

        assertEquals("Announcement", actualProject.getAnnouncement());
        assertTrue(actualProject.is_completed());
        assertTrue(actualProject.isShowAnnouncement());
        assertEquals("https://example.org/example", actualProject.getUrl());
        assertEquals("Name", actualProject.getName());
        assertEquals(1, actualProject.getId());
        assertEquals(1, actualProject.getCompletedOn());
    }
}

