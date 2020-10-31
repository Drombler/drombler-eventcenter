package org.drombler.eventcenter.business.impl.converter;

import org.drombler.event.core.Event;
import org.drombler.event.core.EventDuration;
import org.drombler.eventcenter.integration.persistence.EventEntity;

import java.util.ServiceLoader;
import java.util.stream.StreamSupport;

/**
 * @author Florian
 */
public class EventConverter {

    private static final ServiceLoader<EventDurationConverter> EVENT_DURATION_CONVERTER_SERVICE_LOADER = ServiceLoader.load(EventDurationConverter.class);

    public Event convertEvent(EventEntity eventEntity) {
        return Event.builder()
                .id(eventEntity.getEventId())
                .name(eventEntity.getName())
                .duration(convertDuration(eventEntity))
                .preferredDirName(eventEntity.getPreferredDirName())
                .owners(eventEntity.getOwners())
                .organizers(eventEntity.getOrganizers())
                .participants(eventEntity.getParticipants())
                .build();
    }

    private EventDuration convertDuration(EventEntity eventEntity) {
//        if (eventEntity.getStartDateTime() != null) {
//            return new DateTimeEventDuration(eventEntity.getStartDateTime(), eventEntity.getEndDateTime());
//        }
      return   StreamSupport.stream(EVENT_DURATION_CONVERTER_SERVICE_LOADER.spliterator(), false)
                .filter(eventDurationConverter -> eventDurationConverter.supportsEventEntity(eventEntity))
                .findFirst()
                .map(eventDurationConverter -> eventDurationConverter.convertDuration(eventEntity))
                .orElse(null);
    }

}
