package net.smartcosmos.client.objects.object.address;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Client
 * ===============================================================================
 * Copyright (C) 2013 - 2014 SMARTRAC Technology Fletcher, Inc.
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

import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.IDeleteableBaseClient;
import net.smartcosmos.client.impl.IUpdateableBaseClient;
import net.smartcosmos.objects.model.context.IObjectAddress;
import net.smartcosmos.util.json.ViewType;

import java.util.Collection;

/**
 * Defines, deletes, or queries for {@link net.smartcosmos.objects.model.context.IObjectAddress} instances.
 */
public interface IObjectAddressClient
        extends IUpdateableBaseClient<IObjectAddress>, IDeleteableBaseClient<IObjectAddress>
{
    /**
     * Find a specific {@link net.smartcosmos.objects.model.context.IObject#getObjectUrn()} address using a
     * case-sensitive exact match using a {@link net.smartcosmos.util.json.ViewType#Standard} view.
     *
     * @param objectUrn Exact, case-sensitive object URN to locate
     * @param urn       Exact, case-sensitive system-assigned URN of the address record to locate
     * @return Matching object
     */
    IObjectAddress findByUrn(String objectUrn, String urn) throws ServiceException;

    /**
     * Find a specific {@link net.smartcosmos.objects.model.context.IObject#getObjectUrn()} address using a
     * case-sensitive exact match using the specified field verbosity.
     *
     * @param objectUrn Exact, case-sensitive object URN to locate
     * @param urn       Exact, case-sensitive system-assigned URN of the address record to locate
     * @param viewType  Field verbosity
     * @return Matching object
     */
    IObjectAddress findByUrn(String objectUrn, String urn, ViewType viewType) throws ServiceException;

    /**
     * Locates the last <i>n</i> address records associated with the specified
     * {@link net.smartcosmos.objects.model.context.IObject#getObjectUrn()}. This method is useful regardless when an
     * object has a static address (e.g. a building) or when the object moves around a lot (e.g. a vehicle). To learn
     * the last known address, set the <code>count</code> value to 1.
     * <p/>
     * Returns a {@link net.smartcosmos.util.json.ViewType#Standard} view.
     *
     * @param objectUrn Exact, case-sensitive object URN to locate
     * @param count     How many last known address records to return, chronologically descending
     * @return Non-null collection of matching addresses; collection may have a size of 0 to indicate no matches found
     * @throws ServiceException
     */
    Collection<IObjectAddress> findLastN(String objectUrn, int count) throws ServiceException;

    /**
     * Locates the last <i>n</i> address records associated with the specified
     * {@link net.smartcosmos.objects.model.context.IObject#getObjectUrn()}. This method is useful regardless when an
     * object has a static address (e.g. a building) or when the object moves around a lot (e.g. a vehicle). To learn
     * the last known address, set the <code>count</code> value to 1.
     * <p/>
     * Returns the address records at the specified field verbosity.
     *
     * @param objectUrn Exact, case-sensitive object URN to locate
     * @param count     How many last known address records to return, chronologically descending
     * @return Non-null collection of matching addresses; collection may have a size of 0 to indicate no matches found
     * @throws ServiceException
     */
    Collection<IObjectAddress> findLastN(String objectUrn, int count, ViewType viewType) throws ServiceException;
}
