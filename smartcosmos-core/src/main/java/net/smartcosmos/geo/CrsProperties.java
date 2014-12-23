package net.smartcosmos.geo;

import com.fasterxml.jackson.annotation.JsonView;
import net.smartcosmos.util.json.JsonGenerationView;

public class CrsProperties
{
    @JsonView(JsonGenerationView.Minimum.class)
    private String type;

    @JsonView(JsonGenerationView.Minimum.class)
    private String href;

    @JsonView(JsonGenerationView.Minimum.class)
    private String name;

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getHref()
    {
        return href;
    }

    public void setHref(String href)
    {
        this.href = href;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
