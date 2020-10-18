package org.drombler.eventcenter.business.impl.converter;

import org.drombler.event.core.EventDuration;
import org.drombler.event.core.YearEventDuration;
import org.drombler.eventcenter.integration.persistence.EventEntity;

import java.time.Year;

public class YearEventDurationConverter implements EventDurationConverter {

    @Override
    public boolean supportsEventDuration(EventDuration eventDuration){
        return eventDuration instanceof YearEventDuration;
    }

    @Override
    public void configurDuration(EventEntity eventEntity, EventDuration duration) {
        YearEventDuration yearEventDuration = (YearEventDuration) duration;
        eventEntity.setStartYear(yearEventDuration.getYear().getValue());
    }

    @Override
    public boolean supportsEventEntity(EventEntity eventEntity){
       return   eventEntity.getStartYear() != null            ;
    }

    @Override
    public YearEventDuration convertDuration(EventEntity eventEntity) {
        return new YearEventDuration(Year.of(eventEntity.getStartYear()));//, Year.of(eventEntity.getEndYear()));
    }
}
