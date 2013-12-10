package com.snapbundle.model.geo;

import com.snapbundle.geo.GeometricShape;

public interface IGeometrySupplier
{
    GeometricShape getGeometricShape();

    void setGeometricShape(GeometricShape geometricShape);
}
