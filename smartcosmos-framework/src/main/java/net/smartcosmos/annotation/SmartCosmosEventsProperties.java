package net.smartcosmos.annotation;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpMethod;

/**
 * @author voor
 */
@Data
@ConfigurationProperties("smartcosmos.service.events")
public class SmartCosmosEventsProperties {

    private String address = "http://events";
    private String path = "";
    private HttpMethod httpMethod = HttpMethod.POST;

}
