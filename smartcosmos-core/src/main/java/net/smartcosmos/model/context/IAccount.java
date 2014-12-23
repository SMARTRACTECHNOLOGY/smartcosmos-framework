package net.smartcosmos.model.context;

import net.smartcosmos.model.base.IDomainResource;
import net.smartcosmos.model.base.INamedObject;

/**
 * Collection of foundational attributes that define a platform subscription. Each {@link IAccount} will have a unique
 * public-private key generated at creation time. The private key is securely managed by the platform and is
 * inaccessible to all. The public key is accessible to account administrators, and may be used to verify digitally
 * signed data downloaded from platform to confirm it hasn't been tampered with after download.
 */
public interface IAccount extends IDomainResource<IAccount>, INamedObject<IAccount>
{
}
