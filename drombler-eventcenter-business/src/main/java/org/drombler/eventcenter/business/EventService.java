package org.drombler.eventcenter.business;

import java.util.List;
import java.util.stream.Collectors;
import org.drombler.commons.spring.transaction.stereotype.TransactionalService;
import org.drombler.event.core.Event;
import org.drombler.eventcenter.business.impl.converter.EventConverter;
import org.drombler.eventcenter.integration.persistence.EventRepository;
import org.drombler.identity.core.DromblerId;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Florian
 */

@TransactionalService
public class EventService {
    
    @Autowired
    private EventRepository eventRepository;
    
    public List<Event> getEvents(DromblerId owner){
        EventConverter eventConverter = new EventConverter();
        return eventRepository.findAllByOwner(owner.getDromblerIdFormatted()).stream()
                 .map(eventConverter::convertEvent)
                 .collect(Collectors.toList());
    }
}
