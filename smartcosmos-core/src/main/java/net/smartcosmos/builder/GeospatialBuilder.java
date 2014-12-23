package net.smartcosmos.builder;

import com.google.common.base.Preconditions;
import net.smartcosmos.geo.GeometricShape;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.model.geo.IGeospatialEntry;
import net.smartcosmos.pojo.geo.GeospatialEntry;

/**
 * Convenience Builder pattern class for creating new {@link net.smartcosmos.model.extension.IExtension} instances.
 * <p/>
 * The minimum fields required to define a new instance are:
 * <ul>
 * <li>{@link net.smartcosmos.Field#NAME_FIELD}</li>
 * <li>{@link net.smartcosmos.Field#TYPE_FIELD}</li>
 * </ul>
 */
public final class GeospatialBuilder extends AbstractNamedObjectBuilder<IGeospatialEntry, GeospatialBuilder>
{
    public GeospatialBuilder(GeometricShape shape)
    {
        super(new GeospatialEntry());
        Preconditions.checkNotNull(shape);
        instance.setGeometricShape(shape);
    }

    public GeospatialBuilder setAccount(IAccount account)
    {
        instance.setAccount(account);
        return this;
    }

    public GeospatialBuilder setType(String type)
    {
        instance.setType(type);
        return this;
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getName(), "name must not be null");
        Preconditions.checkNotNull(instance.getType(), "type must not be null");
    }
}

