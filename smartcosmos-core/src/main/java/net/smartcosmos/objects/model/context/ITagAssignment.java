

package net.smartcosmos.objects.model.context;

import net.smartcosmos.model.base.IAccountDomainResource;
import net.smartcosmos.model.base.IReferentialObject;

/**
 * Captures a {@link ITag} assignment with a specific
 * {@link net.smartcosmos.model.base.IReferentialObject} within a specific account context.
 */
public interface ITagAssignment extends IAccountDomainResource<ITagAssignment>, IReferentialObject
{
    ITag getTag();

    void setTag(ITag tag);
}
