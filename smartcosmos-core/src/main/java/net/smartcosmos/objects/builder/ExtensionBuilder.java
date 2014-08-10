/*
 * SMART COSMOS SDK
 * (C) Copyright 2013-2014, Smartrac Technology Fletcher, Inc.
 * 267 Cane Creek Rd, Fletcher, NC, 28732, USA
 * All Rights Reserved.
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

package net.smartcosmos.objects.builder;

import com.google.common.base.Preconditions;
import net.smartcosmos.builder.AbstractNamedObjectBuilder;
import net.smartcosmos.objects.model.extension.ExtensionType;
import net.smartcosmos.objects.model.extension.IExtension;
import net.smartcosmos.objects.pojo.extension.Extension;

/**
 * Convenience Builder pattern class for creating new {@link net.smartcosmos.objects.model.extension.IExtension} instances.
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
