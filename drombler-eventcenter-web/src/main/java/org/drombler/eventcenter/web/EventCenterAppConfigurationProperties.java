package org.drombler.eventcenter.web;

import org.drombler.commons.spring.boot.context.properties.AbstractApplicationConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 *
 * @author Florian
 */

@ConfigurationProperties(prefix = "eventcenter")
public class EventCenterAppConfigurationProperties extends AbstractApplicationConfigurationProperties{
    
}
