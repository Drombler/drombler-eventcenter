package org.drombler.event.center.web;

import ch.sbb.esta.openshift.gracefullshutdown.GracefulshutdownSpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(EventCenterAppConfigurationProperties.class)
@SpringBootApplication
public class EventCenterApp {

    public static void main(String[] args) {
        GracefulshutdownSpringApplication.run(EventCenterApp.class, args);
    }

}
