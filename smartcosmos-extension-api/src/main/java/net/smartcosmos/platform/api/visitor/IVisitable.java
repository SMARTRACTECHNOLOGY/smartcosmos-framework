package net.smartcosmos.platform.api.visitor;

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

import net.smartcosmos.model.event.EventType;

/**
 * Generic visitor design pattern, where any class that wishes to accept visitors of a specific type
 * should implement this interface.
 */
public interface IVisitable<T>
{
    /**
     * Event types help visitors understand the context of their visit, e.g. is it a creation, an update, or a delete
     * event that triggered their visit.
     *
     * @return Event type
     * @see VisitableEvent
     * @see VisitableDevice
     * @see VisitableFile
     * @see VisitableGeospatialEntry
     * @see VisitableMetadata
     * @see VisitableObject
     * @see VisitableObjectAddress
     * @see VisitableObjectInteraction
     * @see VisitableObjectInteractionSession
     * @see VisitableRelationship
     * @see VisitableTagAssignment
     * @see VisitableTimelineEntry
     */
    EventType getEventType();

    /**
     * Accepts a strongly typed visitor.
     *
     * @param visitor visitor
     */
    void accept(IVisitor<T> visitor);
}
