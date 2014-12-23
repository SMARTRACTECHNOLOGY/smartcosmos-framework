

package net.smartcosmos.builder;

import com.google.common.base.Preconditions;
import net.smartcosmos.model.extension.ExtensionType;
import net.smartcosmos.model.extension.IExtension;
import net.smartcosmos.pojo.extension.Extension;

/**
 * Convenience Builder pattern class for creating new {@link net.smartcosmos.model.extension.IExtension} instances.
 * <p/>
 * The minimum fields required to define a new instance are:
 * <ul>
 * <li>{@link net.smartcosmos.Field#NAME_FIELD}</li>
 * <li>{@link net.smartcosmos.Field#DESCRIPTION_FIELD}</li>
 * <li>{@link net.smartcosmos.Field#LONG_DESCRIPTION_FIELD}</li>
 * <li>{@link net.smartcosmos.Field#REDIRECT_URL_FIELD}</li>
 * <li>{@link net.smartcosmos.Field#EXTENSION_TYPE_FIELD}</li>
 * <li>{@link net.smartcosmos.Field#SUPPORT_EMAIL_FIELD}</li>
 * <li>{@link net.smartcosmos.Field#WEB_SITE_FIELD}</li>
 * </ul>
 */
public final class ExtensionBuilder extends AbstractNamedObjectBuilder<IExtension, ExtensionBuilder>
{
    public ExtensionBuilder(String name)
    {
        super(new Extension());
        this.instance.setName(name);
    }

    public ExtensionBuilder setDescription(String description)
    {
        instance.setDescription(description);
        return this;
    }

    public ExtensionBuilder setLongDescription(String longDescription)
    {
        instance.setLongDescription(longDescription);
        return this;
    }

    public ExtensionBuilder setRedirectUrl(String redirectUrl)
    {
        instance.setRedirectUrl(redirectUrl);
        return this;
    }

    public ExtensionBuilder setExtensionType(ExtensionType extensionType)
    {
        instance.setExtensionType(extensionType);
        return this;
    }

    public ExtensionBuilder setSupportEmailAddress(String emailAddress)
    {
        instance.setSupportEmail(emailAddress);
        return this;
    }


    public ExtensionBuilder setWebSiteUrl(String webSitUrl)
    {
        instance.setWebSiteUrl(webSitUrl);
        return this;
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getName(), "name must not be null");
        Preconditions.checkNotNull(instance.getDescription(), "description must not be null");
        Preconditions.checkNotNull(instance.getLongDescription(), "long description must not be null");
        Preconditions.checkNotNull(instance.getRedirectUrl(), "redirect url must not be null");
        Preconditions.checkNotNull(instance.getExtensionType(), "extension type must not be null");
        Preconditions.checkNotNull(instance.getSupportEmail(), "support email must not be null");
        Preconditions.checkNotNull(instance.getWebSiteUrl(), "web site url must not be null");
    }
}
