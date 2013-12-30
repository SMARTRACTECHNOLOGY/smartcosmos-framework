# Geometric Shape Support
SnapBundle™ fully supports the [GeoJSON 1.0 Specification](http://geojson.org/geojson-spec.html). This powerful specification allows develoeprs to _fully_ define georectified shapes for literally any primary object managed by the SnapBundle™ platform.

> SnapBundle™ will continue to support the more limited `IGeoLocation` concept for those objects that already provide support. However, all future modeling will leverage the GeoJSON specification for a more accurate georectification definition.  

## Georectification Fields
Field | Data Type | Required | Can Update | Serialization Level | Default Value
------------ | ------------- | ------------ | ------------ | ------------ | ------------
uniqueId | long  | true | false | Restricted | Generated
urn | String  | true | false | Minimum | Generated
lastModifiedTimestamp | long   | true | false | Standard | Generated
moniker | String  | false | true | Standard | null
account | IAccount  | true | fase | Full | Generated
entityReferenceType | EntityReferenceType | true | false | Minimum |
referenceURN | String | true | false | Minimum |
name | String  | true | true | Minimum |
description | String  | false | true | Standard |
activeFlag | Boolean  | true | false | Standard  |
geometricShape | GeometricShape | true | true | Minimum | 
georectificationType | GeorectificationType | true | true | Minimum | 
