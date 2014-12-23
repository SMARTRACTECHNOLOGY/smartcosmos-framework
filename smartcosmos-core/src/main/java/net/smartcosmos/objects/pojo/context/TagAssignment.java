package net.smartcosmos.objects.pojo.context;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.smartcosmos.objects.model.context.ITag;
import net.smartcosmos.objects.model.context.ITagAssignment;
import net.smartcosmos.pojo.base.ReferentialObject;
import net.smartcosmos.util.json.JsonGenerationView;

public class TagAssignment extends ReferentialObject<ITagAssignment> implements ITagAssignment
{
    @JsonDeserialize(as = Tag.class)
    @JsonView(JsonGenerationView.Minimum.class)
    protected ITag tag;

    @Override
    public ITag getTag()
    {
        return tag;
    }

    @Override
    public void setTag(ITag tag)
    {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TagAssignment that = (TagAssignment) o;

        if (!tag.equals(that.tag)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + tag.hashCode();
        return result;
    }
}


