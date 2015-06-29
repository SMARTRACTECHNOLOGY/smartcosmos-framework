package net.smartcosmos.platform.api.dao;

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

import net.smartcosmos.platform.api.dao.domain.IPage;

public interface IPageProvider<U>
{

    /**
     * 
     * @return the number of total objects in this DAO, regardless of account association.
     */
    Long count();

    /**
     * Retrieves a page of results, unfiltered and containing all data (regardless of account association).
     * 
     * @param page
     *            The page. Must be positive, and always starts at 1.
     * @param pageSize
     *            Number of entries per page. Must be positive, must be at least 1.
     * @return A page object populated with the page provided and at MOST the number of entries provided (unless less
     *         entries exist for this page).
     */
    IPage<U> page(int page, int pageSize);

}
