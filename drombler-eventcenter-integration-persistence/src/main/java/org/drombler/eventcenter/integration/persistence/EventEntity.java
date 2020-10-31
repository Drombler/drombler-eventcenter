package org.drombler.eventcenter.integration.persistence;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.drombler.commons.spring.jpa.AbstractAuditableEntity;
import org.drombler.eventcenter.integration.persistence.impl.DromblerIdConverter;
import org.drombler.identity.core.DromblerId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
//@SequenceGenerator(name = EVENT_GENERATOR, sequenceName = "EVENT_SEQ", allocationSize = ALLOCATION_SIZE)
@Table(name = "EVENT_CORE") // EVENT is a reserved word in SQL
public class EventEntity extends AbstractAuditableEntity {


    /**
     * The technical id of this entity (surrogate PK).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Setter(AccessLevel.NONE)
    private Long id;

    @ToString.Include
    @Column(updatable = false)
    private UUID eventId;

    @ToString.Include
    @Column
    private String name;

    @Column
    private String preferredDirName;

    @Column(name = "START_DATETIME")
    private ZonedDateTime startDateTime;

    @Column
    private LocalDate startDate;

    @Column
    private Integer startYear;

    @Column(name = "END_DATETIME")
    private ZonedDateTime endDateTime;

    @Column
    private LocalDate endDate;

    @Column
    private Integer endYear;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "EVENT_OWNER", joinColumns = @JoinColumn(name = "EVENT_ID"))
    @Column(name = "OWNER")
    @Convert(converter = DromblerIdConverter.class)
    private Set<DromblerId> owners;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "EVENT_ORGANIZER", joinColumns = @JoinColumn(name = "EVENT_ID"))
    @Column(name = "ORGANIZER")
    @Convert(converter = DromblerIdConverter.class)
    private Set<DromblerId> organizers;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "EVENT_PARTICIPANT", joinColumns = @JoinColumn(name = "EVENT_ID"))
    @Column(name = "PARTICIPANT")
    @Convert(converter = DromblerIdConverter.class)
    private Set<DromblerId> participants;
//
//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name="VENDOR_USER", joinColumns=@JoinColumn(name="VENDOR_FK"))
//    @Column(name="NAMESPACE")
//    @AttributeOverrides({
//            @AttributeOverride(name="key", column=@Column(name="USER_NAME")),
//            @AttributeOverride(name="value.name", column=@Column(name="ROLE"))
//    })
//    public Map<String, List<VendorRole>> vendorUserRoleMappings;


}
