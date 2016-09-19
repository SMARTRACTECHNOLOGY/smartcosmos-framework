package net.smartcosmos.util;

import java.util.UUID;

import org.junit.*;

import static org.junit.Assert.assertTrue;

/**
 * Unit tests for UuidUtil class.
 */
public class UuidUtilTest {

    @Test
    public void testGetUuidFromUrn() throws Exception {
        UUID expectedResult = UUID.randomUUID();

        UUID actualResult = UuidUtil.getUuidFromUrn(UuidUtil.URN_PREFIX_UUID + expectedResult);
        assertTrue("The expected result '" + expectedResult + "' did not match the actual result '" + actualResult + ".",
                   expectedResult.equals(actualResult));
    }

    @Test
    public void testGetUuidFromUrn_Null() throws Exception {
        UUID expectedResult = null;

        UUID actualResult = UuidUtil.getUuidFromUrn(null);
        assertTrue("The expected result '" + expectedResult + "' did not match the actual result '" + actualResult + ".",
                   expectedResult ==  actualResult);
    }

    @Test
    public void testGetUuidFromUrn_NullString() throws Exception {
        UUID expectedResult = null;

        UUID actualResult = UuidUtil.getUuidFromUrn("");
        assertTrue("The expected result '" + expectedResult + "' did not match the actual result '" + actualResult + ".",
                   expectedResult ==  actualResult);
    }

    @Test
    public void testGetUuidFromUrn_BlankString() throws Exception {
        UUID expectedResult = null;

        UUID actualResult = UuidUtil.getUuidFromUrn("   ");
        assertTrue("The expected result '" + expectedResult + "' did not match the actual result '" + actualResult + ".",
                   expectedResult ==  actualResult);
    }

    @Test
    public void testGetUrnFromUuid() throws Exception {
        UUID uuid = UUID.randomUUID();
        String expectedResult = UuidUtil.URN_PREFIX_UUID + uuid;

        String actualResult = UuidUtil.getUrnFromUuid(uuid);
        assertTrue("The expected result '" + expectedResult + "' did not match the actual result '" + actualResult + ".",
                   expectedResult.equals(actualResult));
    }

    @Test
    public void testGetUrnFromUuid_Null() throws Exception {
        String expectedResult = null;

        String actualResult = UuidUtil.getUrnFromUuid(null);
        assertTrue("The expected result '" + expectedResult + "' did not match the actual result '" + actualResult + ".",
                   expectedResult == actualResult);
    }

    @Test
    public void testGetUuidFromAccountUrn() throws Exception {
        UUID expectedResult = UUID.randomUUID();

        UUID actualResult = UuidUtil.getUuidFromAccountUrn(UuidUtil.URN_PREFIX_ACCOUNT + expectedResult);
        assertTrue("The expected result '" + expectedResult + "' did not match the actual result '" + actualResult + ".",
                   expectedResult.equals(actualResult));
    }

    @Test
    public void testGetUuidFromAccountUrn_Null() throws Exception {
        UUID expectedResult = null;

        UUID actualResult = UuidUtil.getUuidFromAccountUrn(null);
        assertTrue("The expected result '" + expectedResult + "' did not match the actual result '" + actualResult + ".",
                   expectedResult == actualResult);
    }

    @Test
    public void testGetUuidFromAccountUrn_EmptyString() throws Exception {
        UUID expectedResult = null;

        UUID actualResult = UuidUtil.getUuidFromAccountUrn("");
        assertTrue("The expected result '" + expectedResult + "' did not match the actual result '" + actualResult + ".",
                   expectedResult == actualResult);
    }

    @Test
    public void testGetUuidFromAccountUrn_BlankStringl() throws Exception {
        UUID expectedResult = null;

        UUID actualResult = UuidUtil.getUuidFromAccountUrn("    ");
        assertTrue("The expected result '" + expectedResult + "' did not match the actual result '" + actualResult + ".",
                   expectedResult == actualResult);
    }

    @Test
    public void testGetAccountUrnFromUuid() throws Exception {
        UUID uuid = UUID.randomUUID();
        String expectedResult = UuidUtil.URN_PREFIX_ACCOUNT + uuid;

        String actualResult = UuidUtil.getAccountUrnFromUuid(uuid);
        assertTrue("The expected result '" + expectedResult + "' did not match the actual result '" + actualResult + ".",
                   expectedResult.equals(actualResult));
    }

    @Test
    public void testGetAccountUrnFromUuid_Null() throws Exception {
        String expectedResult = null;

        String actualResult = UuidUtil.getAccountUrnFromUuid(null);
        assertTrue("The expected result '" + expectedResult + "' did not match the actual result '" + actualResult + ".",
                   expectedResult == actualResult);
    }

    @Test
    public void testGetNewUuid() throws Exception {
        UUID uuid = UuidUtil.getNewUuid();
        assertTrue("uuid '" + uuid + "' does not match '" + uuid.toString() + "'.", uuid.equals(UUID.fromString(uuid.toString())));
    }

    @Test
    public void testGetNewUuidAsString() throws Exception {
        String uuid = UuidUtil.getNewUuidAsString();
        assertTrue("uuid string '" + uuid + "' does not match '" + UUID.fromString(uuid) + "'.", uuid.equals(UUID.fromString(uuid).toString()));
    }
}
