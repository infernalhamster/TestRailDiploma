package com.bercut.pltest.mashup.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanTest {
    @Test
    void testConstructor() {
        Plan.Entries entries = new Plan.Entries();
        Plan.Entries entries1 = new Plan.Entries();
        Plan.Entries[] entriesArray = new Plan.Entries[]{entries, entries1, new Plan.Entries()};
        Plan actualPlan = new Plan(123, 3, 1, 1, 1, "The characteristics of someone or something", entriesArray, 3, 1, true,
                123, "Name", 3, 123, 3, 3, "https://example.org/example");

        assertEquals(123, actualPlan.getAssignedtoId());
        assertTrue(actualPlan.isCompleted());
        assertEquals("https://example.org/example", actualPlan.getUrl());
        assertEquals(3, actualPlan.getUntestedCount());
        assertEquals(3, actualPlan.getRetestCount());
        assertEquals(123, actualPlan.getProjectId());
        assertEquals(3, actualPlan.getPassedCount());
        assertEquals("Name", actualPlan.getName());
        assertEquals(123, actualPlan.getMilestoneId());
        assertEquals(1, actualPlan.getId());
        assertEquals(3, actualPlan.getFailedCount());
        Plan.Entries[] entries2 = actualPlan.getEntries();
        assertEquals(3, entries2.length);
        assertEquals("The characteristics of someone or something", actualPlan.getDescription());
        assertEquals(1, actualPlan.getCreatedOn());
        assertEquals(1, actualPlan.getCreatedBy());
        assertEquals(1, actualPlan.getCompletedOn());
        assertEquals(3, actualPlan.getBlockedCount());
        assertSame(entries2, entriesArray);
    }

    @Test
    void testEntriesConstructor() {
        assertNull((new Plan.Entries()).getRuns());
    }

    @Test
    void testEntriesConstructor2() {
        TestRun testRun = new TestRun();
        TestRun testRun1 = new TestRun();
        TestRun[] testRunArray = new TestRun[]{testRun, testRun1, new TestRun()};
        TestRun[] runs = (new Plan.Entries(testRunArray)).getRuns();
        assertEquals(3, runs.length);
        assertSame(runs, testRunArray);
    }
}

