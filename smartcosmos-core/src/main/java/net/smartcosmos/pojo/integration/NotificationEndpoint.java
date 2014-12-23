package net.smartcosmos.pojo.integration;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Core SDK
 * ===============================================================================
 * Copyright (C) 2013 - 2014 SMARTRAC Technology Fletcher, Inc.
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

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.model.integration.INotificationEndpoint;
import net.smartcosmos.pojo.base.ReferentialObject;
import net.smartcosmos.pojo.context.Account;
import net.smartcosmos.util.json.JsonGenerationView;

/**
 * Represents an HTTP/S integration endpoint POJO that SMART COSMOS uses to push SMART COSMOS events in JSON form
 * in near real-time. These endpoints are used for back office integration and 3rd party extension integration.
 */
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
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        NotificationEndpoint that = (NotificationEndpoint) o;

        if (activeFlag != that.activeFlag) return false;
        if (pendingConfirmation != that.pendingConfirmation) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (!encodedPrivateKey.equals(that.encodedPrivateKey)) return false;
        if (!encodedPublicKey.equals(that.encodedPublicKey)) return false;
        if (!integrationEndpointUrl.equals(that.integrationEndpointUrl)) return false;
        if (!name.equals(that.name)) return false;
        if (!referenceAccount.equals(that.referenceAccount)) return false;
        if (subscriptionArn != null ? !subscriptionArn.equals(that.subscriptionArn) : that.subscriptionArn != null)
            return false;
        if (topicArn != null ? !topicArn.equals(that.topicArn) : that.topicArn != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + referenceAccount.hashCode();
        result = 31 * result + encodedPublicKey.hashCode();
        result = 31 * result + encodedPrivateKey.hashCode();
        result = 31 * result + (topicArn != null ? topicArn.hashCode() : 0);
        result = 31 * result + (subscriptionArn != null ? subscriptionArn.hashCode() : 0);
        result = 31 * result + integrationEndpointUrl.hashCode();
        result = 31 * result + (pendingConfirmation ? 1 : 0);
        result = 31 * result + name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (activeFlag ? 1 : 0);
        return result;
    }
}
