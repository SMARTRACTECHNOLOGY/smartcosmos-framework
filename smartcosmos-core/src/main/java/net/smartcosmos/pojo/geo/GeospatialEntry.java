package net.smartcosmos.pojo.geo;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Core SDK
 * ===============================================================================
 * Copyright (C) 2013-2015 SMARTRAC Technology Fletcher, Inc.
 * ===============================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#
 */

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

