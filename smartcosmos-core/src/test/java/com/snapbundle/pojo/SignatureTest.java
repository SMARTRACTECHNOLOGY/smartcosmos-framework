/*
 * SnapBundle™ SDK
 * (C) Copyright 2013-2014 Tag Dynamics, LLC (http://tagdynamics.com/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.snapbundle.pojo;

import com.snapbundle.model.base.EntityReferenceType;
import com.snapbundle.model.context.IAccount;
import com.snapbundle.model.context.IFile;
import com.snapbundle.pojo.context.Account;
import com.snapbundle.pojo.context.File;
import com.snapbundle.util.HashUtil;
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
