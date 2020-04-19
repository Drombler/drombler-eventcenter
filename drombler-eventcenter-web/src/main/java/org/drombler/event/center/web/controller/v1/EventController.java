package org.drombler.event.center.web.controller.v1;

/**
 *
 * @author Florian
 */


    
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import static org.drombler.event.center.web.controller.RestControllerUtils.V1_PATH;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Api(tags = {"EventController V1"})
@RestController("EventControllerV1")
@RequestMapping(path = V1_PATH + "/events")
public class EventController {

    @GetMapping(path = "/{groupId}/{artifactId}")
    @ApiOperation("Gets the application package file.")
    public HttpEntity<byte[]> getApplication(
            @ApiParam(value = "The Maven groupId of the application", required = true, example = "org.drombler.jstore.client") @PathVariable("groupId") String groupId,
            @ApiParam(value = "The Maven artifactId of the application", required = true, example = "drombler-jstore-client-application") @PathVariable("artifactId") String artifactId,
            @ApiParam(value = "The currently installed Maven version of the application", required = false, example = "0.1.0") @RequestParam(value = "installedVersion", required = false) String installedVersion
    ) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_PDF);
//        headers.set(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=" + fileName.replace(" ", "_"));
//        headers.setContentLength(documentBody.length);
//        UrlResource urlResource = new UrlResource()
//        return new HttpEntity<byte[]>(documentBody, headers);

        return ResponseEntity.ok()
                .build();
    }

}