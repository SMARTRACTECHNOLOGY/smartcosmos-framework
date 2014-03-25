/*
 * SnapBundle SDK
 * (C) Copyright 2013-2014 Tag Dynamics, LLC (http://tagdynamics.com/)
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

package com.snapbundle.client.extension;

import com.snapbundle.client.impl.IDeleteableBaseClient;
import com.snapbundle.client.impl.IUpdateableBaseClient;
import com.snapbundle.client.api.ServiceException;
import com.snapbundle.model.extension.IExtension;
import com.snapbundle.util.json.ViewType;

import java.util.Collection;

public interface IExtensionClient extends IUpdateableBaseClient<IExtension>, IDeleteableBaseClient<IExtension>
{
    Collection<IExtension> findByNameLike(String nameLike) throws ServiceException;

    Collection<IExtension> findByNameLike(String nameLike, ViewType viewType) throws ServiceException;

    Collection<IExtension> catalog() throws ServiceException;

    IExtension getPublishedExtension(String urn) throws ServiceException;
}
