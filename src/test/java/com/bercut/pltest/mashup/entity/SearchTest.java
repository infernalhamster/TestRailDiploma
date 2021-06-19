package com.bercut.pltest.mashup.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest {
    @Test
    public void testGetModifiedDataUntil() {
        Search search = new Search();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        search.setDateUntil(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
        assertEquals(75600000L, search.getModifiedDataUntil());
    }

    @Test
    public void testConstructor() {
        Search actualSearch = new Search();
        actualSearch.setIsModified(true);
        actualSearch.setOnlyAutomated(true);
        assertTrue(actualSearch.getIsModified());
        assertTrue(actualSearch.getOnlyAutomated());
    }
}

