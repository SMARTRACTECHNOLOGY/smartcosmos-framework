package net.smartcosmos.objects.pojo.context;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Core
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

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonView;

import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.model.context.IMetadata;
import net.smartcosmos.objects.model.context.IObject;
import net.smartcosmos.objects.model.context.IObjectAddress;
import net.smartcosmos.objects.model.context.IRelationship;
import net.smartcosmos.util.json.JsonGenerationView;

/**
 * Bulk Object is a composite object that assists with handling numerous types of objects. Certain endpoints will
 * support this in the future. For instance, if you wanted to get an Object back and all of its metadata, the Object
 * would be contained in the list of Objects, and the metadata in the list of Metadata.
 * 
 * @author voor
 *
 */
public class BulkObjectImpl
{
    @JsonView(JsonGenerationView.Full.class)
    private final IAccount account;

    @JsonView(JsonGenerationView.Minimum.class)
    private final Collection<IObject> objects;

    @JsonView(JsonGenerationView.Minimum.class)
    private final Collection<IObjectAddress> objectAddresses;

    @JsonView(JsonGenerationView.Minimum.class)
    private final Collection<IRelationship> relationships;

    @JsonView(JsonGenerationView.Minimum.class)
    private final Collection<IMetadata> metadata;

    public BulkObjectImpl(final IAccount account, final Collection<IObject> objects,
            final Collection<IObjectAddress> objectAddresses,
            final Collection<IRelationship> relationships, final Collection<IMetadata> metadata)
    {
        super();
        this.account = account;
        this.objects = objects;
        this.objectAddresses = objectAddresses;
        this.relationships = relationships;
        this.metadata = metadata;
    }

    public IAccount getAccount()
    {
        return account;
    }

    public Collection<IMetadata> getMetadata()
    {
        return metadata;
    }

    public Collection<IObjectAddress> getObjectAddresses()
    {
        return objectAddresses;
    }

    public Collection<IObject> getObjects()
    {
        return objects;
    }

    public Collection<IRelationship> getRelationships()
    {
        return relationships;
    }

}
