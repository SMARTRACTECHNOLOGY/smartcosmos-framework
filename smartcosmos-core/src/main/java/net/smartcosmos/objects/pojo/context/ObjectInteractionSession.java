

package net.smartcosmos.objects.pojo.context;

import com.fasterxml.jackson.annotation.JsonView;
import net.smartcosmos.objects.model.context.IObjectInteractionSession;
import net.smartcosmos.pojo.base.AccountTypedNamedObject;
import net.smartcosmos.util.json.JsonGenerationView;

public class ObjectInteractionSession extends AccountTypedNamedObject<IObjectInteractionSession> implements IObjectInteractionSession
{
    @JsonView(JsonGenerationView.Minimum.class)
    protected long startTimestamp;

    @JsonView(JsonGenerationView.Minimum.class)
    protected long stopTimestamp;

    public long getStartTimestamp()
    {
        return startTimestamp;
    }

    public void setStartTimestamp(long startTimestamp)
    {
        this.startTimestamp = startTimestamp;
    }

    public long getStopTimestamp()
    {
        return stopTimestamp;
    }

    public void setStopTimestamp(long stopTimestamp)
    {
        this.stopTimestamp = stopTimestamp;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ObjectInteractionSession that = (ObjectInteractionSession) o;

        if (startTimestamp != that.startTimestamp) return false;
        if (stopTimestamp != that.stopTimestamp) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + (int) (startTimestamp ^ (startTimestamp >>> 32));
        result = 31 * result + (int) (stopTimestamp ^ (stopTimestamp >>> 32));
        return result;
    }
}
