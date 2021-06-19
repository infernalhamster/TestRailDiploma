package com.bercut.pltest.mashup.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlansTest {
    @Test
    void testConstructor() {
        Plans actualPlans = new Plans(1, "Name");

        assertEquals(1, actualPlans.getId());
        assertEquals("Name", actualPlans.getName());
    }
}

