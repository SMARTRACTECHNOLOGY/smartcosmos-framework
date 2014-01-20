package com.snapbundle.model.integration;

import com.snapbundle.model.base.INamedObject;
import com.snapbundle.model.base.IReferentialObject;
import com.snapbundle.model.context.IAccount;

public interface INotificationEndpoint extends INamedObject<INotificationEndpoint>, IReferentialObject
{
    IAccount getAccount();

    void setAccount(IAccount account);

    IAccount getReferenceAccount();

    void setReferenceAccount(IAccount account);

    String getEncodedPublicKey();

    String getEncodedPrivateKey();

    String getTopicArn();

    void setTopicArn(String topicArn);

    String getSubscriptionArn();

    void setSubscriptionArn(String subscriptionArn);

    String getNotificationEndpointUrl();

    void setNotificationEndpointUrl(String endpointUrl);

    boolean isPendingConfirmation();

    void setPendingConfirmation(boolean pendingConfirmation);
}
