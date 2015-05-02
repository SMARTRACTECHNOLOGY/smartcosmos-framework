package net.smartcosmos.market;

import net.smartcosmos.model.base.IUrnNamespace;

public interface IProfile extends IUrnNamespace
{
    String getProfileId();

    String getName();

    String getLogoUrl();
}
