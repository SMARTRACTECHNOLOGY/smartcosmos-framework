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

package com.snapbundle.client.interaction;

import com.snapbundle.client.api.ServiceException;
import com.snapbundle.client.impl.ICreateableBaseClient;
import com.snapbundle.model.context.IObjectInteraction;
import com.snapbundle.util.json.ViewType;

import java.util.Collection;

/**
 * Defines or queries for {@link com.snapbundle.model.context.IObjectInteraction} instances.
 */
public interface IInteractionClient extends ICreateableBaseClient<IObjectInteraction>
{
    /**
     * Lists all known object interactions under the authenticated account using a
     * {@link com.snapbundle.util.json.ViewType#Standard} view
     * <p/>
     * <b>NOTE:</b> Use caution on this method as it may return a large amount of data!
     *
     * @return Non-null (but possibly empty) collection of object interaction definitions
     * @throws ServiceException
     */
    Collection<IObjectInteraction> listAll() throws ServiceException;

    /**
     * Lists all known object interactions under the authenticated account using the specified field verbosity.
     * <p/>
     * <b>NOTE:</b> Use caution on this method as it may return a large amount of data!
     *
     * @param viewType Field verbosity
     * @return Non-null (but possibly empty) collection of object interaction definitions
     * @throws ServiceException
     */
    Collection<IObjectInteraction> listAll(ViewType viewType) throws ServiceException;

    /**
     * Lists all known object interactions under the authenticated account with an
     * {@link com.snapbundle.model.context.IObject#getObjectUrn()} that starts with a case-sensitive
     * match to the specified objectUrnLike parameter using a
     * {@link com.snapbundle.util.json.ViewType#Standard} view.
     *
     * @param objectUrnLike Case-sensitive object URN like pattern (do not append any special characters like % or * to the end)
     * @return Non-null (but possibly empty) collection of object interaction definitions
     * @throws ServiceException
     */
    Collection<IObjectInteraction> findByObjectUrnLike(String objectUrnLike) throws ServiceException;

    /**
     * Lists all known object interactions under the authenticated account with an
     * {@link com.snapbundle.model.context.IObject#getObjectUrn()} that starts with a case-sensitive
     * match to the specified objectUrnLike parameter using the specified field verbosity.
     *
     * @param objectUrnLike Case-sensitive object URN like pattern (do not append any special characters like % or * to the end)
     * @param viewType      Field verbosity
     * @return Non-null (but possibly empty) collection of object interaction definitions
     * @throws ServiceException
     */
    Collection<IObjectInteraction> findByObjectUrnLike(String objectUrnLike, ViewType viewType) throws ServiceException;
}
