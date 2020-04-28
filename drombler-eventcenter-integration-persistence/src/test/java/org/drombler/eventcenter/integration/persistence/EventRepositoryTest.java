package org.drombler.eventcenter.integration.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
@AutoConfigurationPackage
@ComponentScan(basePackages = "org.drombler.eventcenter.integration.persistence")
@ExtendWith(SpringExtension.class)
public class EventRepositoryTest {

    @Autowired
    private EventRepository testee;

    @Test
    public void testFindAllByOwner() {
    }

}
