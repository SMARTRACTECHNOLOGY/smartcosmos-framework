package net.smartcosmos.platform.configuration;

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

import com.fasterxml.jackson.annotation.JsonProperty;

public class EndpointsFactory
{
    @JsonProperty
    private Boolean metadataEncodingEndpoints = true;

    @JsonProperty
    private Boolean eventsEndpoint = true;

    @JsonProperty
    private Boolean objectAddressEndpoint = true;

    @JsonProperty
    private Boolean metadataEndpoints = true;

    @JsonProperty
    private Boolean timelineEndpoints = true;

    @JsonProperty
    private Boolean relationshipEndpoints = true;

    @JsonProperty
    private Boolean interactionEndpoints = true;

    @JsonProperty
    private Boolean objectEndpoints = true;

    @JsonProperty
    private Boolean transactionEndpoints = true;

    @JsonProperty
    private Boolean libraryEndpoints = true;

    @JsonProperty
    private Boolean registrationEndpoints = true;

    @JsonProperty
    private Boolean accountEndpoints = true;

    @JsonProperty
    private Boolean fileEndpoints = true;

    @JsonProperty
    private Boolean notificationEndpoints = true;

    @JsonProperty
    private Boolean sessionEndpoints = true;

    @JsonProperty
    private Boolean userEndpoints = true;

    @JsonProperty
    private Boolean batchEndpoints = true;

    @JsonProperty
    private Boolean realmEndpoints = true;

    public Boolean getTransactionEndpoints()
    {
        return transactionEndpoints;
    }

    public Boolean getObjectEndpoints()
    {
        return objectEndpoints;
    }

    public Boolean getInteractionEndpoints()
    {
        return interactionEndpoints;
    }

    public Boolean getRelationshipEndpoints()
    {
        return relationshipEndpoints;
    }

    public Boolean getTimelineEndpoints()
    {
        return timelineEndpoints;
    }

    public Boolean getMetadataEndpoints()
    {
        return metadataEndpoints;
    }

    public Boolean getObjectAddressEndpoint()
    {
        return objectAddressEndpoint;
    }

    public Boolean getEventsEndpoint()
    {
        return eventsEndpoint;
    }

    public Boolean getMetadataEncodingEndpoints()
    {
        return metadataEncodingEndpoints;
    }

    public Boolean getLibraryEndpoints()
    {
        return libraryEndpoints;
    }

    public Boolean getRegistrationEndpoints()
    {
        return registrationEndpoints;
    }

    public Boolean getAccountEndpoints()
    {
        return accountEndpoints;
    }

    public Boolean getFileEndpoints()
    {
        return fileEndpoints;
    }

    public Boolean getNotificationEndpoints()
    {
        return notificationEndpoints;
    }

    public Boolean getSessionsEndpoints()
    {
        return sessionEndpoints;
    }

    public Boolean getUserEndpoints()
    {
        return userEndpoints;
    }

    public Boolean getBatchEndpoints()
    {
        return batchEndpoints;
    }

    public boolean getRealmEndpoints()
    {
        return realmEndpoints;
    }

}
