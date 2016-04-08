package net.smartcosmos.client.objects.library;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Client
 * ===============================================================================
 * Copyright (C) 2013 - 2015 SMARTRAC Technology Fletcher, Inc.
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
import net.smartcosmos.client.impl.IUpdateableBaseClient;
import net.smartcosmos.client.impl.endpoint.LibraryEndpoints;
import net.smartcosmos.objects.model.context.ILibraryElement;
import net.smartcosmos.util.json.ViewType;

import java.util.Collection;

/**
 * Defines or queries for {@link net.smartcosmos.objects.model.context.ILibraryElement} instances.
 */
public interface ILibraryElementClient extends IUpdateableBaseClient<ILibraryElement>
{

    /**
     * Complex query for a collection of matching libraryElements.
     *
     * @param builder Builder that defines the query to perform
     * @return Non-null collection of matching entities; collection may have a size of 0 to indicate no matches found
     * @throws ServiceException
     */
    Collection<ILibraryElement> query(LibraryEndpoints.Builder builder) throws ServiceException;

    /**
     * Simple query for parent libraryElement of a libraryElement.
     *
     * @param libraryElementUrn
     * @return null if libraryElement has no parent, otherwise the Urn of the parent libraryElement
     * @throws ServiceException
     */
    ILibraryElement getParent(String libraryElementUrn) throws ServiceException;

    /**
     * Simple query for parent libraryElement of a libraryElement.
     *
     * @param libraryElementUrn
     * @param viewType optional viewType
     * @return null if libraryElement has no parent, otherwise the Urn of the parent libraryElement
     * @throws ServiceException
     */
    ILibraryElement getParent(String libraryElementUrn, ViewType viewType) throws ServiceException;

    /**
     * Simple query for children libraryElements of a libraryElement.
     *
     * @param libraryElementUrn
     * @return empty Collection&lt;ILibraryElement&gt; if libraryElement has no children, otherwise
     * a Collection&lt;LibraryElement&gt; of the child libraryElements
     * @throws ServiceException
     */
    Collection<ILibraryElement> getChildren(String libraryElementUrn) throws ServiceException;

    /**
     * Simple query for children libraryElements of a libraryElement.
     *
     * @param libraryElementUrn
     * @param viewType optional viewType
     * @return empty Collection&lt;ILibraryElement&gt; if libraryElement has no children, otherwise
     * a Collection&lt;LibraryElement&gt; of the child libraryElements
     * @throws ServiceException
     */
    Collection<ILibraryElement> getChildren(String libraryElementUrn, ViewType viewType) throws ServiceException;

}
