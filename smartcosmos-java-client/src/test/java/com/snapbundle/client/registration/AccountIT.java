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

import com.snapbundle.client.ServerContext;
import com.snapbundle.client.ServiceException;
import com.snapbundle.client.account.AccountFactory;
import com.snapbundle.client.account.IAccountClient;
import com.snapbundle.model.context.IAccount;
import com.snapbundle.util.json.ViewType;

public class AccountIT
{

    private static final String NEW_PASSWORD = "!ABCDEFGm9@2";

    private void view(String assignedPassword) throws ServiceException
    {
        ServerContext context = new ServerContext("jason@trrllc.com", assignedPassword);

        IAccountClient client = AccountFactory.createClient(context);
        IAccount account = client.fetch();
        System.out.println(account.getName());
        System.out.println(account.getLastModifiedTimestamp());
    }

    private void viewFull(String assignedPassword) throws ServiceException
    {
        ServerContext context = new ServerContext("jason@trrllc.com", assignedPassword);

        IAccountClient client = AccountFactory.createClient(context);
        IAccount account = client.fetch(ViewType.Minimum);
        System.out.println(account.getName());
        System.out.println(account.getLastModifiedTimestamp());
    }

//    private void changePassword() throws ServiceException
//    {
////        final String assignedPassword = "1JHHLBGGm9@2";
//        ServerContext context = new ServerContext("jason@trrllc.com", assignedPassword);
//
//        IAccountClient client = AccountFactory.createClient(context);
//        boolean result = client.changePassword(assignedPassword, NEW_PASSWORD);
//        System.out.println("Change Result: " + result);
//
//        view(NEW_PASSWORD);
//    }

    public static void main(String[] args) throws ServiceException
    {
        final String assignedPassword = "1JHHLBGGm9@2";

        AccountIT accountIT = new AccountIT();
        accountIT.view(NEW_PASSWORD);
        accountIT.viewFull(NEW_PASSWORD);
//        accountIT.changePassword();
    }
}
