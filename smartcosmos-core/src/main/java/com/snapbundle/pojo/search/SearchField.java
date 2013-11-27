/*
 * SnapBundle™ SDK
 * (C) Copyright 2013 Tag Dynamics, LLC (http://tagdynamics.com/)
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

package com.snapbundle.pojo.search;

public enum SearchField
{
    ObjectUrn("objectUrn"),
    DeviceUrn("urn"),

    Name("name"),
    Moniker("moniker"),
    Description("description"),

    EmailAddress("emailAddress"),

    LastModifiedDate("lastModifiedTimestamp"),
    RecordedTimestamp("recordedTimestamp"),
    ReceivedTimestamp("receivedTimestamp"),
    InteractionSessionTimestamp("startTimestamp"),

    DeviceIdentification("identification"),

    EventType("eventType"),

    ObjectType("objectType"),
    SessionType("sessionType"),
    DeviceType("deviceType"),

    InteractionData("data"),
    InteractionSession("objectInteractionSession"),

    HasInteractionSession("urn"),
    HasInteractionData("data"),
    HasGeoLocation("hasGeoLocation");

    private String jpaFieldName;

    SearchField(String jpaFieldName)
    {
        this.jpaFieldName = jpaFieldName;
    }

    public String getJpaFieldName()
    {
        return jpaFieldName;
    }
}
