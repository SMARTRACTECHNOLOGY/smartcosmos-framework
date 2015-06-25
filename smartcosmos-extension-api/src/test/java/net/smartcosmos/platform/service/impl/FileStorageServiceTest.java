package net.smartcosmos.platform.service.impl;

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

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.UUID;

import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.model.context.IUser;
import net.smartcosmos.objects.model.context.IFile;
import net.smartcosmos.objects.pojo.context.File;
import net.smartcosmos.platform.api.IContext;
import net.smartcosmos.platform.pojo.service.StorageRequest;
import net.smartcosmos.platform.pojo.service.StorageRequest.StorageRequestBuilder;
import net.smartcosmos.platform.pojo.service.StorageResponse;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.net.MediaType;

/**
 * It's kind of hard to really test this class, since technically you're not supposed to hit the outside file system
 * during a functional test. I'm bending the rules, since it creates a temporary filestore.
 * 
 * @author voor
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class FileStorageServiceTest
{

    FileStorageService service;

    @Mock
    IContext context;
    @Mock
    IUser user;

    @Mock
    IAccount account;

    @Mock
    IFile file;

    @Before
    public void setUp() throws Exception
    {

        when(user.getAccount()).thenReturn(account);
        when(file.getEntityReferenceType()).thenReturn(EntityReferenceType.Account);
        when(file.getReferenceUrn()).thenReturn(UUID.randomUUID().toString());
        when(account.getUrn()).thenReturn(UUID.randomUUID().toString());
        when(file.getTimestamp()).thenReturn(System.currentTimeMillis());

        service = new FileStorageService();

        // We don't use anything from the context at the moment.
        service.setContext(context);

        service.initialize();

    }

    /**
     * We have to teardown because we need to remove that temporary directory.
     * 
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception
    {
        service.stop();
    }

    @Test
    public void testInitialize()
    {
        // This is just making sure we can "accidentally" call initialize after we already did.
        service.initialize();

        assertNotNull(service);
    }

    @Test
    public void testFileStorageService()
    {
        assertNotNull(service);
    }

    @Test
    public void testIsHealthy()
    {
        // We don't want to check if this is true or false, we just want to make sure no errors are thrown.
        // If we ran this on a machine with zero disk space that's not a failure of the test.
        service.isHealthy();
    }

    @Test
    public void testStore() throws IOException
    {

        final String filename = "randomFile.json";
        //
        String mockJson = IOUtils.toString(getClass().getResourceAsStream("/mock-json.json"));

        StorageRequest request = new StorageRequestBuilder(IOUtils.toInputStream(mockJson, "UTF-8"))
                .setContentType(MediaType.JSON_UTF_8.toString()).setFileName(filename).setFile(file).setUser(user)
                .build();
        StorageResponse response = service.store(request);

        // I just hashed the file with md5sum, that's where this came from.
        assertEquals("ae2889279c86a8a5b55a0c53e03ac325", response.getDigitalSignature());

    }

    @Test
    public void testRetrieve() throws IOException
    {
        final String filename = "randomFile.json";
        //
        String mockJson = IOUtils.toString(getClass().getResourceAsStream("/mock-json.json"));

        StorageRequest request = new StorageRequestBuilder(IOUtils.toInputStream(mockJson, "UTF-8"))
                .setContentType(MediaType.JSON_UTF_8.toString()).setFileName(filename).setFile(file).setUser(user)
                .build();
        StorageResponse response = service.store(request);

        // I just hashed the file with md5sum, that's where this came from.
        assertEquals("ae2889279c86a8a5b55a0c53e03ac325", response.getDigitalSignature());

        IFile file = new File();
        file.setUrl(response.getUrl());

        String mockRetrieve = IOUtils.toString(this.service.retrieve(file));

        assertEquals(mockJson, mockRetrieve);
    }

    @Test
    public void testDelete() throws IOException
    {
        final String filename = "randomFile.json";
        //
        String mockJson = IOUtils.toString(getClass().getResourceAsStream("/mock-json.json"));

        StorageRequest request = new StorageRequestBuilder(IOUtils.toInputStream(mockJson, "UTF-8"))
                .setContentType(MediaType.JSON_UTF_8.toString()).setFileName(filename).setFile(file).setUser(user)
                .build();
        StorageResponse response = service.store(request);

        // I just hashed the file with md5sum, that's where this came from.
        assertEquals("ae2889279c86a8a5b55a0c53e03ac325", response.getDigitalSignature());

        IFile file = new File();
        file.setUrl(response.getUrl());

        service.delete(file);
    }

    @Test
    public void testGetServiceId()
    {
        assertEquals("646DAF98-9080-4E75-8BF1-D6D1D8BC30FA", service.getServiceId());
    }

    @Test
    public void testGetName()
    {
        assertEquals("File Storage Service", service.getName());
    }

    @Test
    public void testSetContext()
    {

        // Make sure setting the context again doesn't cause any problems.
        this.service.setContext(context);
    }

    @Test
    public void testStop() throws Exception
    {
        this.service.stop();
    }

}
