package net.smartcosmos.model.base;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Core SDK
 * ===============================================================================
 * Copyright (C) 2013 - 2015 SMARTRAC Technology Fletcher, Inc.
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

/**
 * Represents a specific type of platform entity in a generic operation. Instead of defining many different
 * entity specific operations, a singular operation is typically defined by the platform where one of the
 * mandatory parameters is an {@link EntityReferenceType} coupled with a <b>referenceUrn</b>. This reduces
 * the number of API methods to learn, and provides an easy extension mechanism when new types of entities
 * are defined in future releases.
 * <p/>
 * As a concrete example, consider a {@link net.smartcosmos.objects.model.context.IRelationship} used to
 * define some arbitrary type of connection between any two existing entities defined in the system.
 * As new entity types are added to the system, no additional work is required to support those entities
 * so that they too can participate in relationships.
 */
public enum EntityReferenceType
{
    Account,

    BatchTransmission,

    Extension,

    NotificationEndpoint,

    Object,
    Relationship,
    ObjectInteraction,
    ObjectInteractionSession,

    Device,
    ObjectAddress,
    File,
    Metadata,
    Tag,
    Timeline,
    Georectification,

    LibraryElement

//    Library,
//    Shelf,
//    Book,
//    PageEntry
}
