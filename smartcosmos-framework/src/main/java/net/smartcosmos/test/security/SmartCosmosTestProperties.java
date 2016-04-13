package net.smartcosmos.test.security;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author voor
 */
@Data
@ConfigurationProperties("smartcosmos.test")
public class SmartCosmosTestProperties {

    private Map<String, TestUser> users = new LinkedHashMap<>();

    /**
     * Make sure there's at least one user for testing.
     */
    @PostConstruct
    public void init() {
        if (users.isEmpty()) {
            users.put("user",
                    new TestUser("urn:account:test:b5b52af3-9909-4ea4-97bf-07639d683c95",
                            "urn:user:test:e2174814-eb6d-4176-978c-58390105375b"));
        }
    }

    @Data
    @AllArgsConstructor
    public class TestUser {
        private String accountUrn;
        private String userUrn;
    }
}
