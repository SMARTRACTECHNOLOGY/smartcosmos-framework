package net.smartcosmos.platform.bundle.batch;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Batch Processing Framework
 * ===============================================================================
 * Copyright (C) 2014 SMARTRAC Technology Fletcher, Inc.
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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.util.Duration;
import net.smartcosmos.platform.api.ProtocolType;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class BatchFactory
{
    @JsonProperty
    private Boolean enabled = true;

    @JsonProperty
    private Boolean authenticationRequired = true;

    @JsonProperty
    @NotNull
    private String uploadBucketName;

    @JsonProperty
    @NotNull
    private String uploadService;

    @JsonProperty
    @NotNull
    private ProtocolType uploadProtocol = ProtocolType.HTTPS;

    @Valid
    @NotNull
    @JsonProperty
    private Duration signedUrlLife = Duration.minutes(30);

    public ProtocolType getUploadProtocol()
    {
        return uploadProtocol;
    }

    public Boolean isEnabled()
    {
        return enabled;
    }

    public Boolean isAuthenticationRequired()
    {
        return authenticationRequired;
    }

    public String getUploadBucketName()
    {
        return uploadBucketName;
    }

    public String getUploadService()
    {
        return uploadService;
    }

    public long getSignedUrlLifeInMillis()
    {
        return signedUrlLife.toMilliseconds();
    }
}
