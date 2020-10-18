package org.drombler.eventcenter.business.impl;

import org.drombler.commons.spring.transaction.stereotype.TransactionalService;
import org.drombler.event.core.Event;
import org.drombler.eventcenter.business.EventService;
import org.drombler.eventcenter.business.impl.converter.EventConverter;
import org.drombler.eventcenter.business.impl.converter.EventEntityConverter;
import org.drombler.eventcenter.integration.persistence.EventEntity;
import org.drombler.eventcenter.integration.persistence.EventRepository;
import org.drombler.identity.core.DromblerId;
import org.drombler.identity.core.DromblerUserId;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Florian
 */

@TransactionalService
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getEventsByOwnersContaining(DromblerId owner) {
        EventConverter eventConverter = new EventConverter();
        return eventRepository.findAllByOwnersContaining(owner).stream()
                .map(eventConverter::convertEvent)
                .collect(Collectors.toList());
    }

    @Override
    public Event createEvent(DromblerUserId dromblerUserId, Event event) {
        EventEntityConverter eventEntityConverter = new EventEntityConverter();
        EventEntity eventEntity = eventEntityConverter.convertEvent(event);

        EventEntity savedEventEntity = eventRepository.save(eventEntity);

        EventConverter eventConverter = new EventConverter();
        return eventConverter.convertEvent(savedEventEntity);
    }
}
