package org.drombler.eventcenter.web.controller.v1;

/**
 *
 * @author Florian
 */


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.drombler.event.core.Event;
import org.drombler.eventcenter.business.EventService;
import org.drombler.identity.core.DromblerUserId;
import org.drombler.identity.management.DromblerIdentityProviderManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.drombler.eventcenter.web.controller.RestControllerUtils.V1_PATH;


@Api(tags = {"UserEventController V1"})
@RestController("UserEventControllerV1")
@RequestMapping(path = V1_PATH + "/me/events")
@RequiredArgsConstructor
public class UserEventController {

    private final EventService eventService;

    private final DromblerIdentityProviderManager dromblerIdentityProviderManager;

    @GetMapping
    @ApiOperation("Gets all events.")
    public List<Event> getEvents(@RequestHeader("X-Drombler-owner") String owner) // TODO: replace with security principl
    {
        DromblerUserId dromblerUserId = DromblerUserId.parseDromblerUserId(owner, dromblerIdentityProviderManager);
        return eventService.getEventsByOwnersContaining(dromblerUserId);
    }

    @PostMapping
    @ApiOperation("Creates an event.")
    public Event createEvent(@RequestHeader("X-Drombler-owner") String owner, @RequestBody Event event) // TODO: replace with security principl
    {
        DromblerUserId dromblerUserId = DromblerUserId.parseDromblerUserId(owner, dromblerIdentityProviderManager);
        return eventService.createEvent(dromblerUserId, event);
    }


}