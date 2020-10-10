package org.drombler.eventcenter.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(EventCenterAppConfigurationProperties.class)
@SpringBootApplication
public class EventCenterApp {

    public static void main(String[] args) {
        SpringApplication.run(EventCenterApp.class, args);
    }

}
