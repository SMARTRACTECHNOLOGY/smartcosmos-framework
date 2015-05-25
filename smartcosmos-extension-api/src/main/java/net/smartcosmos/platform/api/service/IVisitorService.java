package net.smartcosmos.platform.api.service;

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
import net.smartcosmos.platform.api.visitor.IVisitor;

import java.util.Collection;

/**
 * Global Visitor design pattern hook for implementing server-side triggers.
 */
public interface IVisitorService extends IService, Managed
{
    EntityReferenceType getEntityReferenceType();

    /**
     * Get the list of visitors for the specific type of class.
     *
     * @param typeOf Visitor class type
     * @param <T>    Type of class that the visitor will visit, e.g. <code>IEvent</code>
     * @return
     */
    <T> Collection<IVisitor<T>> getVisitors(Class<T> typeOf);
}
