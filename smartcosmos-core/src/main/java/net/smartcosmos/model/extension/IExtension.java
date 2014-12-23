package net.smartcosmos.model.extension;

import net.smartcosmos.model.base.IDomainResource;
import net.smartcosmos.model.base.INamedObject;
import net.smartcosmos.model.context.IAccount;

/**
 * Primary means of expanding the capabilities of the platform. This is where 3rd party developers hook into the system
 * to provide unique computational capabilities. When combined with the near real-time event delivery mechanisms
 * afforded by {@link net.smartcosmos.model.integration.INotificationEndpoint}, external systems
 * have access to the data flow to maintain state, perform calculations, etc. as data immediately changes in
 * the context store.
 */
public interface IExtension extends IDomainResource<IExtension>, INamedObject<IExtension>
{
    int getVersion();

    void setVersion(int version);

    IAccount getAccount();

    void setAccount(IAccount account);

    String getSupportEmail();

    void setSupportEmail(String supportEmail);

    String getWebSiteUrl();

    void setWebSiteUrl(String webSiteUrl);

    String getClientId();

    String getClientSecret();

    String getAppCatalogUrl();

    String getRedirectUrl();

    void setRedirectUrl(String redirectUrl);

    String getLongDescription();

    void setLongDescription(String longDescription);

    long getRegistrationTimestamp();

    ExtensionType getExtensionType();

    void setExtensionType(ExtensionType extensionType);
}
