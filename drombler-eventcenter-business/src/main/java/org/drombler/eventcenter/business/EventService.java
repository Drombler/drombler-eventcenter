package org.drombler.eventcenter.business;

import org.drombler.event.core.Event;
import org.drombler.identity.core.DromblerId;
import org.drombler.identity.core.DromblerUserId;

import java.util.List;

/**
 *
 * @author Florian
 */

public interface EventService {

    List<Event> getEventsByOwnersContaining(DromblerId owner);

    Event createEvent(DromblerUserId dromblerUserId, Event event);
}
