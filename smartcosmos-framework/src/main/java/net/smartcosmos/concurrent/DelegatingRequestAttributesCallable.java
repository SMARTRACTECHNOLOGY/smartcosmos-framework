package net.smartcosmos.concurrent;

import java.util.concurrent.Callable;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * <p>
 * Wraps a delegate {@link Callable} with logic for setting up a
 * {@link RequestAttributes} before invoking the delegate {@link Callable} and
 * then removing the {@link RequestAttributes} after the delegate has completed.
 * </p>
 * <p>
 * If there is a {@link RequestAttributes} that already exists, it will be
 * restored after the {@link #call()} method is invoked.
 * </p>
 *
 * @see org.springframework.security.concurrent.DelegatingSecurityContextCallable
 * @author Robert Van Voorhees
 */
public final class DelegatingRequestAttributesCallable<V> implements Callable<V> {

	private final Callable<V> delegate;


	/**
	 * The {@link RequestAttributes} that the delegate {@link Callable} will be
	 * ran as.
	 */
	private final RequestAttributes delegateRequestAttributes;

	/**
	 * The {@link RequestAttributes} that was on the {@link RequestContextHolder}
	 * prior to being set to the delegateSecurityContext.
	 */
	private RequestAttributes originalRequestAttributes;

	/**
	 * Creates a new {@link DelegatingRequestAttributesCallable} with a specific
	 * {@link RequestAttributes}.
	 * @param delegate the delegate {@link DelegatingRequestAttributesCallable} to run with
	 * the specified {@link RequestAttributes}. Cannot be null.
	 * @param requestAttributes the {@link RequestAttributes} to establish for the delegate
	 * {@link Callable}. Cannot be null.
	 */
	public DelegatingRequestAttributesCallable(Callable<V> delegate,
                                               RequestAttributes requestAttributes) {
		Assert.notNull(delegate, "delegate cannot be null");
		Assert.notNull(requestAttributes, "requestAttributes cannot be null");
		this.delegate = delegate;
		this.delegateRequestAttributes = requestAttributes;
	}

	/**
	 * Creates a new {@link DelegatingRequestAttributesCallable} with the
	 * {@link SecurityContext} from the {@link SecurityContextHolder}.
	 * @param delegate the delegate {@link Callable} to run under the current
	 * {@link SecurityContext}. Cannot be null.
	 */
	public DelegatingRequestAttributesCallable(Callable<V> delegate) {
		this(delegate, RequestContextHolder.getRequestAttributes());
	}

	public V call() throws Exception {
		this.originalRequestAttributes = RequestContextHolder.getRequestAttributes();

		try {
			RequestContextHolder.setRequestAttributes(this.delegateRequestAttributes);
			return delegate.call();
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
	 * Creates a {@link DelegatingRequestAttributesCallable} and with the given
	 * {@link Callable} and {@link RequestAttributes}, but if the securityContext is null
	 * will defaults to the current {@link RequestAttributes} on the
	 * {@link RequestContextHolder}
	 *
	 * @param delegate the delegate {@link DelegatingRequestAttributesCallable} to run with
	 * the specified {@link RequestAttributes}. Cannot be null.
	 * @param requestAttributes the {@link RequestAttributes} to establish for the delegate
	 * {@link Callable}. If null, defaults to {@link SecurityContextHolder#getContext()}
	 * @return
	 */
	public static <V> Callable<V> create(Callable<V> delegate,
                                         RequestAttributes requestAttributes) {
		return requestAttributes == null ? new DelegatingRequestAttributesCallable<V>(
				delegate) : new DelegatingRequestAttributesCallable<V>(delegate,
                                                                       requestAttributes);
	}
}
