package org.drombler.eventcenter.integration.persistence;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.drombler.commons.spring.jpa.AbstractAuditableEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
//@SequenceGenerator(name = EVENT_GENERATOR, sequenceName = "EVENT_SEQ", allocationSize = ALLOCATION_SIZE)
@Table(name = "EVENT")
public class EventEntity extends AbstractAuditableEntity {

    public static final int ALLOCATION_SIZE = 1;
    public static final String EVENT_GENERATOR = "EVENT_GENERATOR";

    /**
     * The technical id of this entity (surrogate PK).
     */
    @Id
//    @GeneratedValue(generator = EVENT_GENERATOR, strategy = GenerationType.SEQUENCE)
    @Column
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(updatable = false)
    private UUID eventId;

    @Column
    private String name;

    @Column
    private String preferredDirName;

    @Column
    private ZonedDateTime startDateTime;

    @Column
    private LocalDate startDate;

    @Column
    private Integer startYear;

    @Column
    private ZonedDateTime endDateTime;

    @Column
    private LocalDate endDate;

    @Column
    private Integer endYear;

    @Version
    @Column(name = "ENTITY_VERSION")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Long version;

//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name="VENDOR_NAMESPACES", joinColumns=@JoinColumn(name="VENDOR_FK"))
//    @Column(name="NAMESPACE")
//    private Set<String> namespaces;
//
//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name="VENDOR_USER", joinColumns=@JoinColumn(name="VENDOR_FK"))
//    @Column(name="NAMESPACE")
//    @AttributeOverrides({
//            @AttributeOverride(name="key", column=@Column(name="USER_NAME")),
//            @AttributeOverride(name="value.name", column=@Column(name="ROLE"))
//    })
//    public Map<String, List<VendorRole>> vendorUserRoleMappings;
    public Long getId() {
        return id;
    }

}
