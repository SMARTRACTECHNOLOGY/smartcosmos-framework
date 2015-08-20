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

import io.dropwizard.lifecycle.Managed;
import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.platform.api.IService;

/**
 * Generic visitor design pattern.
 *
 * @param <T> Type of object to visit
 */
public interface IVisitor<T> extends IService, Managed, Comparable<IVisitor>
{
    int FIRST_PRIORITY = 1;

    int HIGH_PRIORITY = 25;

    int MEDIUM_PRIORITY = 50;

    int LOW_PRIORITY = 75;

    int LAST_PRIORITY = 100;

    int DEFAULT_PRIORITY = MEDIUM_PRIORITY;

    /**
     * Prioritizes this visitor in relationship to other registered visitors.
     *
     * @return a priority value between 1 and 100 where 1 is more important than 100
     */
    int getPriority();

    /**
     * Declares the entity reference type this visitor can work against.
     *
     * @return entity reference type
     */
    EntityReferenceType getEntityReferenceType();

    /**
     * Visit the object.
     *
     * @param instance instance to visit
     */
    void visit(T instance);
}
