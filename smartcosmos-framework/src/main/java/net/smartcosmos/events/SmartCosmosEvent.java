package net.smartcosmos.events;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Smart Cosmos Events flow through the system between services, providing helpful
 * information and representing the basis for the visitor, announce, and audit services.
 *
 * @author voor
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmartCosmosEvent<T> {
    @NotEmpty
    private String eventType;

    private String accountUrn;
    private String userUrn;

    @NotNull
    private T data;
}
