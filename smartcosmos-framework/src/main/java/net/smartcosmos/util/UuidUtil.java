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
 */
@AllArgsConstructor(access = PRIVATE)
public final class UuidUtil {

    public static final String URN_SEPARATOR = ":";
    public static final String URN_PREFIX = "urn";
    public static final String URN_PREFIX_ACCOUNT = URN_PREFIX + URN_SEPARATOR + "account" + URN_SEPARATOR;
    public static final String URN_PREFIX_UUID = URN_PREFIX + URN_SEPARATOR + "uuid" + URN_SEPARATOR;

    public static UUID getUuidFromUrn(final String urn) {
        if (StringUtils.isNotBlank(urn)) {
            return UUID.fromString(StringUtils.substringAfterLast(urn, URN_SEPARATOR));
        }
        return null;
    }

    /**
     * Create a URN for a provided UUID.
     *
     * @param referenceUuid the reference UUID
     * @return the String version (in canonical UUID-as-String format) of an input UUID
     */
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
     */
    public static UUID getUuidFromAccountUrn(final String urn) {
        if (StringUtils.isNotBlank(urn)) {
            return UUID.fromString(StringUtils.substringAfterLast(urn, URN_SEPARATOR));
        }
        return null;
    }

    /**
     * Create an account URN for a provided UUID.
     *
     * @param referenceUuid the reference UUID
     * @return the Sting version (in canonical UUID-as-String format) of an input UUID
     */
    public static String getAccountUrnFromUuid(final UUID referenceUuid) {
        if (referenceUuid == null) {
            return null;
        }
        return URN_PREFIX_ACCOUNT + referenceUuid.toString();
    }

    public static UUID getNewUuid() {
        return UUID.randomUUID();
    }

    public static String getNewUuidAsString() {
        return getNewUuid().toString();
    }
}
