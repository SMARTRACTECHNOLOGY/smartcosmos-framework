package net.smartcosmos.util;

import java.util.UUID;

import lombok.AllArgsConstructor;

import org.apache.commons.lang.StringUtils;

import static lombok.AccessLevel.PRIVATE;

/**
 * Utilities for common UUID activities.
 * <p>
 * Inspired by:
 * <a href="https://www.percona.com/blog/2014/12/19/store-uuid-optimized-way/">https://www.percona.com/blog/2014/12/19/store-uuid-optimized-way/</a>
 * </p>
 * @deprecated SMART COSMOS Objects v3 does not generally rely on UUIDs. Although DAO layer implementations may use UUID for IDs, the Framework
 * puts no requirements on the ID type, this class therefore will be removed in future versions. Solutions therefore should provide their own
 * specific utilities if required.
 */
@Deprecated
@AllArgsConstructor(access = PRIVATE)
public final class UuidUtil {

    public static final String URN_SEPARATOR = ":";
    public static final String URN_PREFIX = "urn";
    public static final String URN_PREFIX_ACCOUNT = URN_PREFIX + URN_SEPARATOR + "account" + URN_SEPARATOR;
    public static final String URN_PREFIX_UUID = URN_PREFIX + URN_SEPARATOR + "uuid" + URN_SEPARATOR;

    /**
     *
     * @param urn the urn
     * @return the UUID
     * @throws IllegalArgumentException
     * @deprecated Default implementations in SMART COSMOS Objects v3 use a different scheme for URNs
     */
    @Deprecated
    public static UUID getUuidFromUrn(final String urn) {
        if (StringUtils.isNotBlank(urn) && urn.startsWith(URN_PREFIX_UUID)) {
            return UUID.fromString(StringUtils.substringAfterLast(urn, URN_SEPARATOR));
        }
        throw new IllegalArgumentException(String.format("Invalid URN: %s", urn));
    }

    /**
     * Create a URN for a provided UUID.
     *
     * @param referenceUuid the reference UUID
     * @return the String version (in canonical UUID-as-String format) of an input UUID
     * @deprecated Default implementations in SMART COSMOS Objects v3 use a different scheme for URNs
     */
    @Deprecated
    public static String getUrnFromUuid(final UUID referenceUuid) {
        if (referenceUuid == null) {
            return null;
        }
        return URN_PREFIX_UUID + referenceUuid.toString();
    }

    /**
     * Get the UUID portion of an account URN.
     *
     * @param urn the account URN
     * @return the UUID
     * @throws IllegalArgumentException
     * @deprecated SMART COSMOS Objects v3 uses a different scheme for tenant URNs
     */
    @Deprecated
    public static UUID getUuidFromAccountUrn(final String urn) {
        if (StringUtils.isNotBlank(urn)&& urn.startsWith(URN_PREFIX_ACCOUNT)) {
            return UUID.fromString(StringUtils.substringAfterLast(urn, URN_SEPARATOR));
        }
        throw new IllegalArgumentException(String.format("Invalid URN: %s", urn));
    }

    /**
     * Create an account URN for a provided UUID.
     *
     * @param referenceUuid the reference UUID
     * @return the Sting version (in canonical UUID-as-String format) of an input UUID
     * @deprecated SMART COSMOS Objects v3 uses a different scheme for tenant URNs
     */
    @Deprecated
    public static String getAccountUrnFromUuid(final UUID referenceUuid) {
        if (referenceUuid == null) {
            return null;
        }
        return URN_PREFIX_ACCOUNT + referenceUuid.toString();
    }

    /**
     * @return
     * @deprecated Use {@code UUID.randomUUID()} instead
     */
    @Deprecated
    public static UUID getNewUuid() {
        return UUID.randomUUID();
    }

    /**
     * @return
     * @deprecated Use {@code UUID.randomUUID().toString()} instead
     */
    @Deprecated
    public static String getNewUuidAsString() {
        return getNewUuid().toString();
    }
}
