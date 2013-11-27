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

package com.snapbundle.pojo.context;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snapbundle.model.context.IAccount;
import com.snapbundle.model.context.LicenseType;
import com.snapbundle.pojo.base.NamedObjectEndpoint;
import com.snapbundle.util.JsonGenerationView;

import java.io.IOException;

public class Account extends NamedObjectEndpoint<IAccount> implements IAccount
{
    @JsonView(JsonGenerationView.Restricted.class)
    protected LicenseType licenseType;

    @JsonView(JsonGenerationView.Minimum.class)
    protected boolean developerMode;

    public static IAccount fromJson(String json) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Account.class);
    }

    @Override
    public void copy(IAccount account)
    {
        throw new UnsupportedOperationException("POJO doesn't support copying");
    }

    @Override
    public LicenseType getLicenseType()
    {
        return licenseType;
    }

    @Override
    public void setLicenseType(LicenseType licenseType)
    {
        this.licenseType = licenseType;
    }

    @Override
    public boolean isDeveloperMode()
    {
        return developerMode;
    }

    @Override
    public void setDeveloperMode(boolean developerMode)
    {
        this.developerMode = developerMode;
    }

    @Override
    public IAccount getAccount()
    {
        return this;
    }
}
