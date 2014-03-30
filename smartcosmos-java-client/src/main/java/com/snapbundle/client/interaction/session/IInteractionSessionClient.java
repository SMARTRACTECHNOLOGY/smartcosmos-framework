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

package com.snapbundle.client.interaction.session;

import com.snapbundle.client.connectivity.ServiceException;
import com.snapbundle.client.impl.IUpdateableBaseClient;
import com.snapbundle.model.context.IObjectInteractionSession;
import com.snapbundle.util.json.ViewType;

import java.util.Collection;

/**
 * Defines or queries for {@link com.snapbundle.model.context.IObjectInteractionSession} instances.
 */
public interface IInteractionSessionClient extends IUpdateableBaseClient<IObjectInteractionSession>
{
    /**
     * Queries for sessions with names that start with the case-sensitive pattern, returning all matches using a
     * {@link com.snapbundle.util.json.ViewType#Standard} view.
     *
     * @param nameLike Case-sensitive name like pattern (do not append any special characters like % or * to the end)
     * @return Non-null (but possibly empty) collection of sessions that have a name that matches the specified pattern
     * @throws ServiceException
     */
    Collection<IObjectInteractionSession> findByNameLike(String nameLike) throws ServiceException;

    /**
     * Queries for sessions with names that start with the case-sensitive pattern, returning all matches using the
     * specified field verbosity.
     *
     * @param nameLike Case-sensitive name like pattern (do not append any special characters like % or * to the end)
     * @param viewType Field verbosity
     * @return Non-null (but possibly empty) collection of sessions that have a name that matches the specified pattern
     * @throws ServiceException
     */
    Collection<IObjectInteractionSession> findByNameLike(String nameLike, ViewType viewType) throws ServiceException;
}
