package net.smartcosmos.concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.web.context.request.RequestAttributes;

/**
 * An {@link ExecutorService} which wraps each {@link Runnable} in a
 * {@link DelegatingRequestAttributesRunnable} and each {@link Callable} in a
 * {@link DelegatingRequestAttributesCallable}.
 *
 * @see org.springframework.security.concurrent.DelegatingSecurityContextExecutorService
 * @author Robert Van Voorhees
 */
public class DelegatingRequestAttributesExecutorService extends
                                                        DelegatingRequestAttributesExecutor implements ExecutorService {
    /**
     * Creates a new {@link org.springframework.security.concurrent.DelegatingSecurityContextExecutorService} that uses the
     * specified {@link RequestAttributes}.
     *
     * @param delegateExecutorService the {@link ExecutorService} to delegate to. Cannot
     * be null.
     * @param requestAttributes the {@link RequestAttributes} to use for each
     * {@link DelegatingRequestAttributesRunnable} and each
     * {@link DelegatingRequestAttributesCallable}.
     */
    public DelegatingRequestAttributesExecutorService(
        ExecutorService delegateExecutorService, RequestAttributes requestAttributes) {
        super(delegateExecutorService, requestAttributes);
    }

    /**
     * Creates a new {@link DelegatingRequestAttributesExecutorService} that uses the
     * current {@link RequestAttributes} from the {@link org.springframework.web.context.request.RequestContextHolder}.
     *
     * @param delegate the {@link ExecutorService} to delegate to. Cannot be
     * null.
     */
    public DelegatingRequestAttributesExecutorService(ExecutorService delegate) {
        this(delegate, null);
    }

    public final void shutdown() {
        getDelegate().shutdown();
    }

    public final List<Runnable> shutdownNow() {
        return getDelegate().shutdownNow();
    }

    public final boolean isShutdown() {
        return getDelegate().isShutdown();
    }

    public final boolean isTerminated() {
        return getDelegate().isTerminated();
    }

    public final boolean awaitTermination(long timeout, TimeUnit unit)
        throws InterruptedException {
        return getDelegate().awaitTermination(timeout, unit);
    }

    public final <T> Future<T> submit(Callable<T> task) {
        task = wrap(task);
        return getDelegate().submit(task);
    }

    public final <T> Future<T> submit(Runnable task, T result) {
        task = wrap(task);
        return getDelegate().submit(task, result);
    }

    public final Future<?> submit(Runnable task) {
        task = wrap(task);
        return getDelegate().submit(task);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public final List invokeAll(Collection tasks) throws InterruptedException {
        tasks = createTasks(tasks);
        return getDelegate().invokeAll(tasks);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public final List invokeAll(Collection tasks, long timeout, TimeUnit unit)
        throws InterruptedException {
        tasks = createTasks(tasks);
        return getDelegate().invokeAll(tasks, timeout, unit);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public final Object invokeAny(Collection tasks) throws InterruptedException,
                                                           ExecutionException {
        tasks = createTasks(tasks);
        return getDelegate().invokeAny(tasks);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public final Object invokeAny(Collection tasks, long timeout, TimeUnit unit)
        throws InterruptedException, ExecutionException, TimeoutException {
        tasks = createTasks(tasks);
        return getDelegate().invokeAny(tasks, timeout, unit);
    }

    private <T> Collection<Callable<T>> createTasks(Collection<Callable<T>> tasks) {
        if (tasks == null) {
            return null;
        }
        List<Callable<T>> results = new ArrayList<Callable<T>>(tasks.size());
        for (Callable<T> task : tasks) {
            results.add(wrap(task));
        }
        return results;
    }

    private ExecutorService getDelegate() {
        return (ExecutorService) getDelegateExecutor();
    }
}
