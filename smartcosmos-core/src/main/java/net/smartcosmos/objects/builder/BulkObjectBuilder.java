package net.smartcosmos.objects.builder;

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

import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.model.context.IMetadata;
import net.smartcosmos.objects.model.context.IObject;
import net.smartcosmos.objects.model.context.IObjectAddress;
import net.smartcosmos.objects.model.context.IRelationship;
import net.smartcosmos.objects.pojo.context.BulkObjectImpl;

public class BulkObjectBuilder
{

    private final IAccount account;

    private Collection<IObject> objects;

    private Collection<IObjectAddress> objectAddresses;

    private Collection<IRelationship> relationships;

    private Collection<IMetadata> metadata;

    public BulkObjectBuilder(final IAccount account)
    {
        super();
        this.account = account;
    }

    public Collection<IObject> getObjects()
    {
        return objects;
    }

    public BulkObjectBuilder setObjects(final Collection<IObject> objects)
    {
        this.objects = objects;
        return this;
    }

    public Collection<IObjectAddress> getObjectAddresses()
    {
        return objectAddresses;
    }

    public BulkObjectBuilder setObjectAddresses(final Collection<IObjectAddress> objectAddresses)
    {
        this.objectAddresses = objectAddresses;
        return this;
    }

    public Collection<IRelationship> getRelationships()
    {
        return relationships;
    }

    public BulkObjectBuilder setRelationships(final Collection<IRelationship> relationships)
    {
        this.relationships = relationships;
        return this;
    }

    public Collection<IMetadata> getMetadata()
    {
        return metadata;
    }

    public BulkObjectBuilder setMetadata(final Collection<IMetadata> metadata)
    {
        this.metadata = metadata;
        return this;
    }

    public IAccount getAccount()
    {
        return account;
    }

    public BulkObjectImpl build()
    {
        return new BulkObjectImpl(account, objects, objectAddresses, relationships, metadata);
    }
}
