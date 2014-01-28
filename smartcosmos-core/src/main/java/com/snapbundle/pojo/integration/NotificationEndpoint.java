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

package com.snapbundle.pojo.integration;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.snapbundle.model.context.IAccount;
import com.snapbundle.model.integration.INotificationEndpoint;
import com.snapbundle.pojo.base.ReferentialObject;
import com.snapbundle.pojo.context.Account;
import com.snapbundle.util.json.JsonGenerationView;

public class NotificationEndpoint extends ReferentialObject<INotificationEndpoint> implements INotificationEndpoint
{
    @JsonView(JsonGenerationView.Restricted.class)
    @JsonDeserialize(as = Account.class)
    protected IAccount referenceAccount;

    @JsonView(JsonGenerationView.Standard.class)
    protected String encodedPublicKey;

    @JsonView(JsonGenerationView.Restricted.class)
    protected String encodedPrivateKey;

    @JsonView(JsonGenerationView.Restricted.class)
    protected String topicArn;

    @JsonView(JsonGenerationView.Restricted.class)
    protected String subscriptionArn;

    @JsonView(JsonGenerationView.Full.class)
    protected String integrationEndpointUrl;

    @JsonView(JsonGenerationView.Full.class)
    protected boolean pendingConfirmation;

    @JsonView(JsonGenerationView.Published.class)
    protected String name;

    @JsonView(JsonGenerationView.Standard.class)
    protected String description;

    @JsonView(JsonGenerationView.Standard.class)
    protected boolean activeFlag;

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public boolean isActive()
    {
        return activeFlag;
    }

    @Override
    public void setActive(boolean flag)
    {
        this.activeFlag = flag;
    }

    @Override
    public IAccount getReferenceAccount()
    {
        return referenceAccount;
    }

    @Override
    public void setReferenceAccount(IAccount referenceAccount)
    {
        this.referenceAccount = referenceAccount;
    }

    @Override
    public boolean isPendingConfirmation()
    {
        return pendingConfirmation;
    }

    @Override
    public void setPendingConfirmation(boolean pendingConfirmation)
    {
        this.pendingConfirmation = pendingConfirmation;
    }

    @Override
    public String getEncodedPublicKey()
    {
        return encodedPublicKey;
    }

    @Override
    public String getEncodedPrivateKey()
    {
        return encodedPrivateKey;
    }

    @Override
    public String getNotificationEndpointUrl()
    {
        return integrationEndpointUrl;
    }

    @Override
    public void setNotificationEndpointUrl(String integrationEndpointUrl)
    {
        this.integrationEndpointUrl = integrationEndpointUrl;
    }

    @Override
    public String getTopicArn()
    {
        return topicArn;
    }

    @Override
    public void setTopicArn(String topicArn)
    {
        this.topicArn = topicArn;
    }

    @Override
    public String getSubscriptionArn()
    {
        return subscriptionArn;
    }

    @Override
    public void setSubscriptionArn(String subscriptionArn)
    {
        this.subscriptionArn = subscriptionArn;
    }

    @Override
    public void copy(INotificationEndpoint object)
    {
        throw new UnsupportedOperationException("POJO doesn't support copying");
    }
}
