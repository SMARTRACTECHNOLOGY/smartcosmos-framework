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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.TreeSet;

import org.junit.Test;

import net.smartcosmos.platform.api.IVersion;

/**
 * Although we didn't use a much code, a lot of these tests are borrowed from gradle-gitsemver.
 *
 */
public class VersionTest
{

    @Test(expected = IllegalArgumentException.class)
    public void testNonNegative()
    {
        new Version(1, 1, -1, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBasicStringWithExtraDot()
    {
        String versionString = "1.23.24.";
        Version.parseVersion(versionString);
    }

    @Test
    public void isValidIdentifier()
    {
        assertTrue(Version.isValidIdentifier("SNAPSHOT"));
        assertTrue(Version.isValidIdentifier("branchname-SNAPSHOT"));
        assertTrue(Version.isValidIdentifier("alpha.123.02020.fffff"));
    }

    @Test
    public void isValidBuildMetadata()
    {
        assertTrue(Version.isValidBuildMetadata("alpha.123.02020.fffff"));
    }

    @Test
    public void testCheckVersion()
    {
        new Version(1, 2, 3, "four", "asldkasdlkas");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckVersionInvalid()
    {
        new Version(-1, 2, 3, "four", "asldkasdlkas");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckVersionInvalidStr()
    {
        new Version(-1, 2, 3, "   ____$%%%%", "asldkasdlkas");
    }

    @Test
    public void testCheckVersionNumber()
    {
        new Version(1, 2, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckVersionNumberInvalid()
    {
        new Version(1, -30, 3);
    }

    @Test
    public void testCompare()
    {

        final TreeSet<Version> sorted = new TreeSet<>();

        Version two = new Version(2, 0, 0);

        sorted.add(two);
        Version one = new Version(1, 0, 0);
        sorted.add(one);
        Version oneBeta = new Version(1, 0, 0, "BETA");

        sorted.add(oneBeta);
        Version oneBetaMetadata = new Version(1, 0, 0, "BETA", "123013030");
        // We actually LOSE this one because it's the same as the beta version!
        sorted.add(oneBetaMetadata);
        Version oneNewFuncBugFixes = new Version(1, 1, 1);

        sorted.add(oneNewFuncBugFixes);
        Version oneNewFunctionality = new Version(1, 1, 0);

        sorted.add(oneNewFunctionality);

        assertTrue(Version.compare(one, two) < 0);
        assertTrue(Version.compare(two, one) > 0);
        assertTrue(String.valueOf(Version.compare(one, oneBeta)), Version.compare(one, oneBeta) > 0);

        assertEquals(0, Version.compare(oneBeta, oneBetaMetadata));

        Iterator<Version> iterator = sorted.iterator();

        Version test = iterator.next();
        assertEquals(test.toString(), 0, Version.compare(test, oneBeta));
        test = iterator.next();
        assertEquals(test.toString(), 0, Version.compare(test, one));
        test = iterator.next();
        assertEquals(test.toString(), 0, Version.compare(test, oneNewFunctionality));
        test = iterator.next();
        assertEquals(test.toString(), 0, Version.compare(test, oneNewFuncBugFixes));
        test = iterator.next();
        assertEquals(test.toString(), 0, Version.compare(test, two));
    }

    @Test
    public void testEqualComparison()
    {
        IVersion version = new Version(
                10,
                2,
                3,
                "dev.1.2.3",
                "build.30");
        IVersion sameVersion = new Version(
                10,
                2,
                3,
                "dev.1.2.3",
                "build.30");
        assertEquals(0, sameVersion.compareTo(version));
        assertEquals(0, version.compareTo(sameVersion));
    }

    private static void assertComparison(IVersion lower,
            IVersion higher)
    {
        assertTrue(higher.compareTo(lower) > 0);
        assertTrue(lower.compareTo(higher) < 0);
    }

    @Test
    public void testParseVersion()
    {
        IVersion version = Version.parseVersion("1.0.0-BETA+129312031293");
        assertEquals(Integer.valueOf(1), version.getMajor());
        assertEquals(Integer.valueOf(0), version.getMinor());
        assertEquals(Integer.valueOf(0), version.getPatch());
        assertEquals("BETA", version.getIdentifier());
        assertEquals("129312031293", version.getBuildMetadata());
    }

}
