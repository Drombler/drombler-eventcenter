package org.drombler.eventcenter.integration.persistence;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.drombler.commons.spring.jpa.AbstractAuditableEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@IdClass(EventAttendeeId.class)
@Table(name = "EVENT_ATTENDEE")
@ToString
public class EventAttendeeEntity implements Serializable {

    @Id
    private Long eventId;

    @Id
    private String attendee;


}
