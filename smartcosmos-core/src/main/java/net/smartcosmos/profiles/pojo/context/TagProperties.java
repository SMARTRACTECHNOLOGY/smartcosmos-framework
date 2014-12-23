

package net.smartcosmos.profiles.pojo.context;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.base.Preconditions;
import net.smartcosmos.profiles.model.context.ITagProperties;
import net.smartcosmos.util.json.JsonGenerationView;

import java.util.ArrayList;
import java.util.Collection;

public class TagProperties implements ITagProperties
{
    @JsonView(JsonGenerationView.Minimum.class)
    private Collection<String> tagIdentifiers = new ArrayList<>();

    @JsonView(JsonGenerationView.Minimum.class)
    private Collection<String> verificationTypes = new ArrayList<>();

    @JsonView(JsonGenerationView.Minimum.class)
    private Collection<String> propertyNames = new ArrayList<>();

    @Override
    public Collection<String> getTagIdentifiers()
    {
        return tagIdentifiers;
    }

    @Override
    public void setTagIdentifiers(Collection<String> tagIdentifiers)
    {
        Preconditions.checkNotNull(tagIdentifiers);
        this.tagIdentifiers.addAll(tagIdentifiers);
    }

    @Override
    public Collection<String> getVerificationTypes()
    {
        return verificationTypes;
    }

    @Override
    public void setVerificationTypes(Collection<String> verificationTypes)
    {
        Preconditions.checkNotNull(verificationTypes);
        this.verificationTypes.addAll(verificationTypes);
    }

    @Override
    public Collection<String> getPropertyNames()
    {
        return propertyNames;
    }

    @Override
    public void setPropertyNames(Collection<String> propertyNames)
    {
        Preconditions.checkNotNull(propertyNames);
        this.propertyNames.addAll(propertyNames);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagProperties that = (TagProperties) o;

        if (!propertyNames.equals(that.propertyNames)) return false;
        if (!tagIdentifiers.equals(that.tagIdentifiers)) return false;
        if (!verificationTypes.equals(that.verificationTypes)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = tagIdentifiers.hashCode();
        result = 31 * result + verificationTypes.hashCode();
        result = 31 * result + propertyNames.hashCode();
        return result;
    }
}
