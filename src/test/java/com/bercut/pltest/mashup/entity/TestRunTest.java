package com.bercut.pltest.mashup.entity;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class TestRunTest {
    @Test
    void testConstructor() {
        TestRun actualTestRun = new TestRun(123, 3, 1, "Config", 1, mock(Timestamp.class),
                "The characteristics of someone or something", 3, 1, true, true, 123, "Name", 3, 123, 123, 3, 123, 3, 1,
                "https://example.org/example");

        assertEquals(123, actualTestRun.getAssignedtoId());
        assertTrue(actualTestRun.isIncludeAll());
        assertTrue(actualTestRun.isCompleted());
        assertEquals("https://example.org/example", actualTestRun.getUrl());
        assertEquals(1, actualTestRun.getUpdatedOn());
        assertEquals(3, actualTestRun.getUntestedCount());
        assertEquals(123, actualTestRun.getSuiteId());
        assertEquals(3, actualTestRun.getRetestCount());
        assertEquals(123, actualTestRun.getProjectId());
        assertEquals(123, actualTestRun.getPlanId());
        assertEquals(3, actualTestRun.getPassedCount());
        assertEquals("Name", actualTestRun.getName());
        assertEquals(123, actualTestRun.getMilestoneId());
        assertEquals(1, actualTestRun.getId());
        assertEquals(3, actualTestRun.getFailedCount());
        assertEquals("The characteristics of someone or something", actualTestRun.getDescription());
        assertEquals(1, actualTestRun.getCreatedBy());
        assertEquals(3, actualTestRun.getBlockedCount());
        assertEquals("Config", actualTestRun.getConfig());
        assertEquals(1, actualTestRun.getCompletedOn());
        Date createdOn = actualTestRun.getCreatedOn();
        assertEquals(-180, createdOn.getTimezoneOffset());
        assertEquals(0L, createdOn.getTime());
        assertEquals(0, createdOn.getSeconds());
        assertEquals(0, createdOn.getMonth());
        assertEquals(0, createdOn.getMinutes());
        assertEquals(3, createdOn.getHours());
        assertEquals(4, createdOn.getDay());
        assertEquals(1, createdOn.getDate());
        assertEquals(70, createdOn.getYear());
    }

    @Test
    void testGetCreatedOn() {
        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.getTime()).thenReturn(1L);
        (new TestRun(123, 3, 1, "Config", 1, timestamp, "The characteristics of someone or something", 3, 1, true, true,
                123, "Name", 3, 123, 123, 3, 123, 3, 1, "https://example.org/example")).getCreatedOn();
        verify(timestamp).getTime();
    }
}

