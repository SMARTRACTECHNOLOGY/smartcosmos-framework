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

package com.snapbundle.pojo.base;

import com.fasterxml.jackson.annotation.JsonView;
import com.snapbundle.model.base.INotificationEndpoint;
import com.snapbundle.util.json.JsonGenerationView;

public abstract class NamedObjectEndpoint<T> extends NamedObject<T> implements INotificationEndpoint
{
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
    public String getIntegrationEndpointUrl()
    {
        return integrationEndpointUrl;
    }

    @Override
    public void setIntegrationEndpointUrl(String integrationEndpointUrl)
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
}
