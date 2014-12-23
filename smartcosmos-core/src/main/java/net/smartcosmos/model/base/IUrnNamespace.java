package net.smartcosmos.model.base;

/**
 * Sole means of uniquely identifying a specific object in the SMART COSMOS platform. Developers must not rely on
 * the {@link IDomainResource#getUniqueId()} value, as neither its uniqueness nor
 * consistency is guaranteed by the SMART COSMOS platform.
 */
public interface IUrnNamespace
{
    /**
     * System assigned, globally unique key used to reference a specific record. When
     * defining a referential object, this URN maps to the
     * {@link IReferentialObject#getReferenceUrn()} value.
     *
     * @return System-assigned UUID
     * @see IReferentialObject
     */
    String getUrn();

    void setUrn(String urn);

    /**
     * System managed field for tracking the last time the record was updated. This field
     * is predominately used to create a valid ETag for client-side cache management.
     *
     * @return Last modified time (based on the Unix timestamp)
     */
    long getLastModifiedTimestamp();
}
