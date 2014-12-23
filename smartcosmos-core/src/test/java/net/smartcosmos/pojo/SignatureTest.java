package net.smartcosmos.pojo;

import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.objects.model.context.IFile;
import net.smartcosmos.pojo.context.Account;
import net.smartcosmos.objects.pojo.context.File;
import net.smartcosmos.util.HashUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SignatureTest
{
    @Test
    public void testSignature() throws NoSuchAlgorithmException, JSONException
    {
        IAccount mockAccount = new Account();
        mockAccount.setUrn("1234-456-7890");

        IFile mockFile = new File();
        mockFile.setAccount(mockAccount);
        mockFile.setUrn("0987-654-321");
        mockFile.setEntityReferenceType(EntityReferenceType.Object);
        mockFile.setReferenceUrn("urn:uuid:123123123123");
        mockFile.setUrl("http://localhost:8080/foo/bar");

        final String MESSAGE = "The quick brown fox jumps over the lazy dog";

        ByteArrayInputStream validateStream = new ByteArrayInputStream(MESSAGE.getBytes());
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        int bytesRead;
        byte[] block = new byte[8];
        while ((bytesRead = validateStream.read(block, 0, 8)) > 0)
        {
            digest.update(block, 0, bytesRead);
        }
        byte[] validationSignature = digest.digest();

        JSONObject signedFile = HashUtil.signFile(mockFile, validationSignature);
        System.out.println(signedFile.toString(3));

        System.out.println("Length: " + signedFile.toString(3).length());
    }
}
