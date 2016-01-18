/*
 * Copyright (C> 2013 - 2015, Smartrac Technology Fletcher, Inc.
 * 267 Cane Creek Rd, Fletcher, NC, 28732, USA
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Smartrac Technology Fletcher, Inc. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Smartrac Technology Fletcher, Inc.
 */

package net.smartcosmos.platform.jpa.base;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Objects JPA Mappings
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
import net.smartcosmos.model.base.ITypedObject;
import net.smartcosmos.util.json.JsonGenerationView;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class DomainResourceAccountTypedNamedObjectEntity<T extends INamedObject<T>>
    extends DomainResourceAccountNamedObjectEntity<T>implements ITypedObject
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @JsonView(JsonGenerationView.Minimum.class)
    @Column(length = TYPE_MAX_LENGTH, nullable = false)
    @Size(max = TYPE_MAX_LENGTH)
    @NotNull
    private String type;

    @Override
    public String getType()
    {
        return type;
    }

    @Override
    public void setType(final String type)
    {
        this.type = type;
    }

    /**
     * Serves as a reminder that the supertype does not copy the type.
     *
     * @param type
     */
    protected abstract void copyTypedObject(String type);
}
