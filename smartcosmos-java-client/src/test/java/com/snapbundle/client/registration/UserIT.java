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

import com.snapbundle.builder.UserBuilder;
import com.snapbundle.client.api.ServerContext;
import com.snapbundle.client.api.ServiceException;
import com.snapbundle.client.device.DeviceFactory;
import com.snapbundle.client.device.IDeviceClient;
import com.snapbundle.client.user.IUserClient;
import com.snapbundle.client.user.UserFactory;
import com.snapbundle.model.context.IDevice;
import com.snapbundle.model.context.IUser;
import com.snapbundle.model.context.RoleType;
import com.snapbundle.pojo.base.ResponseEntity;
import com.snapbundle.util.json.ViewType;
import org.json.JSONException;

public class UserIT
{
    private static final String USERNAME = "jason@trrllc.com";

    private static final String PASSWORD = "!ABCDEFGm9@2";

    private static final String CREATED_USERNAME = "jrw@trrllc.com";

    private static final String CREATED_PASSWORD = "s3cret!";

    private static final String TEST_IDENTIFICATION = "urn:device:imei:990000862471854";

    private static final String UPDATED_PASSWORD = "s3cret!";

    public void testCreate() throws JSONException, ServiceException
    {
        ServerContext context = new ServerContext(USERNAME, PASSWORD);
        IUserClient client = UserFactory.createClient(context);

        IUser newUser = new UserBuilder("jrw@trrllc.com")
                .setGivenName("Jason")
                .setSurname("Weiss")
                .setRoleType(RoleType.Administrator)
                .build();

        ResponseEntity responseEntity = client.create(newUser);

        System.out.printf("Code: %s, %s", responseEntity.getCode(), responseEntity.getMessage());
    }

    public static void main(String[] args) throws JSONException, ServiceException
    {
        UserIT userIT = new UserIT();
        userIT.testCreate();
//        userIT.testChangePassword();
//        userIT.testNewUserLogin();
//        userIT.testFindByEmail();
    }

    private void testFindByEmail() throws ServiceException
    {
        ServerContext context = new ServerContext(USERNAME, PASSWORD);
        IUserClient client = UserFactory.createClient(context);

        IUser match = client.findByEmailAddress(CREATED_USERNAME, ViewType.Full);
        System.out.println(match.getUrn());
        System.out.println(match.getSurname());
    }

    private void testChangePassword() throws ServiceException
    {
        ServerContext context = new ServerContext(USERNAME, PASSWORD);
        IUserClient client = UserFactory.createClient(context);

        client.changePassword(CREATED_USERNAME, UPDATED_PASSWORD);
    }

    private void testNewUserLogin() throws ServiceException
    {
        ServerContext context = new ServerContext(CREATED_USERNAME, UPDATED_PASSWORD);
        IDeviceClient client = DeviceFactory.createClient(context);

        IDevice device = client.findByDeviceIdentification(TEST_IDENTIFICATION);
        System.out.println(device.getName());
    }
}
