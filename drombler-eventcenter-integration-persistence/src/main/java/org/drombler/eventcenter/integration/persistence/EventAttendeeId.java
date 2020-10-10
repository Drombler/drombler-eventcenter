package org.drombler.eventcenter.integration.persistence;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class EventAttendeeId implements Serializable {

    private final Long eventId;

    private final String attendee;


}
