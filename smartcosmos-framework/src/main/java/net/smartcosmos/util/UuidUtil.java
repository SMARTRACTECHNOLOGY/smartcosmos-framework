package net.smartcosmos.util;

import java.util.UUID;

import org.springframework.util.StringUtils;

/**
 * Motiviated by:
 * <p>
 * https://www.percona.com/blog/2014/12/19/store-uuid-optimized-way/
 *
 * @author tcross
 */
public final class UuidUtil {

    private UuidUtil() {
        // only static methods - no public constructor
    }

    public static UUID getUuidFromUrn(final String urn) {
        if (!StringUtils.isEmpty(urn)) {
            return UUID.fromString(urn.replaceFirst("urn:uuid:", ""));
        }
        return null;
    }

    /**
     * @param referenceUuid the reference UUID
     * @return the Sting version (in canonical UUID-as-String format) of an input UUID
     */
    public static String getUrnFromUuid(final UUID referenceUuid)

    {
        if (referenceUuid == null) {
            return null;
        }
        return "urn:uuid:" + referenceUuid.toString();
    }

    public static UUID getUuidFromAccountUrn(final String urn) {
        if (!StringUtils.isEmpty(urn)) {
            return UUID.fromString(urn.replaceFirst("urn:account:", ""));
        }
        return null;
    }

    /**
     * @param referenceUuid the reference UUID
     * @return the Sting version (in canonical UUID-as-String format) of an input UUID
     */
    public static String getAccountUrnFromUuid(final UUID referenceUuid)

    {
        if (referenceUuid == null) {
            return null;
        }
        return "urn:account:" + referenceUuid.toString();
    }
}
