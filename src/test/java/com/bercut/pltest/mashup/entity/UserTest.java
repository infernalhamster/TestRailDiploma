package com.bercut.pltest.mashup.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {
    @Test
    public void testConstructor() {
        User actualUser = new User("jane.doe@example.org", 1, true, "Name", "42", "Role");

        assertEquals("jane.doe@example.org", actualUser.getEmail());
        assertTrue(actualUser.isActive());
        assertEquals("42", actualUser.getRoleId());
        assertEquals("Role", actualUser.getRole());
        assertEquals("Name", actualUser.getName());
        assertEquals(1, actualUser.getId());
    }
}

