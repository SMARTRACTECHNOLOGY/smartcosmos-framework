package net.smartcosmos.platform.base;

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

import com.google.common.base.Preconditions;
import io.dropwizard.lifecycle.Managed;
import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.platform.api.visitor.IVisitor;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public abstract class AbstractVisitor<T> extends AbstractService implements IVisitor<T>, Managed
{
    protected final Integer priority;

    /**
     * Only those EntityReferenceType values in the list below can be bound as a visitable entity.
     */
    protected static final List<EntityReferenceType> BINDABLE_ENTITIES = Arrays.asList(
            EntityReferenceType.Device,
            EntityReferenceType.Event,
            EntityReferenceType.File,
            EntityReferenceType.Georectification,
            EntityReferenceType.Metadata,
            EntityReferenceType.Relationship,
            EntityReferenceType.Object,
            EntityReferenceType.ObjectAddress,
            EntityReferenceType.ObjectInteraction,
            EntityReferenceType.ObjectInteractionSession,
            EntityReferenceType.Tag,
            EntityReferenceType.Timeline);

    private final EntityReferenceType entityReferenceType;

    protected AbstractVisitor(String serviceId, String name, EntityReferenceType entityReferenceType)
    {
        this(serviceId, name, entityReferenceType, DEFAULT_PRIORITY);
    }

    protected AbstractVisitor(String serviceId, String name, EntityReferenceType entityReferenceType, int priority)
    {
        super(serviceId, name);

        Preconditions.checkArgument((priority > 0 && priority <= 100),
                "priority must fall within the range of 0 < priority <= 100");

        //
        // This is here so that visitors of a particular entity type will get loaded into the
        // per-entity-type SortedSet in the visitor registry (see createEntityVisitors() in
        // net.smartcosmos.objects.app.ServiceFactory) even if they have the same priority,
        // i.e., that priority numbers in the visitors registry are (close enough to) unique.
        //
        Long leastSignificantBits = UUID.fromString(serviceId).getLeastSignificantBits();
        Integer uniquePriority = Math.abs(leastSignificantBits.intValue()) % 4096;
        this.priority = (priority * 4096) + uniquePriority;

        Preconditions.checkNotNull(entityReferenceType, "entityReferenceType must not be null");
        Preconditions.checkArgument(BINDABLE_ENTITIES.contains(entityReferenceType),
                "Specified entityReferenceType is not bindable as a visitor");
        this.entityReferenceType = entityReferenceType;
    }

    @Override
    public int getPriority()
    {
        return priority;
    }

    @Override
    public EntityReferenceType getEntityReferenceType()
    {
        return entityReferenceType;
    }


    @Override
    public void start() throws Exception
    {

    }

    @Override
    public void stop() throws Exception
    {

    }

    @Override
    public int compareTo(IVisitor other)
    {
        return priority.compareTo(other.getPriority());
    }
}
