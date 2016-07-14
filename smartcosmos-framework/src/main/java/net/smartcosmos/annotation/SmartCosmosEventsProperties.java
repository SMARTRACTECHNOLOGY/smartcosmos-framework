package net.smartcosmos.annotation;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpMethod;

/**
 * @author voor
 */
@Data
@ConfigurationProperties("smartcosmos.events")
public class SmartCosmosEventsProperties {

        private String serviceName = "smartcosmos-events";
        private String url = "";
        private HttpMethod httpMethod = HttpMethod.POST;

}
