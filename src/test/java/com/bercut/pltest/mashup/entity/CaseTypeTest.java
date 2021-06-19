package com.bercut.pltest.mashup.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CaseTypeTest {
    @Test
    void testConstructor() {
        CaseType actualCaseType = new CaseType(1, true, "Name");

        assertEquals(1, actualCaseType.getId());
        assertTrue(actualCaseType.isDefault());
        assertEquals("Name", actualCaseType.getName());
    }
}

