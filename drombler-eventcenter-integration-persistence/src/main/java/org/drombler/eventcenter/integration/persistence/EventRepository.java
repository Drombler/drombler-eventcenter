package org.drombler.eventcenter.integration.persistence;

import org.drombler.identity.core.DromblerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Component
public interface EventRepository extends JpaRepository<EventEntity, Long> {

    Optional<EventEntity> findByEventId(UUID eventId);

    List<EventEntity> findAllByOwnersContaining(DromblerId dromblerId);

//    List<VendorEntity> findAllByUsername(String username);


}
