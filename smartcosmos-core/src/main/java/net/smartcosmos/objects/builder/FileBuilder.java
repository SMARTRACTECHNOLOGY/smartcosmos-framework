package net.smartcosmos.objects.builder;

import com.google.common.base.Preconditions;
import net.smartcosmos.builder.AbstractReferentialBuilder;
import net.smartcosmos.objects.model.context.IFile;
import net.smartcosmos.objects.pojo.context.File;

/**
 * Convenience Builder pattern class for creating new {@link net.smartcosmos.objects.model.context.IFile} instances.
 * <p/>
 * The minimum fields required to define a new instance are:
 * <ul>
 * <li>{@link net.smartcosmos.Field#ENTITY_REFERENCE_TYPE}</li>
 * <li>{@link net.smartcosmos.Field#REFERENCE_URN_FIELD}</li>
 * <li>{@link net.smartcosmos.Field#MIME_TYPE_FIELD}</li>
 * </ul>
 */
public final class FileBuilder extends AbstractReferentialBuilder<IFile, FileBuilder>
{
    public FileBuilder(String mimeType)
    {
        super(new File());
        instance.setMimeType(mimeType);
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getReferenceUrn(), "Reference Urn must not be null");
        Preconditions.checkNotNull(instance.getEntityReferenceType(), "Entity Reference Type must not be null");
        Preconditions.checkNotNull(instance.getMimeType(), "MIME type must not be null");
    }
}
