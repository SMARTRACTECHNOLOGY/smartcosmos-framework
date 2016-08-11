package net.smartcosmos.concurrent;

import java.util.concurrent.ExecutorService;

import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Robert Van Voorhees
 */
public class DelegatingSecurityContextAndRequestAttributesExecutorService extends DelegatingSecurityContextExecutorService {
    /**
     * Creates a new {@link DelegatingSecurityContextAndRequestAttributesExecutorService} that uses the
     * current {@link SecurityContext} from the {@link SecurityContextHolder} and the current
     * {@link org.springframework.web.context.request.RequestAttributes} from the
     * {@link org.springframework.web.context.request.RequestContextHolder}.
     *
     * @param delegate the {@link ExecutorService} to delegate to. Cannot be
     * null.
     */
    public DelegatingSecurityContextAndRequestAttributesExecutorService(ExecutorService delegate) {
        super(new DelegatingRequestAttributesExecutorService(delegate));
    }
}
