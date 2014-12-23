

package net.smartcosmos.profiles.builder;

import com.google.common.base.Preconditions;
import net.smartcosmos.builder.AbstractBuilder;
import net.smartcosmos.profiles.model.context.ITagProperties;
import net.smartcosmos.profiles.pojo.context.TagProperties;

public class TagPropertiesBuilder extends AbstractBuilder<ITagProperties>
{
    public TagPropertiesBuilder()
    {
        super(new TagProperties());
    }

    public TagPropertiesBuilder addTagIdentifier(String tagIdentifier)
    {
        Preconditions.checkNotNull(tagIdentifier);
        instance.getTagIdentifiers().add(tagIdentifier);
        return this;
    }

    public TagPropertiesBuilder addPropertyName(String propertyName)
    {
        Preconditions.checkNotNull(propertyName);
        instance.getPropertyNames().add(propertyName);
        return this;
    }

    public TagPropertiesBuilder add(String verificationType)
    {
        Preconditions.checkNotNull(verificationType);
        instance.getVerificationTypes().add(verificationType);
        return this;
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkState(instance.getTagIdentifiers().size() > 0, "at least 1 tag identified must be defined");
        Preconditions.checkState(instance.getVerificationTypes().size() > 0, "at least 1 verification type must be defined");
        Preconditions.checkState(instance.getPropertyNames().size() > 0, "at least 1 property name must be defined");
    }
}
