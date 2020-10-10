package org.drombler.eventcenter.integration.persistence;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@IdClass(EventOrganizerId.class)
@Table(name = "EVENT_ORGANIZER")
@ToString
public class EventOrganizerEntity implements Serializable {

    @Id
    private Long eventId;

    @Id
    private String organizer;


}
