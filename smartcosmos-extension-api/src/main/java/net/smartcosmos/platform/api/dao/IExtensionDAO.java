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

import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.model.extension.IExternalExtension;

import java.util.Collection;

public interface IExtensionDAO extends IBaseDAO<IExternalExtension>, INamedObjectSearchDAO<IExternalExtension>
{
    IExternalExtension findPublished(String urn);

    IExternalExtension lookupByClientId(String clientId);

    IExternalExtension findByClientId(String clientId, IAccount account);

    IExternalExtension lookupExtension(String clientId, String clientSecret);

    IExternalExtension lookupNotificationEndpointExtension(String referenceUrn);

    IExternalExtension findByClientIdClientSecret(String clientId, String clientSecret);

    Collection<IExternalExtension> listActive();
}
