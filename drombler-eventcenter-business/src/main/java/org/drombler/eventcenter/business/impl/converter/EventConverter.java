package org.drombler.eventcenter.business.impl.converter;

import org.drombler.event.core.AllDayEventDuration;
import org.drombler.event.core.Event;
import org.drombler.event.core.EventDuration;
import org.drombler.event.core.YearEventDuration;
import org.drombler.eventcenter.integration.persistence.EventEntity;

import java.time.Year;

/**
 * @author Florian
 */
public class EventConverter {

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
        if (eventEntity.getStartDate() != null) {
            return new AllDayEventDuration(eventEntity.getStartDate(), eventEntity.getEndDate());
        }
        if (eventEntity.getEndDateTime() != null) {
            return new YearEventDuration(Year.of(eventEntity.getStartYear()));//, Year.of(eventEntity.getEndYear()));
        }
        return null;
    }

}
