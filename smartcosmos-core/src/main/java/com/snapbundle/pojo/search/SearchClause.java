/*
 * SnapBundle™ SDK
 * (C) Copyright 2013 Tag Dynamics, LLC (http://tagdynamics.com/)
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

package com.snapbundle.pojo.search;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = {"searchField", "searchPredicate", "value"})
public class SearchClause
{
    public static final String NULL = "null";

    protected Object value;

    protected SearchField searchField;

    protected SearchPredicate searchPredicate;

    public static SearchClause newInstance()
    {
        return new SearchClause();
    }

    public SearchClause field(SearchField searchField)
    {
        this.searchField = searchField;
        return this;
    }

    public SearchClause is(SearchPredicate searchPredicate)
    {
        this.searchPredicate = searchPredicate;
        return this;
    }

    public SearchClause value(Object value)
    {
        this.value = value;
        return this;
    }

    public SearchPredicate predicate()
    {
        return searchPredicate;
    }

    public SearchField field()
    {
        return searchField;
    }

    public Object value()
    {
        return value;
    }
}
