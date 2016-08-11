package net.smartcosmos.concurrent;

import java.util.concurrent.Callable;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

/**
 * An internal support class that wraps {@link Callable} with
 * {@link DelegatingRequestAttributesCallable} and {@link Runnable} with
 * {@link DelegatingRequestAttributesRunnable}
 *
 * @see org.springframework.security.concurrent.AbstractDelegatingSecurityContextSupport
 * @author Robert Van Voorhees
 */
public class AbstractDelegatingRequestAttributesSupport {

    private final RequestAttributes requestAttributes;

    /**
     * Creates a new {@link AbstractDelegatingRequestAttributesSupport} that uses the
     * specified {@link RequestContext}.
     *
     * @param requestAttributes the {@link RequestContext} to use for each
     * {@link DelegatingRequestAttributesRunnable} and each
     * {@link DelegatingRequestAttributesCallable} or null to default to the current
     * {@link RequestContext}.
     */
    AbstractDelegatingRequestAttributesSupport(RequestAttributes requestAttributes) {
        this.requestAttributes = requestAttributes;
    }

    protected final Runnable wrap(Runnable delegate) {
        return DelegatingRequestAttributesRunnable.create(delegate, requestAttributes);
    }

    protected final <T> Callable<T> wrap(Callable<T> delegate) {
        return DelegatingRequestAttributesCallable.create(delegate, requestAttributes);
    }
}
