package com.bercut.pltest.mashup.entity;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CaseTest {
    @Test
    void testConstructor() {
        Case actualResultCase = new Case(1, "jane.doe@example.org", mock(Timestamp.class),
                "Estimate", "Estimate Forecast",
                1, 123, 123, "Refs", 123,
                "Dr", 123, "Type", 1, "2020-03-01", mock(Timestamp.class));

        assertEquals(1, actualResultCase.getCreatedBy());
        assertEquals(1, actualResultCase.getUpdatedBy());
        Date updatedOn = actualResultCase.getUpdatedOn();
        Date createdOn = actualResultCase.getCreatedOn();
        assertEquals(updatedOn, createdOn);
        assertEquals("2020-03-01", actualResultCase.getEmailUpdatedBy());
        assertEquals(123, actualResultCase.getMilestoneId());
        assertEquals(123, actualResultCase.getPriorityId());
        assertEquals("Estimate", actualResultCase.getEstimate());
        assertEquals("Refs", actualResultCase.getRefs());
        assertEquals(123, actualResultCase.getSuiteId());
        assertEquals("jane.doe@example.org", actualResultCase.getEmailCreatedBy());
        assertEquals("Estimate Forecast", actualResultCase.getEstimateForecast());
        assertEquals("Dr", actualResultCase.getTitle());
        assertEquals("Type", actualResultCase.getType());
        assertEquals(1, actualResultCase.getId());
        assertEquals(123, actualResultCase.getTypeId());
        assertEquals(70, updatedOn.getYear());
        assertEquals(-180, updatedOn.getTimezoneOffset());
        assertEquals(0L, updatedOn.getTime());
        assertEquals(0, updatedOn.getSeconds());
        assertEquals(0, updatedOn.getMonth());
        assertEquals(0, updatedOn.getMinutes());
        assertEquals(3, updatedOn.getHours());
        assertEquals(4, updatedOn.getDay());
        assertEquals(1, updatedOn.getDate());
        assertEquals(3, createdOn.getHours());
        assertEquals(0, createdOn.getMonth());
        assertEquals(0, createdOn.getSeconds());
        assertEquals(70, createdOn.getYear());
        assertEquals("1 янв. 1970 г., 3:00:00", createdOn.toLocaleString());
        assertEquals("1 янв. 1970 г., 3:00:00", updatedOn.toLocaleString());
        assertEquals("Thu Jan 01 03:00:00 MSK 1970", updatedOn.toString());
        assertEquals(4, createdOn.getDay());
        assertEquals(0, createdOn.getMinutes());
        assertEquals(-180, createdOn.getTimezoneOffset());
        assertEquals("Thu Jan 01 03:00:00 MSK 1970", createdOn.toString());
        assertEquals(1, createdOn.getDate());
        assertEquals(0L, createdOn.getTime());
    }

    @Test
    void testGetCreatedOn() {
        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.getTime()).thenReturn(1L);

        Case resultCase = new Case();
        resultCase.setCreatedOn(timestamp);
        resultCase.getCreatedOn();
        verify(timestamp).getTime();
    }

    @Test
    void testGetUpdatedOn() {
        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.getTime()).thenReturn(1L);

        Case resultCase = new Case();
        resultCase.setUpdatedOn(timestamp);
        resultCase.getUpdatedOn();
        verify(timestamp).getTime();
    }

}

