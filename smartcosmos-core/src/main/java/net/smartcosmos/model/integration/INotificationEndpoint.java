package net.smartcosmos.model.integration;

import net.smartcosmos.model.base.INamedObject;
import net.smartcosmos.model.base.IReferentialObject;
import net.smartcosmos.model.context.IAccount;

/**
 * Defines a single external integration endpoint whereby the platform will issue an
 * HTTP/S POST of events occurring within a given account context in near real-time.
 */
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
