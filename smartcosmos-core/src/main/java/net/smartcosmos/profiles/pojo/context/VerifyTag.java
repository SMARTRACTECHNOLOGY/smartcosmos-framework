package net.smartcosmos.profiles.pojo.context;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.base.Preconditions;
import net.smartcosmos.profiles.model.context.IVerifyTag;
import net.smartcosmos.util.json.JsonGenerationView;

import java.util.ArrayList;
import java.util.Collection;

public class VerifyTag implements IVerifyTag
{
    @JsonView(JsonGenerationView.Minimum.class)
    private Collection<String> tagIdentifiers = new ArrayList<>();

    @JsonView(JsonGenerationView.Minimum.class)
    private String verificationType;

    @Override
    public Collection<String> getTagIdentifiers()
    {
        return tagIdentifiers;
    }

    @Override
    public void setTagIdentifiers(Collection<String> tagIdentifiers)
    {
        Preconditions.checkNotNull(tagIdentifiers);
        tagIdentifiers.addAll(tagIdentifiers);
    }

    @Override
    public String getVerificationType()
    {
        return verificationType;
    }

    @Override
    public void setVerificationType(String verificationType)
    {
        Preconditions.checkNotNull(verificationType);
        this.verificationType = verificationType;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VerifyTag that = (VerifyTag) o;

        if (!tagIdentifiers.equals(that.tagIdentifiers)) return false;
        if (!verificationType.equals(that.verificationType)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = tagIdentifiers.hashCode();
        result = 31 * result + verificationType.hashCode();
        return result;
    }
}
