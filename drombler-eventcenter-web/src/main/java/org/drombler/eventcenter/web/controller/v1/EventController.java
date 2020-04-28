package org.drombler.eventcenter.web.controller.v1;

/**
 *
 * @author Florian
 */


    
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import org.drombler.event.core.Event;
import static org.drombler.eventcenter.web.controller.RestControllerUtils.V1_PATH;
import org.springframework.web.bind.annotation.*;


@Api(tags = {"EventController V1"})
@RestController("EventControllerV1")
@RequestMapping(path = V1_PATH + "/events")
public class EventController {

    @GetMapping
    @ApiOperation("Gets all events.")
    public List<Event> getEvents(@RequestHeader("X-Drombler-owner") String owner) // TODO: replace with security principaö
    { 
return new ArrayList<>();
    }

}