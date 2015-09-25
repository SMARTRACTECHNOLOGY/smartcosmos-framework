package ${package}.smartcosmos.extension.server.jpa.impl;

import com.fasterxml.jackson.annotation.JsonView;
import ${package}.smartcosmos.extension.server.jpa.IExampleEntity;
import net.smartcosmos.platform.jpa.base.DomainResourceEntity;
import net.smartcosmos.util.json.JsonGenerationView;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "example")
public class ExampleEntity
        extends DomainResourceEntity<IExampleEntity>
        implements IExampleEntity
{
    private static final long serialVersionUID = 1L;

    // NOTE TO EXTENSION DEVELOPER:
    // We're setting a bunch of properties here for Hibernate and for the JSON view, which you'll notice
    // we're not setting in MoreInterestingExampleEntity.java. Both ways work; defaults are reasonable for both.

    @Column(length = 255, nullable = false, updatable = true, unique = false)
    @JsonView(JsonGenerationView.Minimum.class)
    private String firstString;

    @Column(length = 16, nullable = false, updatable = true, unique = false)
    @JsonView(JsonGenerationView.Minimum.class)
    private String secondString;

    public String getFirstString()
    {
        return firstString;
    }

    public void setFirstString(String firstString)
    {
        this.firstString = firstString;
    }

    public String getSecondString()
    {
        return secondString;
    }

    public void setSecondString(String secondString)
    {
        this.secondString = secondString;
    }

    // NOTE TO EXTENSION DEVELOPER:
    // Notice the difference between this copy method and the one in MoreInterestingExampleEntity
    public void copy(IExampleEntity target)
    {
        super.copy(target);
        this.firstString = target.getFirstString();
        this.secondString = target.getSecondString();
    }
}
