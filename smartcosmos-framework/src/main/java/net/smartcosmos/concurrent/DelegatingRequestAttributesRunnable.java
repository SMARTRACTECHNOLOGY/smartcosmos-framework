
package net.smartcosmos.concurrent;

import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.support.RequestContext;

/**
 * <p>
 * Wraps a delegate {@link Runnable} with logic for setting up a {@link RequestAttributes}
 * before invoking the delegate {@link Runnable} and then removing the
 * {@link RequestAttributes} after the delegate has completed.
 * </p>
 * <p>
 * If there is a {@link RequestAttributes} that already exists, it will be
 * restored after the {@link #run()} method is invoked.
 * </p>
 *
 * @see org.springframework.security.concurrent.DelegatingSecurityContextRunnable
 * @author Robert Van Voorhees
 */
public final class DelegatingRequestAttributesRunnable implements Runnable {

	private final Runnable delegate;

	/**
	 * The {@link RequestAttributes} that the delegate {@link Runnable} will be
	 * ran as.
	 */
	private final RequestAttributes delegateRequestAttributes;

	/**
	 * The {@link RequestContext} that was on the {@link RequestContextHolder}
	 * prior to being set to the delegateRequestAttributes.
	 */
	private RequestAttributes originalRequestAttributes;

	/**
	 * Creates a new {@link DelegatingRequestAttributesRunnable} with a specific
	 * {@link RequestAttributes}.
	 * @param delegate the delegate {@link Runnable} to run with the specified
	 * {@link RequestAttributes}. Cannot be null.
	 * @param requestAttributes the {@link RequestAttributes} to establish for the delegate
	 * {@link Runnable}. Cannot be null.
	 */
	public DelegatingRequestAttributesRunnable(Runnable delegate,
                                               RequestAttributes requestAttributes) {
		Assert.notNull(delegate, "delegate cannot be null");
		Assert.notNull(requestAttributes, "requestAttributes cannot be null");
		this.delegate = delegate;
		this.delegateRequestAttributes = requestAttributes;
	}

	/**
	 * Creates a new {@link DelegatingRequestAttributesRunnable} with the
	 * {@link RequestAttributes} from the {@link RequestContextHolder}.
	 * @param delegate the delegate {@link Runnable} to run under the current
	 * {@link RequestAttributes}. Cannot be null.
	 */
	public DelegatingRequestAttributesRunnable(Runnable delegate) {
		this(delegate, RequestContextHolder.getRequestAttributes());
	}

	public void run() {
		this.originalRequestAttributes = RequestContextHolder.getRequestAttributes();

		try {
            RequestContextHolder.setRequestAttributes(delegateRequestAttributes);
			delegate.run();
		}
		finally {
			if(originalRequestAttributes == null) {
                RequestContextHolder.resetRequestAttributes();
			} else {
				RequestContextHolder.setRequestAttributes(originalRequestAttributes);
			}
			this.originalRequestAttributes = null;
		}
	}

	public String toString() {
		return delegate.toString();
	}

	/**
	 * Factory method for creating a {@link DelegatingRequestAttributesRunnable}.
	 *
	 * @param delegate the original {@link Runnable} that will be delegated to after
	 * establishing a {@link RequestAttributes} on the {@link RequestContextHolder}. Cannot
	 * have null.
	 * @param requestAttributes the {@link RequestContextHolder} to establish before invoking the
	 * delegate {@link Runnable}. If null, the current {@link RequestAttributes} from the
	 * {@link RequestContextHolder} will be used.
	 * @return the runnable
	 */
	public static Runnable create(Runnable delegate, RequestAttributes requestAttributes) {
		Assert.notNull(delegate, "delegate cannot be  null");
		return requestAttributes == null ? new DelegatingRequestAttributesRunnable(delegate)
				: new DelegatingRequestAttributesRunnable(delegate, requestAttributes);
	}
}
