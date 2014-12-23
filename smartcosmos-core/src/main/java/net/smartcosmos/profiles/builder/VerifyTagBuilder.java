

package net.smartcosmos.profiles.builder;

import com.google.common.base.Preconditions;
import net.smartcosmos.builder.AbstractBuilder;
import net.smartcosmos.profiles.model.context.IVerifyTag;
import net.smartcosmos.profiles.pojo.context.VerifyTag;

public class VerifyTagBuilder extends AbstractBuilder<IVerifyTag>
{
    public VerifyTagBuilder(String verificationType)
    {
        super(new VerifyTag());

        Preconditions.checkNotNull(verificationType);
        instance.setVerificationType(verificationType);
    }

    public VerifyTagBuilder addTagIdentifier(String tagIdentifier)
    {
        Preconditions.checkNotNull(tagIdentifier);
        instance.getTagIdentifiers().add(tagIdentifier);
        return this;
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkState(instance.getTagIdentifiers().size() > 0, "at least 1 tag identified must be defined");
    }
}


