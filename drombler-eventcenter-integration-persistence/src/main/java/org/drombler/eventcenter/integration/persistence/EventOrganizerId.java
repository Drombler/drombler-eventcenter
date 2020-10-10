package org.drombler.eventcenter.integration.persistence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class EventOrganizerId implements Serializable {

    private final Long eventId;

    private final String organizer;


}
