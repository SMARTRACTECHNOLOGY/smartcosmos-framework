

package net.smartcosmos.objects.model.context;

import net.smartcosmos.model.base.IAccountDomainResource;
import net.smartcosmos.model.base.INamedObject;
import net.smartcosmos.model.base.ITypedObject;

/**
 * Simple construct used to establish a logical albeit arbitrarily defined peer relationship between
 * a collection of {@link IObjectInteraction} instances.
 * <p/>
 * The duration of an interaction session is arbitrary; it may be seconds, hours, days, or any
 * time period defined by the application data being captured.
 */
public interface IObjectInteractionSession extends IAccountDomainResource<IObjectInteractionSession>, INamedObject<IObjectInteractionSession>, ITypedObject
{
    /**
     * System-assigned timestamp established when the session is opened.
     *
     * @return Session start timestamp
     */
    long getStartTimestamp();

    void setStartTimestamp(long timestamp);

    /**
     * System-assigned timestamp defined when the session is closed.
     *
     * @return Session stop timestamp
     */
    long getStopTimestamp();

    void setStopTimestamp(long timestamp);
}
