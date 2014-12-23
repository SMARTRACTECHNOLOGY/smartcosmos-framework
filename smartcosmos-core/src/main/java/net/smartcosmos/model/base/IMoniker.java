package net.smartcosmos.model.base;

/**
 * Arbitrary textual field that is guaranteed to <b>never</b> be directly interpreted by the platform. Monikers are
 * provided to developers to store additional contextual data outside of the scope defined by the platform. A typical
 * usage is to store an external system alias or key, linking the SMART COSMOS entity to an entity managed and stored
 * outside of the platform.
 * <p/>
 * If a moniker value is assigned, it can be reset to its original (default) null value by passing in the special
 * {@link net.smartcosmos.Field#NULL_MONIKER} value. This special value is needed because the various levels of
 * {@link net.smartcosmos.util.json.ViewType} may preclude an instance from having the actual moniker value, and if that
 * instance were submitted during an update operation it wouldn't be clear if the value was omitted in order to reduce
 * network bandwidth consumption or if it was omitted because the developer actually wanted null assigned. The use of
 * the special NULL_MONIKER solves this problem.
 */
public interface IMoniker
{
    String getMoniker();

    void setMoniker(String moniker);
}
