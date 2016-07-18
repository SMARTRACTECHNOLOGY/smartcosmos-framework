package net.smartcosmos.concurrent;

import java.util.concurrent.Executor;

import org.springframework.security.concurrent.DelegatingSecurityContextRunnable;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;

/**
 * An {@link Executor} which wraps each {@link Runnable} in a
 * {@link DelegatingRequestAttributesRunnable} or {@link DelegatingRequestAttributesCallable}.
 *
 * @see org.springframework.security.concurrent.DelegatingSecurityContextExecutor
 * @author Robert Van Voorhees
 */
public class DelegatingRequestAttributesExecutor extends AbstractDelegatingRequestAttributesSupport implements Executor {
    private final Executor delegate;

    /**
     * Creates a new {@link DelegatingRequestAttributesExecutor} that uses the specified
     * {@link RequestAttributes}.
     *
     * @param delegateExecutor the {@link Executor} to delegate to. Cannot be null.
     * @param requestAttributes the {@link RequestAttributes} to use for each
     * {@link DelegatingSecurityContextRunnable} or null to default to the current
     * {@link SecurityContext}
     */
    public DelegatingRequestAttributesExecutor(Executor delegateExecutor,
                                             RequestAttributes requestAttributes) {
        super(requestAttributes);
        Assert.notNull(delegateExecutor, "delegateExecutor cannot be null");
        this.delegate = delegateExecutor;
    }

    /**
     * Creates a new {@link DelegatingRequestAttributesExecutor} that uses the current
     * {@link RequestAttributes} from the {@link org.springframework.web.context.request.RequestContextHolder} at the time the task
     * is submitted.
     *
     * @param delegate the {@link Executor} to delegate to. Cannot be null.
     */
    public DelegatingRequestAttributesExecutor(Executor delegate) {
        this(delegate, null);
    }

    public final void execute(Runnable task) {
        task = wrap(task);
        delegate.execute(task);
    }

    protected final Executor getDelegateExecutor() {
        return delegate;
    }
}
