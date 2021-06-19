package com.bercut.pltest.mashup.entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NotificationTest {

    @Test
    void testConstructor() {
        ArrayList<Notification.Section> sectionList = new ArrayList<Notification.Section>();
        Notification actualNotification = new Notification(sectionList);
        assertEquals("https://schema.org/extensions", actualNotification.getContext());
        assertEquals("MessageCard", actualNotification.getType());
        assertEquals("Automatic notification", actualNotification.getTitle());
        assertEquals("Summary", actualNotification.getSummary());
        List<Notification.Section> sections = actualNotification.getSections();
        assertSame(sectionList, sections);
        assertTrue(sections.isEmpty());
        assertSame(sections, sectionList);
    }

    @Test
    void testSectionConstructor() {
        assertEquals("Text", (new Notification.Section("Text")).getText());
    }

    @Test
    void testSection_FactsConstructor() {
        Notification.Section.Facts actualFacts = new Notification.Section.Facts("Name", "42");

        assertEquals("Name", actualFacts.getName());
        assertEquals("42", actualFacts.getValue());
    }

}

