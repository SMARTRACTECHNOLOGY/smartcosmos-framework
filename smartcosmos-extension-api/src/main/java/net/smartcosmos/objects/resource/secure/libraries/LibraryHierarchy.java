package net.smartcosmos.objects.resource.secure.libraries;

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

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by tcross on 26.01.2015.
 * <p>
 * A singleton with two public methods, one of which returns the appropriate
 * parent library entity type for a particular library entity type, and the other
 * a boolean to indicate whether a library type accepts links to non-library objects.
 * <p>
 * Instantiated by LibraryHierarchyFactory, which is responsible for ensuring its
 * completeness and consistency (in the sense of the list of library element types
 * and the list of library link flags are the same size) of the instance.
 */
public final class LibraryHierarchy implements ILibraryHierarchy
{

    // singleton, but no big deal if there are two floating around
    private static LibraryHierarchy libraryHierarchy = null;

    private LibraryHierarchy()
    {
    }

    private LinkedHashMap<String, Boolean> hierarchyMap = null;

    public static synchronized LibraryHierarchy getInstance()
    {
        if (libraryHierarchy == null)
        {
            libraryHierarchy = new LibraryHierarchy();
        }
        return libraryHierarchy;
    }

    public void setLibraryHierarchyMap(LinkedHashMap<String, Boolean> hierarchyMap)
    {
        this.hierarchyMap = hierarchyMap;
    }

    /**
     * @param libraryElementType libraryElementType string
     * @return boolean indicating whether this is a legitimate libraryElementType
     * <p>
     * Returns the parent library element type for the input library element type.
     * <p>
     * Returns null if the input library element type is found, but has no parent, ie.,
     * is the element at the top of the hierarchy.
     * <p>
     * If the input type is not found at all, returns an error string "ErrNoSuchType"
     */
    public boolean isLibraryElementType(String libraryElementType)
    {
        for (String next : hierarchyMap.keySet())
        {
            if (next.equals(libraryElementType))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * @param libraryElementType libraryElementType string
     * @return String this libraryElementType's parent libraryElementType
     * <p>
     * Returns the parent library element type for the input library element type.
     * <p>
     * Returns null if the input library element type is found, but has no parent, ie.,
     * is the element at the top of the hierarchy.
     * <p>
     * If the input type is not found at all, returns an error string "ErrNoSuchType"
     */
    public String getParentTypeFor(String libraryElementType)
    {
        Iterator<String> iterator = hierarchyMap.keySet().iterator();
        while (iterator.hasNext())
        {
            String next = iterator.next();
            if (next.equals(libraryElementType))
            {
                if (iterator.hasNext())
                {
                    return iterator.next();
                } else
                {
                    return null;
                }
            }
        }
        throw new IllegalStateException("LibraryElementType " + libraryElementType + " is not in the list of " +
                "libraryElementTypes specified in the objects.yml configuration.");
    }

    /**
     * @param libraryEntityType
     * @return boolean
     * <p>
     * Returns a boolean flag indicating whether library elements of the input library element type
     * can have library links added to them. default configuration (see objects.yml) is to allow links
     * only to the bottom-most element in the library hierarchy.
     */
    public boolean canLink(String libraryEntityType)
    {
        for (Map.Entry<String, Boolean> entry: hierarchyMap.entrySet())
        {
            if (entry.getKey().equals(libraryEntityType))
            {
                return entry.getValue();
            }
        }
        return false;
    }
}
