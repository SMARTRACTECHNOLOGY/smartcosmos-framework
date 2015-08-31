package net.smartcosmos.platform.base;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Server API
 * ===============================================================================
 * Copyright (C) 2013 - 2015 Smartrac Technology Fletcher, Inc.
 * ===============================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;

import net.smartcosmos.platform.api.IVersion;

/**
 * Default, concrete implementation of the {@link IVersion} interface.
 * <p>
 * This version object takes an optional identifier and build metadata. The qualifier and build metadata may only
 * contain characters are that are valid Java.
 * 
 * @see <a href="http://semver.org/">Semantic Versioning 2.0.0</a>
 * 
 */
public class Version implements IVersion
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final String NUMBER_FORMAT = "(\\d+)(\\.(\\d+))(\\.(\\d+))";
    private static final String IDENTIFIER_FORMAT = "(-(([0-9A-Za-z-]+)(\\.[0-9A-Za-z-]+)*))?";
    private static final String BUILD_METADATA_FORMAT = "(\\+(([0-9A-Za-z-]+)(\\.[0-9A-Za-z-]+)*))?";

    public static final Pattern PATTERN = Pattern
            .compile("^" + NUMBER_FORMAT + IDENTIFIER_FORMAT + BUILD_METADATA_FORMAT + "$");

    /**
     * Used to check if someone tries to sneak invalid stuff into the Identifier or Build Metadata. REGEX taken mostly
     * from here:
     * http://stackoverflow.com/questions/8248277/how-to-determine-if-a-string-has-non-alphanumeric-characters
     */
    public static final Pattern IDENTIFIER_PATTERN = Pattern.compile("[^a-zA-Z0-9-\\.]");
    public static final Pattern BUILD_METADATA_PATTERN = Pattern.compile("[^a-zA-Z0-9-\\.]");

    /**
     * Compares the versions for order. Returns a negative integer, zero, or a positive integer as the first version is
     * less than, equal to, or greater than the second version.
     * 
     * Remember, build metadata is completely ignored in a comparison, and any identifier means the provided version is
     * less than the second.
     * 
     * @return a negative integer, zero, or a positive integer as the first version is less than, equal to, or greater
     *         than the second version
     */
    public static int compare(final IVersion one, final IVersion two)
    {
        Preconditions.checkNotNull(one);
        Preconditions.checkNotNull(two);

        int cmp;

        // major
        cmp = Double.compare(one.getMajor(), two.getMajor());
        if (cmp != 0)
            return cmp;

        // minor
        cmp = Double.compare(one.getMinor(), two.getMinor());
        if (cmp != 0)
            return cmp;

        // patch
        cmp = Double.compare(one.getPatch(), two.getPatch());
        if (cmp != 0)
            return cmp;

        if (one.getIdentifier().isEmpty())
        {
            if (two.getIdentifier().length() > 0)
            {
                // one is a release, and two is a pre-release
                return 1;
            } else
            {
                // Both strings are null.
                return 0;
            }
        } else
        {
            if (two.getIdentifier().isEmpty())
            {
                // two is a release, and one is a pre-release
                return -1;
            } else
            {
                // Both are pre-release, so just compare the two identifiers.
                return one.getIdentifier().compareTo(two.getIdentifier());
            }
        }

    }

    /**
     * Returns <code>true</code>if the specified qualifier is legal, namely, if the string is either the empty string,
     * and MUST comprise only ASCII alphanumerics, hyphen, and period. [0-9A-Za-z-]
     * 
     * @param identifier
     *            the qualifier to check
     * @return <code>true</code> if a legal qualifier; <code>false</code> otherwise
     * @throws NullPointerException
     *             if the specified string is <code>null</code>
     */
    public static boolean isValidIdentifier(@Nonnull final String identifier)
    {
        return !IDENTIFIER_PATTERN.matcher(identifier).find();
    }

    /**
     * Returns <code>true</code>if the specified qualifier is legal, namely, if the string is either the empty string,
     * and MUST comprise only ASCII alphanumerics, hyphen, and period. [0-9A-Za-z-]
     * 
     * @param buildMetadata
     *            the qualifier to check
     * @return <code>true</code> if a legal buildMetadata; <code>false</code> otherwise
     * @throws NullPointerException
     *             if the specified string is <code>null</code>
     */
    public static boolean isValidBuildMetadata(@Nonnull final String buildMetadata)
    {
        return !BUILD_METADATA_PATTERN.matcher(buildMetadata).find();
    }

    /**
     * Returns <code>true</code> if the arguments identify a legal version; <code>false</code> otherwise.
     * 
     * @param major
     *            the major version number
     * @param minor
     *            the minor version number
     * @param patch
     *            the patch version number
     * @param identifier
     *            the version qualifier
     * @param buildMetadata
     *            the build metadata
     * @return <code>true</code> if the arguments identify a legal version; <code>false</code> otherwise.
     */
    public static boolean isValidVersion(final int major, final int minor, final int patch, @Nonnull final String identifier,
            @Nonnull final String buildMetadata)
    {
        if (!isValidVersionNumber(major, minor, patch))
        {
            return false;
        }
        if (!isValidIdentifier(identifier))
        {
            return false;
        }
        if (!isValidBuildMetadata(buildMetadata))
        {
            return false;
        }
        return true;
    }

    /**
     * Returns true if any of three numbers are negative.
     * 
     * @param major
     *            the major version number
     * @param minor
     *            the minor version number
     * @param patch
     *            the patch version number
     * @return <code>true</code> if all the numbers are non-negative; <code>false</code> otherwise
     */
    public static boolean isValidVersionNumber(final int major, final int minor, final int patch)
    {
        if (major < 0)
            return false;
        if (minor < 0)
            return false;
        if (patch < 0)
            return false;
        return true;
    }

    /**
     * Creates a version string for the specified version numbers.
     * 
     * @param major
     *            the major version number, i.e., the '1' in 1.2.3-q
     * @param minor
     *            the minor version number, i.e., the '2' in 1.2.3-q
     * @param patch
     *            the patch version number, i.e., the '3' in 1.2.3-q
     * @param identifier
     *            the version qualifier, i.e., the 'q' in 1.2.3-q
     * @return a string representing the specified version
     * @throws IllegalArgumentException
     */
    public static String makeVersionString(final int major, final int minor, final int patch, @Nonnull final String identifier,
            @Nonnull final String buildMetadata)
    {
        boolean hasQualifier = identifier != null && identifier.length() > 0;
        boolean hasBuildMetadata = buildMetadata != null && buildMetadata.length() > 0;

        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(major));
        sb.append('.');
        sb.append(Integer.toString(minor));
        sb.append('.');
        sb.append(Integer.toString(patch));
        if (hasQualifier)
        {
            sb.append('-');
            sb.append(identifier);
        }

        if (hasBuildMetadata)
        {
            sb.append('+');
            sb.append(buildMetadata);
        }
        return sb.toString();
    }

    /**
     * Tries to transform the specified character sequence into a version object.
     * 
     * @param verStr
     *            the sequence of characters to be transformed
     * @return the version
     * @throws NullPointerException
     *             if the character sequence is <code>null</code>
     * @throws IllegalArgumentException
     *             if the character sequence does not correspond to a legal version
     */
    public static IVersion parseVersion(final CharSequence verStr)
    {
        Preconditions.checkNotNull(verStr);

        Matcher m = PATTERN.matcher(verStr.toString().trim());
        if (!m.matches())
        {
            throw new IllegalArgumentException("Could not parse version string: " + verStr);
        }

        String majorStr = m.group(1);
        final int major = Integer.parseInt(majorStr);

        String minorStr = m.group(3);
        final int minor = Integer.parseInt(minorStr);

        String patchStr = m.group(5);
        final int patch = Integer.parseInt(patchStr);

        final String identifier = m.group(7);

        final String buildMetadata = m.group(11);

        if (!isValidVersionNumber(major, minor, patch))
        {
            throw new IllegalArgumentException("Invalid Version number: " + major + "." + minor + "." + patch);
        }

        return new Version(major, minor, patch, identifier, buildMetadata);
    }

    // final instance fields
    private final Integer major;

    private final Integer minor;

    private final Integer patch;

    private final String identifier;

    private final String buildMetadata;

    private transient String toString;

    /**
     * Creates a new version object with the specified version numbers.
     * <p>
     * Clients should normally obtain instances of this class via the static {@code getVersion} methods.
     * 
     * @param major
     *            the major version number, i.e., the '1' in 1.2.3
     * @param minor
     *            the minor version number, i.e., the '2' in 1.2.3
     * @param patch
     *            the patch version number, i.e., the '3' in 1.2.3
     * @throws IllegalArgumentException
     *             if any of the version numbers are negative
     */
    public Version(final Integer major, final Integer minor, final Integer patch)
    {
        this(major, minor, patch, "");
    }

    /**
     * Creates a new version object with the specified version numbers.
     * <p>
     * Clients should normally obtain instances of this class via the static {@code getVersion} methods.
     * 
     * @param major
     *            the major version number, i.e., the '1' in 1.2.3-i
     * @param minor
     *            the minor version number, i.e., the '2' in 1.2.3-i
     * @param patch
     *            the patch version number, i.e., the '3' in 1.2.3-i
     * @param identifier
     *            the pre-release identifier, i.e., the 'i' in 1.2.3-i
     * @throws IllegalArgumentException
     *             if any of the version numbers are negative, or the identifier is not a legal identifier
     */
    public Version(final Integer major, final Integer minor, final Integer patch, final String identifier)
    {
        this(major, minor, patch, identifier, "");
    }

    /**
     * Creates a new version object with the specified version numbers.
     * <p>
     * Clients should normally obtain instances of this class via the static {@code getVersion} methods.
     * 
     * @param major
     *            the major version number, i.e., the '1' in 1.2.3-i
     * @param minor
     *            the minor version number, i.e., the '2' in 1.2.3-i
     * @param patch
     *            the patch version number, i.e., the '3' in 1.2.3-i
     * @param identifier
     *            the pre-release identifier, i.e., the 'i' in 1.2.3-i
     * @param buildMetadata
     *            the build metadata, i.e., the 'm' in 1.2.3-i+m
     * @throws IllegalArgumentException
     *             if any of the version numbers are negative, or the identifier is not a legal identifier
     */
    public Version(final Integer major, final Integer minor, final Integer patch, final String identifier, final String buildMetadata)
    {
        if (!isValidVersion(major, minor, patch, identifier, buildMetadata))
        {
            throw new IllegalArgumentException(
                    "Invalid version string: " + makeVersionString(major, minor, patch, identifier, buildMetadata));
        }

        // field assignments
        this.major = major;
        this.minor = minor;
        this.patch = patch;
        this.identifier = identifier;
        this.buildMetadata = buildMetadata;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(final IVersion o)
    {
        return compare(this, o);
    }

    @Override
    public String getBuildMetadata()
    {
        return buildMetadata;
    }

    @Override
    public String getIdentifier()
    {
        return identifier;
    }

    @Override
    public Integer getMajor()
    {
        return major;
    }

    @Override
    public Integer getMinor()
    {
        return minor;
    }

    @Override
    public Integer getPatch()
    {
        return patch;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((buildMetadata == null) ? 0 : buildMetadata.hashCode());
        result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
        result = prime * result + ((major == null) ? 0 : major.hashCode());
        result = prime * result + ((minor == null) ? 0 : minor.hashCode());
        result = prime * result + ((patch == null) ? 0 : patch.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Version other = (Version) obj;
        if (buildMetadata == null)
        {
            if (other.buildMetadata != null)
                return false;
        } else if (!buildMetadata.equals(other.buildMetadata))
            return false;
        if (identifier == null)
        {
            if (other.identifier != null)
                return false;
        } else if (!identifier.equals(other.identifier))
            return false;
        if (major == null)
        {
            if (other.major != null)
                return false;
        } else if (!major.equals(other.major))
            return false;
        if (minor == null)
        {
            if (other.minor != null)
                return false;
        } else if (!minor.equals(other.minor))
            return false;
        if (patch == null)
        {
            if (other.patch != null)
                return false;
        } else if (!patch.equals(other.patch))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        if (toString == null)
            toString = makeVersionString(major, minor, patch, identifier, buildMetadata);
        return toString;
    }
}