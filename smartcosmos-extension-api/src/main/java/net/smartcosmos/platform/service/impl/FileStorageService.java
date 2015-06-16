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

import io.dropwizard.lifecycle.Managed;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import net.smartcosmos.objects.model.context.IFile;
import net.smartcosmos.platform.api.service.IStorageService;
import net.smartcosmos.platform.base.AbstractService;
import net.smartcosmos.platform.pojo.service.StorageRequest;
import net.smartcosmos.platform.pojo.service.StorageResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;

/**
 * Just a convenient storage service that uses the current file system. Great for testing or development.
 * 
 * This will create a temporary working folder inside your system's temporary directory, enabling you to create files
 * and observe them outside of Smart Cosmos.
 * 
 * NOT FOR PRODUCTION ENVIRONMENTS.
 * 
 * @author voor
 *
 */
public class FileStorageService extends AbstractService implements IStorageService, Managed
{

    private File workingDirectory;

    private static final Logger LOG = LoggerFactory.getLogger(FileStorageService.class);

    public FileStorageService()
    {
        super("646DAF98-9080-4E75-8BF1-D6D1D8BC30FA", "File Storage Service");
    }

    @Override
    public boolean isHealthy()
    {
        // We're just going to assume we're good for now. Once again, this isn't designed for production environments.
        try
        {
            return workingDirectory.getFreeSpace() > 100000L;
        } catch (Exception e)
        {
            LOG.error("The File Storage system is unable to query for disk space.");
            LOG.debug(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public StorageResponse store(StorageRequest request) throws IOException
    {

        File storingFile = new File(workingDirectory, request.getFileName());

        FileOutputStream fileOutputStream = new FileOutputStream(storingFile);
        IOUtils.copyLarge(request.getInputStream(), fileOutputStream);

        IOUtils.closeQuietly(request.getInputStream());
        IOUtils.closeQuietly(fileOutputStream);

        HashCode hc = Files.hash(storingFile, Hashing.md5());

        StorageResponse response = new StorageResponse();

        // Awwww yesh, Java URLs.
        response.setUrl(storingFile.toURI().toURL().toString());
        response.setContentHash(hc.toString());

        return response;
    }

    @Override
    public void initialize()
    {
        super.initialize();

        if (workingDirectory == null)
        {
            // We're going to throw everything into this directory.
            workingDirectory = Files.createTempDir();
        }

    }

    @Override
    public InputStream retrieve(IFile file) throws IOException
    {
        File internal = new File(URI.create(file.getUrl()));
        if (internal.exists() && internal.canRead())
        {
            return new FileInputStream(internal);
        } else
        {
            File retrievingFile = new File(workingDirectory, file.getFileName());
            if (retrievingFile.exists() && retrievingFile.canRead())
            {
                return new FileInputStream(retrievingFile);
            }
        }
        throw new FileNotFoundException("Could not find file specified from URL or filename");
    }

    @Override
    public void delete(IFile file) throws IOException
    {
        File internal = new File(URI.create(file.getUrl()));
        if (internal.exists() && internal.canWrite())
        {

            internal.delete();

        } else
        {
            File delete = new File(workingDirectory, file.getFileName());

            if (delete.exists())
            {
                delete.delete();
            }

        }
        // At this point we assume there's no file there. This might have been accidentally called multiple times, so
        // we're good since that file does not exist.
    }

    @Override
    public void start() throws Exception
    {

    }

    @Override
    public void stop() throws Exception
    {
        // Get rid of our working directory.
        if (workingDirectory != null)
        {
            FileUtils.deleteDirectory(workingDirectory);
            if (this.workingDirectory.exists())
            {
                this.workingDirectory.delete();
            }
        }
    }

}
