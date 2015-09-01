package net.smartcosmos.platform.jpa.base;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

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

import com.fasterxml.jackson.annotation.JsonView;

import net.smartcosmos.model.base.INamedObject;
import net.smartcosmos.util.json.JsonGenerationView;

@MappedSuperclass
public abstract class DomainResourceNamedObjectEntity<T extends INamedObject<T>>
        extends DomainResourceEntity<T>implements INamedObject<T>
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JsonView(JsonGenerationView.Published.class)
    @Column(length = 255, nullable = false)
    protected String name;

    @JsonView(JsonGenerationView.Standard.class)
    @Column(length = 1024)
    protected String description;

    @JsonView(JsonGenerationView.Standard.class)
    @Basic
    protected boolean activeFlag = true;

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void setName(final String name)
    {
        this.name = name;
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public void setDescription(final String description)
    {
        this.description = description;
    }

    @Override
    public boolean isActive()
    {
        return activeFlag;
    }

    @Override
    public void setActive(final boolean flag)
    {
        this.activeFlag = flag;
    }

    @Override
    public void copy(final T target)
    {
        super.copy(target);

        this.name = target.getName();
        this.description = target.getDescription();
        this.activeFlag = target.isActive();
    }
}
