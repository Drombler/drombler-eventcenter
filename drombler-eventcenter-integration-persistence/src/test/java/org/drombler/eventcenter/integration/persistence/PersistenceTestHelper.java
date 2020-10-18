package org.drombler.eventcenter.integration.persistence;

import org.drombler.identity.core.DromblerUserId;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;

public final class PersistenceTestHelper {
    public static final String TEST_PRIVATE_USER_1_NAME = "testuser1";
    public static final String TEST_PRIVATE_USER_2_NAME = "testuser2";
    public static final String TEST_PRIVATE_USER_3_NAME = "testuser3";
    public static final String TEST_PRIVATE_USER_4_NAME = "testuser4";
    public static final DromblerUserId TEST_PRIVATE_USER_1 = new DromblerUserId(TEST_PRIVATE_USER_1_NAME);
    public static final DromblerUserId TEST_PRIVATE_USER_2 = new DromblerUserId(TEST_PRIVATE_USER_2_NAME);
    public static final DromblerUserId TEST_PRIVATE_USER_3 = new DromblerUserId(TEST_PRIVATE_USER_3_NAME);
    public static final DromblerUserId TEST_PRIVATE_USER_4 = new DromblerUserId(TEST_PRIVATE_USER_4_NAME);

    private PersistenceTestHelper() {
    }

    public static final List<EventEntity> createEventEntities(int num) {
        return IntStream.range(1, num + 1)
                .mapToObj(PersistenceTestHelper::createEventEntity)
                .collect(Collectors.toList());
    }

    public static final EventEntity createEventEntity(int nameId) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setEventId(UUID.randomUUID());
        eventEntity.setName("My Test Event " + nameId);
        eventEntity.setPreferredDirName("My-Test-Event-" + nameId);
        eventEntity.setStartDate(LocalDate.of(2020, 10, 4));
        eventEntity.setEndDate(LocalDate.of(2020, 10, 4));
        eventEntity.setOwners(new HashSet<>(asList(TEST_PRIVATE_USER_1, TEST_PRIVATE_USER_2)));
        eventEntity.setOrganizers(new HashSet<>(asList(TEST_PRIVATE_USER_3)));
        eventEntity.setParticipants(new HashSet<>(asList(TEST_PRIVATE_USER_1, TEST_PRIVATE_USER_2, TEST_PRIVATE_USER_3, TEST_PRIVATE_USER_4)));
        return eventEntity;
    }
}
