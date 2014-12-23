

package net.smartcosmos.profiles.pojo.context;

import com.fasterxml.jackson.annotation.JsonView;
import net.smartcosmos.profiles.model.context.ITagCode;
import net.smartcosmos.util.json.JsonGenerationView;

public class TagCode implements ITagCode
{
    @JsonView(JsonGenerationView.Minimum.class)
    private int tagCode;

    @Override
    public int getTagCode()
    {
        return tagCode;
    }

    @Override
    public void setTagCode(int tagCode)
    {
        this.tagCode = tagCode;
    }
}
