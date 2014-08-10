/*
 * SMART COSMOS SDK
 * (C) Copyright 2013-2014, Smartrac Technology Fletcher, Inc.
 * 267 Cane Creek Rd, Fletcher, NC, 28732, USA
 * All Rights Reserved.
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

package net.smartcosmos.objects.model.context;

import net.smartcosmos.model.base.IAccountDomainResource;
import net.smartcosmos.model.base.IReferentialObject;
import net.smartcosmos.model.base.ITypedObject;

/**
 * Captures an explicit interaction between two existing {@link IObject}
 * instances that already exist in the system.
 * <p/>
 * The {@link #getObject()} is generally treated as the recipient of the interaction, while
 * the {@link net.smartcosmos.model.base.IReferentialObject} is considered the initiator. As a
 * concrete example, the person 'Jason' would be identified as the {@link net.smartcosmos.model.base.IReferentialObject}
 * while the {@link #getObject()} would be the vehicle 'Jeep' with the {@link #getType()} of
 * <b>Drives</b> to successfully capture the simple interaction that "Jason drives the Jeep."
 * <p/>
 * Additional metadata, including {@link IFile},
 * {@link net.smartcosmos.objects.model.geo.IGeospatialEntry}, {@link net.smartcosmos.model.context.IMetadata},
 * and {@link ITagAssignment} are typically used to capture
 * additional context about the interaction. Returning to the driving example, the
 * files might captures photos taken of Jason behind the driver's wheel, a geospatial record
 * to capture the route that Jason drove, metadata to capture the mileage of the vehicle at
 * the start of the trip and at the end of the trip, and tags to represent landmarks along
 * the path that the vehicle drove.
 * <p/>
 * Interactions capture a recurring pattern of interactivity between two objects. In other
 * words, interactions are used to capture 0, 1, or many relationships between objects.
 * <p/>
 * If you are looking for a binary or true-false existence check, the recommended construct is
 * to use an {@link IRelationship} instead of an interaction.
 */
public interface IObjectInteraction extends IAccountDomainResource<IObjectInteraction>, IReferentialObject, ITypedObject
{
    IObject getObject();

    void setObject(IObject object);

    /**
     * Actual time of the interaction as defined by the developer.
     *
     * @return timestamp
     */
    long getRecordedTimestamp();

    void setRecordedTimestamp(long timestamp);

    /**
     * Time the platform recorded the interaction. The delta between the
     * {@link #getRecordedTimestamp()} and this timestamp could be due to
     * network latency, cached storage due to lack of network access, etc.
     *
     * @return System-assigned timestamp when the platform received the interaction data
     */
    long getReceivedTimestamp();

    void setReceivedTimestamp(long timestamp);

    /**
     * System-computed method driven by the existence (or non-existence) of an
     * {@link IObjectInteractionSession}.
     *
     * @return true, if the interaction is a single peer within a set of arbitrarily related interactions
     */
    boolean isSessionMember();

    IObjectInteractionSession getObjectInteractionSession();

    void setObjectInteractionSession(IObjectInteractionSession session);
}
