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

package net.smartcosmos.platform.api.dao;

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

import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.objects.model.context.IObject;

import java.util.Collection;
import java.util.UUID;

public interface IObjectDAO extends IBaseDAO<IObject>, INamedObjectSearchDAO<IObject>
{
    IObject findByObjectUrn(String objectUrn, IAccount account);

    Collection<IObject> findByObjectUrnLike(String objectUrnLike, IAccount account);

    // Don't know if you've got a system urn or an object urn? This will return a system urn if
    // the object has already been persisted, and a null otherwise
    UUID getSystemUrnFromObjectUrn(String referenceUrn, IAccount account);
    String getSystemUrnFromObjectUrnAsString(String referenceUrn, IAccount account);
    String getSystemUrnFromObjectUrnAsString(String referenceUrn, EntityReferenceType ert, IAccount account);
}

