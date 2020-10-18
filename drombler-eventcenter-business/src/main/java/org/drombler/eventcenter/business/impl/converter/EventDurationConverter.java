package org.drombler.eventcenter.business.impl.converter;

import org.drombler.event.core.EventDuration;
import org.drombler.eventcenter.integration.persistence.EventEntity;

public interface EventDurationConverter {
    boolean supportsEventDuration(EventDuration eventDuration);

    void configurDuration(EventEntity eventEntity, EventDuration duration);

    boolean supportsEventEntity(EventEntity eventEntity);

    EventDuration convertDuration(EventEntity eventEntity);
}
