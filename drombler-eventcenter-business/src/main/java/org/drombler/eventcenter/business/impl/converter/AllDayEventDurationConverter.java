package org.drombler.eventcenter.business.impl.converter;

import org.drombler.event.core.AllDayEventDuration;
import org.drombler.event.core.EventDuration;
import org.drombler.eventcenter.integration.persistence.EventEntity;

public class AllDayEventDurationConverter implements EventDurationConverter {

    @Override
    public boolean supportsEventDuration(EventDuration eventDuration){
        return eventDuration instanceof AllDayEventDuration;
    }

    @Override
    public void configurDuration(EventEntity eventEntity, EventDuration duration) {
            AllDayEventDuration allDayEventDuration = (AllDayEventDuration) duration;
            eventEntity.setStartDate(allDayEventDuration.getStartDateInclusive());
            eventEntity.setEndDate(allDayEventDuration.getEndDateInclusive());
    }

    @Override
    public boolean supportsEventEntity(EventEntity eventEntity){
       return eventEntity.getStartDate() != null;
    }

    @Override
    public AllDayEventDuration convertDuration(EventEntity eventEntity) {
            return new AllDayEventDuration(eventEntity.getStartDate(), eventEntity.getEndDate());
    }
}
