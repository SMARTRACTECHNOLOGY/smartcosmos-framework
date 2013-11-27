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

package com.snapbundle.pojo;

import com.snapbundle.model.base.EntityReferenceType;
import com.snapbundle.pojo.search.SearchClause;
import com.snapbundle.pojo.search.SearchCriteria;
import com.snapbundle.pojo.search.SearchField;
import com.snapbundle.pojo.search.SearchPredicate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SearchExample
{
    public static void main(String[] args) throws IOException
    {
        createObjectInteractionQuery();
    }

    private static void createObjectInteractionQuery() throws IOException
    {
        final long WITHIN_LAST_WEEK = System.currentTimeMillis() - TimeUnit.DAYS.toDays(7);

        String json = SearchCriteria.newInstance(EntityReferenceType.ObjectInteraction)
                .addCriteria(EntityReferenceType.User,
                        SearchClause.newInstance()
                                .field(SearchField.EmailAddress)
                                .is(SearchPredicate.STARTS_WITH)
                                .value("foo"))
                .addCriteria(EntityReferenceType.Device,
                        SearchClause.newInstance()
                                .field(SearchField.DeviceIdentification)
                                .is(SearchPredicate.EQUALS)
                                .value(123456))
                .addCriteria(EntityReferenceType.Object,
                        SearchClause.newInstance()
                                .field(SearchField.ReceivedTimestamp)
                                .is(SearchPredicate.AFTER)
                                .value(WITHIN_LAST_WEEK))
                .setLimit(5)
                .toJson();

        System.out.println(json);

        ObjectInteractionSearchAssembly assembly = new ObjectInteractionSearchAssembly(SearchCriteria.fromJson(json));
        assembly.assemble();
        String whereClause = assembly.getWhereClause();

        System.out.println(whereClause);

    }
}
