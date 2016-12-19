package net.smartcosmos.autoconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.audit.InMemoryAuditEventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration of Spring Boot Actuator services that are overridden, disabled or configured differently from the default
 */
@Configuration
public class ActuatorServicesAutoConfiguration {

    @Bean
    @Autowired
    public InMemoryAuditEventRepository auditEventRepository(@Value("${smartcosmos.audit-queue.size:20}") int queueSize) throws Exception {

        return new InMemoryAuditEventRepository(queueSize);
    }

}
