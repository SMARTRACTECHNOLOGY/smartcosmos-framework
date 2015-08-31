package net.smartcosmos.platform.api;

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

import java.io.Serializable;

/**
 * Standardized versioning mechanism used by plugin archetypes to define API versions.
 * <p/>
 * A normal version number MUST take the form X.Y.Z-Q+M where X, Y, and Z are integers, Q is a pre-release identifier
 * for development use only, and M is build metadata that is outright ignored.
 * <p/>
 * <ul>
 * <li>X is the major version</li>
 * <li>Y is the minor version</li>
 * <li>Z is the patch version</li>
 * <li>Q is the pre-release identifier</li>
 * <li>M is the build metadata</li>
 * </ul>
 * <p/>
 * Each element MUST increase numerically, except the identifier or build metadata. For instance: 1.9.0 &lt; 1.10.0 &lt;
 * 1.11.0.
 *
 * @see <a href="http://semver.org/">Semantic Versioning 2.0.0</a>
 */
public interface IVersion extends Comparable<IVersion>, Serializable
{

    /**
     * Major version.
     * <p>
     * Major version X (X.y.z | X > 0) MUST be incremented if any <b>backwards <i>incompatible</i> public API</b>
     * functionality is introduced. It MAY include minor and patch level changes.
     * </p>
     *
     * @return Major version
     */
    Integer getMajor();

    /**
     * Minor version.
     * <p>
     * Minor version Y (x.Y.z | x > 0) MUST be incremented if new, <b>backwards compatible public API</b> functionality
     * is introduced. It MAY be incremented if substantial new functionality or improvements are introduced within the
     * private code. It MAY include patch level changes.
     * </p>
     *
     * @return Minor version
     */
    Integer getMinor();

    /**
     * Patch version.
     * <p>
     * Patch version Z (x.y.Z | x > 0) MUST be incremented if only backwards compatible bug fixes are introduced. A bug
     * fix is defined as an internal change that fixes incorrect behavior.
     * </p>
     *
     * @return Patch version
     */
    Integer getPatch();

    /**
     * Identifier
     * <p>
     * A pre-release version MAY be denoted by appending a hyphen and a series of dot separated identifiers immediately
     * following the patch version. Identifiers MUST comprise only ASCII alphanumerics and hyphen [0-9A-Za-z-].
     * Identifiers MUST NOT be empty. Numeric identifiers MUST NOT include leading zeroes. Pre-release versions have a
     * lower precedence than the associated normal version. A pre-release version indicates that the version is unstable
     * and might not satisfy the intended compatibility requirements as denoted by its associated normal version.
     * Examples: 1.0.0-alpha, 1.0.0-alpha.1, 1.0.0-0.3.7, 1.0.0-x.7.z.92.
     * </p>
     * 
     * @return Optional Identifier or null if this is a non development release.
     */
    String getIdentifier();

    /**
     * Build Metadata
     * <p>
     * Build metadata MAY be denoted by appending a plus sign and a series of dot separated identifiers immediately
     * following the patch or pre-release version. Identifiers MUST comprise only ASCII alphanumerics and hyphen
     * [0-9A-Za-z-]. Identifiers MUST NOT be empty. Build metadata SHOULD be ignored when determining version
     * precedence. Thus two versions that differ only in the build metadata, have the same precedence. Examples:
     * 1.0.0-alpha+001, 1.0.0+20130313144700, 1.0.0-beta+exp.sha.5114f85.
     * </p>
     * 
     * @return
     */
    String getBuildMetadata();

    /**
     * Use this method to determine if one version is semantically compatible with another version.
     * <p>
     * Based on the definition of <a href="http://semver.org/">Semantic Versioning</a>, a MINOR increment may include
     * new, backwards compatible functionality. Consider version 1.0 that includes the sole method
     * <code>public int foo()</code>. Along comes version 1.1, that maintains the status quo on the <code>foo</code>
     * method, but adds a brand new method <code>public int bar()</code>. Consider these results:
     * </p>
     * <p>
     * Version 1.0 is <code>VersionCompatibility.Incompatible</code> with version 1.1 because the caller will expect the
     * version to support the new method <code>bar()</code>, which version 1.0 fails to define.
     * </p>
     * <p>
     * Version 1.1 is <code>VersionCompatibility.Compatible</code> with version 1.0 because the caller is guaranteed
     * that 100% of the methods defined in version 1.0 exist in version 1.0.
     * </p>
     * <p>
     * Finally, Version 1.x is always <code>VersionCompatibility.Incompatible</code> with version 2.x, since backwards
     * incompatible changes were introduced into the public API.
     * </p>
     * <p>
     * In situations where the <code>IVersion</code> fails to define a minor version number, the compatibility check
     * will always return <code>VersionCompatibility.Undetermined</code>.
     * </p>
     * <p>
     * Precedence refers to how versions are compared to each other when ordered. Precedence MUST be calculated by
     * separating the version into major, minor, patch and pre-release identifiers in that order (Build metadata does
     * not figure into precedence). Precedence is determined by the first difference when comparing each of these
     * identifiers from left to right as follows: Major, minor, and patch versions are always compared numerically.
     * Example: 1.0.0 < 2.0.0 < 2.1.0 < 2.1.1. When major, minor, and patch are equal, a pre-release version has lower
     * precedence than a normal version. Example: 1.0.0-alpha < 1.0.0. Precedence for two pre-release versions with the
     * same major, minor, and patch version MUST be determined by comparing each dot separated identifier from left to
     * right until a difference is found as follows: identifiers consisting of only digits are compared numerically and
     * identifiers with letters or hyphens are compared lexically in ASCII sort order. Numeric identifiers always have
     * lower precedence than non-numeric identifiers. A larger set of pre-release fields has a higher precedence than a
     * smaller set, if all of the preceding identifiers are equal. Example: 1.0.0-alpha < 1.0.0-alpha.1 <
     * 1.0.0-alpha.beta < 1.0.0-beta < 1.0.0-beta.2 < 1.0.0-beta.11 < 1.0.0-rc.1 < 1.0.0.
     * </p>
     *
     * @param version
     *            Version to compare for semantic (API) compatibility
     * @return true, if the version are semantically (API) compatible
     */
    @Override
    int compareTo(IVersion o);

}