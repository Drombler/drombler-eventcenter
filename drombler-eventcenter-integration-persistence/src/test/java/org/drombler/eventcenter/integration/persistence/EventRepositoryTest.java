package org.drombler.eventcenter.integration.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.drombler.eventcenter.integration.persistence.PersistenceTestHelper.TEST_PRIVATE_USER_1_NAME;
import static org.drombler.eventcenter.integration.persistence.PersistenceTestHelper.createEventEntity;
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
        EventEntity eventEntity = createEventEntity();
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
        assertThat(foundEventEntity.getAttendees()).isEqualTo(eventEntity.getAttendees());
    }

}
