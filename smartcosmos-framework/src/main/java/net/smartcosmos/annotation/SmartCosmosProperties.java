package net.smartcosmos.annotation;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpMethod;

/**
 * @author voor
 */
@Data
@ConfigurationProperties("smartcosmos")
public class SmartCosmosProperties {

    private Map<String, String> services = new LinkedHashMap<>();

    private EventProperties events = new EventProperties();

    private Boolean debug = false;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EventProperties {
        private String topic = "smartcosmos:events";

        private String serviceName = "smartcosmos-events";
        private String url = "";
        private HttpMethod httpMethod = HttpMethod.POST;
    }

}
