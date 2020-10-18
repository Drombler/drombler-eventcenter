package org.drombler.eventcenter.integration.persistence;

import java.time.LocalDate;
import java.util.UUID;

public final class PersistenceTestHelper {
    private PersistenceTestHelper() {
    }

    public static final EventEntity createEventEntity() {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setEventId(UUID.randomUUID());
        eventEntity.setName("My Test Event 1");
        eventEntity.setPreferredDirName("My-Test-Event-1");
        eventEntity.setStartDate(LocalDate.of(2020, 10, 4));
        eventEntity.setEndDate(LocalDate.of(2020, 10, 4));
        return eventEntity;
    }
}
