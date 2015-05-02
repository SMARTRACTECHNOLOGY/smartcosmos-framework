package net.smartcosmos.market;

import net.smartcosmos.model.base.INamedObject;

public interface ISellerProfile extends IProfile
{
    String getAboutSeller();

    String getSellerWebSiteUrl();

    String getSupportEmailAddress();
}
