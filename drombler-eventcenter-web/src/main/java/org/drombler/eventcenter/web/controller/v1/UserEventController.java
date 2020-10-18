package org.drombler.eventcenter.web.controller.v1;

/**
 *
 * @author Florian
 */


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.drombler.event.core.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.drombler.eventcenter.web.controller.RestControllerUtils.V1_PATH;


@Api(tags = {"UserEventController V1"})
@RestController("UserEventControllerV1")
@RequestMapping(path = V1_PATH + "/events/me")
public class UserEventController {

    @Autowired
    private AuditorAware<?> auditorAware;

    @GetMapping
    @ApiOperation("Gets all events.")
    public List<Event> getEvents(@RequestHeader("X-Drombler-owner") String owner) // TODO: replace with security principl
    {
        return new ArrayList<>();
    }

}