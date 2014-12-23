

package net.smartcosmos.pojo.geo;

import com.fasterxml.jackson.annotation.JsonView;
import net.smartcosmos.geo.GeometricShape;
import net.smartcosmos.model.geo.IGeospatialEntry;
import net.smartcosmos.pojo.base.AccountTypedNamedObject;
import net.smartcosmos.util.json.JsonGenerationView;

public class GeospatialEntry extends AccountTypedNamedObject<IGeospatialEntry> implements IGeospatialEntry
{
    @JsonView(JsonGenerationView.Minimum.class)
    protected GeometricShape geometricShape;

    @Override
    public GeometricShape getGeometricShape()
    {
        return geometricShape;
    }

    @Override
    public void setGeometricShape(GeometricShape geometricShape)
    {
        this.geometricShape = geometricShape;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        GeospatialEntry that = (GeospatialEntry) o;

        if (!geometricShape.equals(that.geometricShape)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + geometricShape.hashCode();
        return result;
    }
}

