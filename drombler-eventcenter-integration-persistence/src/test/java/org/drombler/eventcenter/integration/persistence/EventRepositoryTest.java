package org.drombler.eventcenter.integration.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.drombler.eventcenter.integration.persistence.PersistenceTestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = "org.drombler.eventcenter.integration.persistence")
public class EventRepositoryTest {

    @Autowired
    private EventRepository testee;

    @Test
    @WithMockUser(username = TEST_PRIVATE_USER_1_NAME)
    public void findByEventId() {
        EventEntity eventEntity = createEventEntity(1);
        eventEntity = testee.saveAndFlush(eventEntity);

        Optional<EventEntity> result = testee.findByEventId(eventEntity.getEventId());

        assertThat(result).isPresent();
        EventEntity foundEventEntity = result.get();
        assertNotNull(foundEventEntity.getCreatedAt());
        assertEquals(TEST_PRIVATE_USER_1_NAME, foundEventEntity.getCreatedBy());
        assertNotNull(foundEventEntity.getLastModifiedAt());
        assertEquals(TEST_PRIVATE_USER_1_NAME, foundEventEntity.getLastModifiedBy());

        assertThat(foundEventEntity.getOwners()).isEqualTo(eventEntity.getOwners());
        assertThat(foundEventEntity.getOrganizers()).isEqualTo(eventEntity.getOrganizers());
        assertThat(foundEventEntity.getParticipants()).isEqualTo(eventEntity.getParticipants());
    }

    @Test
    @WithMockUser(username = TEST_PRIVATE_USER_1_NAME)
    public void findAllByOwnersContaining() {
        List<EventEntity> eventEntities = createEventEntities(5);
        eventEntities.get(0).setOwners(new HashSet<>(asList(TEST_PRIVATE_USER_1)));
        eventEntities.get(1).setOwners(new HashSet<>(asList(TEST_PRIVATE_USER_1)));
        eventEntities.get(2).setOwners(new HashSet<>(asList(TEST_PRIVATE_USER_1, TEST_PRIVATE_USER_2)));
        eventEntities.get(3).setOwners(new HashSet<>(asList(TEST_PRIVATE_USER_2, TEST_PRIVATE_USER_1)));
        eventEntities.get(4).setOwners(new HashSet<>(asList(TEST_PRIVATE_USER_2)));
        eventEntities = testee.saveAll(eventEntities);

        List<EventEntity> result = testee.findAllByOwnersContaining(TEST_PRIVATE_USER_1);

        assertThat(result).containsAll(eventEntities.subList(0, 4));

    }

}
