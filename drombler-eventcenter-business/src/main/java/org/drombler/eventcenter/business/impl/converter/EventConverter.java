package org.drombler.eventcenter.business.impl.converter;

import java.time.Year;
import org.drombler.event.core.AllDayEventDuration;
import org.drombler.event.core.Event;
import org.drombler.event.core.EventDuration;
import org.drombler.event.core.YearEventDuration;
import org.drombler.eventcenter.integration.persistence.EventEntity;

/**
 *
 * @author Florian
 */
public class EventConverter {

    public Event convertEvent(EventEntity eventEntity) {
        Event event = new Event(eventEntity.getEventId(), eventEntity.getName(), convertDuration(eventEntity));
        return event;
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
