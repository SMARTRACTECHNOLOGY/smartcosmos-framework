/*
 * Copyright (C) 2013 - 2015, Smartrac Technology Fletcher, Inc.
 * 267 Cane Creek Rd, Fletcher, NC, 28732, USA
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Smartrac Technology Fletcher, Inc. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Smartrac Technology Fletcher, Inc.
 */
package net.smartcosmos.platform.configuration;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Client
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

import net.smartcosmos.objects.resource.secure.libraries.EmptyLibraryHierarchy;
import net.smartcosmos.objects.resource.secure.libraries.ILibraryHierarchy;
import net.smartcosmos.objects.resource.secure.libraries.LibraryHierarchy;

import java.util.List;

/**
 * Created by tcross on 05.02.2015.
 * <p/>
 * Source of the singleton library hierarchy. If there is no library hierarchy specified in the configuration,
 * or if there is and it's not completely initialized, it returns an EmptyLibraryHierarchy instance, which
 * basically answers no to everything involving library hierarchies.
 * <p/>
 * The setLibraryHierarchyList() and setLibraryLinkFlagsList() methods have to be public so they can be accessed
 * by the DropWizard application initialization, but they are noops after the initial call.
 */
public final class LibraryHierarchyFactory
{
    private LibraryHierarchyFactory()
    {
    }

    private static boolean libraryHierarchyInitialized = false;

    private static ILibraryHierarchy libraryHierarchy = LibraryHierarchy.getInstance();

    private static ILibraryHierarchy emptyLibraryHierarchy = EmptyLibraryHierarchy.getInstance();

    private static int hierarchyListLength = 0;

    private static int linkFlagsListLength = 0;

    /**
     * @return a singleton EmptyLibraryHierarchy instance if there is no library hierarchy specified in the
     * configuration, or if there is and it's not fully initialized. If there is a fully initialized
     * LibraryHierarchy instance available, it returns that.
     */
    public static ILibraryHierarchy getLibraryHierarchy()
    {
        if (libraryHierarchyInitialized)
        {
            return libraryHierarchy;
        }
        return emptyLibraryHierarchy;
    }

    /**
     * @param libraryHierarchyList: a List<String> names of LibraryElementTypes from objects.yml, from lowest to highest
     *                              <p/>
     *                              See "LIBRARY HIERARCHY" section of objects.yml for an example
     */
    public static void setLibraryHierarchyList(List<String> libraryHierarchyList)
    {
        if (!libraryHierarchyInitialized && libraryHierarchyList != null)
        {
            ((LibraryHierarchy) libraryHierarchy).setLibraryHierarchyList(libraryHierarchyList);
            hierarchyListLength = libraryHierarchyList.size();
            checkCompleteness();
        }

    }

    /**
     * @param libraryLinkFlags a List\<Boolean\> of Booleans from objects.yml
     *                         <p/>
     *                         Each flag indicates whether elements of LibraryElementType with the same index from
     *                         the LibraryHierarchyList can have Objects directly attached directly to them. In the
     *                         default/example version objects can only be attached to library elements of
     *                         libraryElementType PageEntry, i.e., the lowest level of the hierarchy.
     */
    public static void setLibraryLinkFlagsList(List<Boolean> libraryLinkFlags)
    {
        if (!libraryHierarchyInitialized && libraryLinkFlags != null)
        {
            ((LibraryHierarchy) libraryHierarchy).setLibraryLinkFlagsList(libraryLinkFlags);
            linkFlagsListLength = libraryLinkFlags.size();
            checkCompleteness();
        }

    }

    /**
     * if the hierachyList and the linkFlagsList have the same non-zero length, this sets the flag to return
     * the non-empty LibraryHierarchy. As long as this flag is unset, this factory returns only the empty hierarchy.
     */
    private static void checkCompleteness()
    {
        // does nothing if one or the other arrays has not yet been initialized
        if (hierarchyListLength > 0 && linkFlagsListLength > 0)
        {
            // if they're both initialized, and both the same size, we're cool
            if (hierarchyListLength == linkFlagsListLength)
            {
                libraryHierarchyInitialized = true;
            } else
            {
                throw new IllegalStateException("SMART COSMOS length mismatch between LibraryHierarchy and " +
                        "LibraryLinkFlags - please correct your objects.yml configuration");
            }
        }

    }
}

