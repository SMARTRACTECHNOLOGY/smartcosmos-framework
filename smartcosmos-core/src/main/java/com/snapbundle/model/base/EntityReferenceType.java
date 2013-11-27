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

package com.snapbundle.model.base;

/**
 * Represents a specific type of platform entity in a generic operation, e.g. search. Instead of defining many different
 * entity specific search operations, a singular search operation is defined where one of the mandatory parameters is
 * an {@link EntityReferenceType}. This reduces the number of API methods to learn, and provides an easy extension
 * mechanism when new types of entities are defined in later versions.
 */
public enum EntityReferenceType
{
    Account("AccountEntity"),
    Device("DeviceEntity"),
    User("UserEntity"),

    Object("ObjectEntity"),
    ObjectAddress("ObjectAddressEntity"),
    ObjectInteraction("ObjectInteractionEntity"),
    ObjectInteractionSession("ObjectInteractionSessionEntity"),

    File("FileEntity"),
    Metadata("MetadataEntity"),
    Tag("TagEntity"),

    Event("EventEntity"),

    Extension("Extension"),
    ExtensionScreenShot("ExtensionScreenShot");

    private String entityName;

    EntityReferenceType(String entityName)
    {
        this.entityName = entityName;
    }

    public String getEntityName()
    {
        return entityName;
    }
}
