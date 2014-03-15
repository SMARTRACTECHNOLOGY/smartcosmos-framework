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

import com.snapbundle.builder.DeviceBuilder;
import com.snapbundle.client.api.ServerContext;
import com.snapbundle.client.api.ServiceException;
import com.snapbundle.client.device.DeviceFactory;
import com.snapbundle.client.device.IDeviceClient;
import com.snapbundle.model.context.IDevice;
import org.json.JSONException;

public class DeviceIT
{
    private static final String USERNAME = "jason@trrllc.com";

    private static final String PASSWORD = "!ABCDEFGm9@2";

    private static final String TEST_IDENTIFICATION = "urn:device:imei:990000862471854";

    private static final String TEST_URN_ALPHA = "urn:uuid:5a1ef494-ab49-4c0d-a7a3-cbaa5c434857";

    private static final String TEST_URN_BETA = "urn:rfid:badge:7e5e8e21-566c-4aea-a460-dbafd4c92392";

    public void testCreate() throws JSONException, ServiceException
    {
        ServerContext context = new ServerContext(USERNAME, PASSWORD);

        IDeviceClient client = DeviceFactory.createClient(context);

        IDevice entity = new DeviceBuilder(TEST_IDENTIFICATION)
                .setName("My Phone")
                .setMoniker("foo")
                .build();

        client.create(entity);
    }

    public void testFetch() throws ServiceException
    {
        ServerContext context = new ServerContext(USERNAME, PASSWORD);

        IDeviceClient client = DeviceFactory.createClient(context);

        IDevice entity = client.findByDeviceIdentification(TEST_IDENTIFICATION);

        System.out.println(entity.getName());

        entity.setName("Updated Phone Name");
        entity.setMoniker("foo-moniker");

        client.update(entity);
    }

    public void testFetchByUrn() throws ServiceException
    {
        ServerContext context = new ServerContext(USERNAME, PASSWORD);

        IDeviceClient client = DeviceFactory.createClient(context);

        IDevice entity = client.findByUrn(TEST_URN_ALPHA);

        System.out.println(entity.getName());
    }

    public static void main(String[] args) throws JSONException, ServiceException
    {
        DeviceIT instance = new DeviceIT();
//        instance.testCreate();
//        instance.testFetch();
        instance.testFetchByUrn();
    }
}
