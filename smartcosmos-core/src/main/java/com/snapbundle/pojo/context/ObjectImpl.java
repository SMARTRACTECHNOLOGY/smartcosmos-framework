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
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.snapbundle.model.context.IAccount;
import com.snapbundle.model.context.IObject;
import com.snapbundle.model.context.IObjectType;
import com.snapbundle.pojo.base.NamedObject;
import com.snapbundle.util.json.JsonGenerationView;

public class ObjectImpl extends NamedObject<IObject> implements IObject
{
    @JsonView(JsonGenerationView.Full.class)
    @JsonDeserialize(as = Account.class)
    protected IAccount account;

    @JsonView(JsonGenerationView.Minimum.class)
    protected String objectUrn;

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonDeserialize(as = ObjectType.class)
    protected IObjectType objectType;

    @Override
    public void copy(IObject object)
    {
        throw new UnsupportedOperationException("POJO doesn't support copying");
    }

    @Override
    public IAccount getAccount()
    {
        return account;
    }

    @Override
    public void setAccount(IAccount account)
    {
        this.account = account;
    }

    @Override
    public String getObjectUrn()
    {
        return objectUrn;
    }

    @Override
    public void setObjectUrn(String objectUrn)
    {
        this.objectUrn = objectUrn;
    }

    @Override
    public IObjectType getObjectType()
    {
        return objectType;
    }

    @Override
    public void setObjectType(IObjectType objectType)
    {
        this.objectType = objectType;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ObjectImpl object = (ObjectImpl) o;

        if (!account.equals(object.account)) return false;
        if (!objectType.equals(object.objectType)) return false;
        if (!objectUrn.equals(object.objectUrn)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + account.hashCode();
        result = 31 * result + objectUrn.hashCode();
        result = 31 * result + objectType.hashCode();
        return result;
    }
}
