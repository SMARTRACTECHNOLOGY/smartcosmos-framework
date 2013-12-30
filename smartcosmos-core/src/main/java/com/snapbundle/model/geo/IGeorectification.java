package com.snapbundle.model.geo;

import com.snapbundle.model.base.IAccountDomainResource;
import com.snapbundle.model.base.INamedObject;
import com.snapbundle.model.base.IReferentialObject;

/**
 * Referential object that creates a geographic binding to a reference object. The underlying format is represented by
 * the GeoJSON 1.0 specification. GeoJSON is a format for encoding a variety of geographic data structures. A GeoJSON
 * object may represent a geometry, a feature, or a collection of features. GeoJSON supports the following geometry
 * types:
 * <ul>
 * <li>Point</li>
 * <li>LineString</li>
 * <li>Polygon</li>
 * <li>MultiPoint</li>
 * <li>MultiLineString</li>
 * <li>MultiPolygon</li>
 * <li>GeometryCollection</li>
 * <li>Feature</li>
 * <li>FeatureCollection</li>
 * </ul>
 * Features in GeoJSON contain a geometry object and additional properties, and a feature collection represents a list
 * of features.
 * <p/>
 * To capture the a temporal geo-rectification, create an {@link com.snapbundle.model.geo.IGeorectification} that refers
 * to the object, then use the {@link IGeorectification#getUrn()} and
 * {@link com.snapbundle.model.base.EntityReferenceType#Georectification} to place the location on an
 * {@link com.snapbundle.model.context.ITimeline}.
 * <p/>
 * Possible uses of the georectification object include the definition of a complex geo-fence that marks
 * a job site, a multi-point set of coordinates that represents a courier route, or
 * perhaps an analytical view of aggregated data. Another use case is to link an
 * {@link com.snapbundle.model.context.IObjectAddress} with a georectification to provide
 * a three-dimensional representation of the building located at a specific address
 * identified in only general terms by the postal address and {@link com.snapbundle.model.base.IGeoLocation}
 * defined in the {@link com.snapbundle.model.context.IObjectAddress} record.
 */
public interface IGeorectification extends IAccountDomainResource<IGeorectification>, IReferentialObject, IGeometrySupplier, INamedObject<IGeorectification>
{
    GeorectificationType getGeorectificationType();

    void setGeorectificationType(GeorectificationType georectificationType);
}
