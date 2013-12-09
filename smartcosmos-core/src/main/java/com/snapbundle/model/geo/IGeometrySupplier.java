package com.snapbundle.model.geo;

import com.snapbundle.geo.GeometricShape;

public interface IGeometrySupplier
{
    GeometricShape getGeometry();

    void setGeometry(GeometricShape geometricShape);
}
