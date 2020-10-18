package org.drombler.eventcenter.business.impl.converter;

import org.drombler.event.core.Event;
import org.drombler.event.core.EventDuration;
import org.drombler.eventcenter.integration.persistence.EventEntity;

import java.util.ServiceLoader;
import java.util.UUID;
import java.util.stream.StreamSupport;

/**
 * @author Florian
 */
public class EventEntityConverter {
    private static final ServiceLoader<EventDurationConverter> EVENT_DURATION_CONVERTER_SERVICE_LOADER = ServiceLoader.load(EventDurationConverter.class);

    public EventEntity convertEvent(Event event) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setEventId(UUID.randomUUID());
        eventEntity.setName(event.getName());
        configurDuration(eventEntity, event.getDuration());
        eventEntity.setPreferredDirName(event.getPreferredDirName());
        eventEntity.setOwners(event.getOwners());
        eventEntity.setOrganizers(event.getOrganizers());
        eventEntity.setParticipants(event.getParticipants());
        return eventEntity;
    }

    private void configurDuration(EventEntity eventEntity, EventDuration duration) {
        if (duration == null) {
            throw new IllegalArgumentException("Duration must not be null!");
        }
        EventDurationConverter eventDurationConverter = StreamSupport.stream(EVENT_DURATION_CONVERTER_SERVICE_LOADER.spliterator(), false)
                .filter(converter -> converter.supportsEventDuration(duration))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported EventDuration type: " + duration.getClass()));
        eventDurationConverter.configurDuration(eventEntity, duration);
    }

}
