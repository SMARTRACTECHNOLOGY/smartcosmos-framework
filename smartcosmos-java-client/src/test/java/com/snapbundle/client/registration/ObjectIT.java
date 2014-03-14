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

package com.snapbundle.client.registration;

import com.snapbundle.builder.ObjectBuilder;
import com.snapbundle.client.ServerContext;
import com.snapbundle.client.ServiceException;
import com.snapbundle.client.endpoint.ObjectEndpoints;
import com.snapbundle.client.object.IObjectClient;
import com.snapbundle.client.object.ObjectFactory;
import com.snapbundle.model.context.IObject;
import org.json.JSONException;

import java.util.Collection;
import java.util.UUID;

public class ObjectIT
{
    private static final String USERNAME = "jason@trrllc.com";

    private static final String PASSWORD = "!ABCDEFGm9@2";

    private static final String FIRST_OBJECT_URN = "urn:rfid:badge:36b2a8cc-92c5-4408-b4e5-4f7cb019cffd";

    private static final String NEXT_OBJECT_URN = "urn:rfid:badge:7e5e8e21-566c-4aea-a460-dbafd4c92392";

    public void createObject() throws JSONException, ServiceException
    {
        ServerContext context = new ServerContext(USERNAME, PASSWORD);

        IObjectClient client = ObjectFactory.createClient(context);

        String objectUrn = "urn:rfid:badge:" + UUID.randomUUID();

        IObject object = new ObjectBuilder(objectUrn)
                .setName("My RFID Tag")
                .setType("Badge")
                .build();

        client.create(object);
    }

    public void fetchObject() throws ServiceException
    {
        ServerContext context = new ServerContext(USERNAME, PASSWORD);

        IObjectClient client = ObjectFactory.createClient(context);

        IObject object = client.findByExactObjectUrn(FIRST_OBJECT_URN);

        System.out.println(object.getName());

        object.setName("Updated RFID Tag Name");
        object.setMoniker("foo-moniker");

        client.update(object);
    }


    public void searchTestA() throws ServiceException
    {
        ServerContext context = new ServerContext(USERNAME, PASSWORD);
        IObjectClient client = ObjectFactory.createClient(context);

        ObjectEndpoints.Builder builder = new ObjectEndpoints.Builder()
                .objectUrnLike("urn:rfid:badge:36");

        Collection<IObject> matches = client.query(builder);

        System.out.println(matches.size());

    }

    public static void main(String[] args) throws JSONException, ServiceException
    {
        ObjectIT instance = new ObjectIT();
//        instance.createObject();
//        instance.fetchObject();
        instance.searchTestA();
    }

}
