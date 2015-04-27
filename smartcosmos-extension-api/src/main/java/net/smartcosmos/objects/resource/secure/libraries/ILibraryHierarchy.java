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
 *
 */
package net.smartcosmos.objects.resource.secure.libraries;

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

/**
 * Created by tcross on 26.01.2015.
 * <p/>
 * Two public methods, one of which returns the appropriate parent library entity type
 * for a particular library entity type, and the other a boolean to indicate whether a
 * library type accepts links to non-library objects.
 * <p/>
 * Instantiated by LibraryHierarchyFactory, which is responsible for ensuring its
 * completeness and consistency (in the sense of the list of library element types
 * and the list of library link flags are the same size) of the instance.
 */
public interface ILibraryHierarchy
{
    /**
     * @param libraryElementType libraryElementType string
     * @return boolean indicating whether this is a legitimate libraryElementType
     * <p/>
     * Returns the parent library element type for the input library element type.
     * <p/>
     * Returns null if the input library element type is found, but has no parent, ie.,
     * is the element at the top of the hierarchy.
     * <p/>
     * If the input type is not found at all, returns an error string "ErrNoSuchType"
     * <p/>
     * Always returns false for the empty library hierarchy
     */
    boolean isLibraryElementType(String libraryElementType);

    /**
     * @param libraryElementType libraryElementType string
     * @return String this libraryElementType's parent libraryElementType
     * <p/>
     * Returns the parent library element type for the input library element type.
     * <p/>
     * Returns null if the input library element type is found, but has no parent, ie.,
     * is the element at the top of the hierarchy.
     * <p/>
     * If the input type is not found at all, returns an error string "ErrNoSuchType"
     * <p/>
     * Always throws an illegal state exception for the empty library hierarchy
     */
    String getParentTypeFor(String libraryElementType);

    /**
     * @param libraryEntityType
     * @return boolean
     * <p/>
     * Returns a boolean flag indicating whether library elements of the input library element type
     * can have library links added to them. default configuration (see objects.yml) is to allow links
     * only to the bottom-most element in the library hierarchy.
     * <p/>
     * Always returns false for the empty library hierarchy
     */
    boolean canLink(String libraryEntityType);
}
