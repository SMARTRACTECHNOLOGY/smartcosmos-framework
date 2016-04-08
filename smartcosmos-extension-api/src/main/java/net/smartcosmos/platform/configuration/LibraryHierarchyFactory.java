package net.smartcosmos.platform.configuration;

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

import net.smartcosmos.objects.resource.secure.libraries.EmptyLibraryHierarchy;
import net.smartcosmos.objects.resource.secure.libraries.ILibraryHierarchy;
import net.smartcosmos.objects.resource.secure.libraries.LibraryHierarchy;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by tcross on 05.02.2015.
 * <p>
 * Source of the singleton library hierarchy. If there is no library hierarchy specified in the configuration,
 * or if there is and it's not completely initialized, it returns an EmptyLibraryHierarchy instance, which
 * basically answers no to everything involving library hierarchies.
 * <p>
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
     * @param libraryHierarchyMap: a LinkedHashMap<String, Boolean> of names of LibraryElementTypes from objects.yml, from lowest to highest,
     *                           paired with flags indicating whether LibraryElements of this type can have Relationships with entities
     *                           other than LibraryLinks to other LibraryElements from the LibraryElementType hierarchy
     *                              <p>
     *                              See "LIBRARY HIERARCHY" section of objects.yml for an example
     *                              This map must be a linked hash map to preserve ordering specified in configuration.
     */
    public static void setLibraryHierarchy(LinkedHashMap<String, Boolean> libraryHierarchyMap)
    {
        if (!libraryHierarchyInitialized && libraryHierarchyMap != null)
        {
            ((LibraryHierarchy) libraryHierarchy).setLibraryHierarchyMap(libraryHierarchyMap);
            checkCompleteness(libraryHierarchyMap);
            libraryHierarchyInitialized = true;
        }

    }

    /**
     * In the libraryHierarchyMap, every key must be non-empty, and every value must be a Boolean.
     */
    private static void checkCompleteness(Map<String, Boolean> libraryHierarchyMap)
    {
        int hierarchyMapLength = libraryHierarchyMap.size();
        // does nothing if the map has not yet been initialized
        if (hierarchyMapLength > 0)
        {
            // every element must have a legitimate boolean value
            for (Map.Entry<String, Boolean> entry:libraryHierarchyMap.entrySet())
            {
                if (entry.getKey() == null || entry.getKey().isEmpty())
                {
                    throw new IllegalStateException("SMART COSMOS empty value in LibraryHierarchy. Please correct your objects.yml configuration");
                }
                if (entry.getValue() == null)
                {
                    throw new IllegalStateException("SMART COSMOS empty boolean in LibraryHierarchy element " + entry.getKey() +
                        ". Please correct your objects.yml configuration");
                }
            }
        }

    }
}

