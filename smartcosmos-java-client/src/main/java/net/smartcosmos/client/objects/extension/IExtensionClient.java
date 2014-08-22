/*
 * SMART COSMOS SDK
 * (C) Copyright 2013-2014, Smartrac Technology Fletcher, Inc.
 * 267 Cane Creek Rd, Fletcher, NC, 28732, USA
 * All Rights Reserved.
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

package net.smartcosmos.client.objects.extension;

import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.IDeleteableBaseClient;
import net.smartcosmos.client.impl.IUpdateableBaseClient;
import net.smartcosmos.model.extension.IExtension;
import net.smartcosmos.util.json.ViewType;

import java.util.Collection;

public interface IExtensionClient extends IUpdateableBaseClient<IExtension>, IDeleteableBaseClient<IExtension>
{
    Collection<IExtension> findByNameLike(String nameLike) throws ServiceException;

    Collection<IExtension> findByNameLike(String nameLike, ViewType viewType) throws ServiceException;

    Collection<IExtension> catalog() throws ServiceException;

    IExtension getPublishedExtension(String urn) throws ServiceException;
}
