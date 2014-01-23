# Geospatial Support
SnapBundle™ fully supports the [GeoJSON 1.0 Specification](http://geojson.org/geojson-spec.html). This powerful specification allows develoeprs to _fully_ define georectified shapes for any primary object managed by the platform.

The design is similar to popular social media sites that offer check-in capabilities. It is reasonble to define a single georectified entity and then create multiple relationships, such as check-ins, etc. (Remember, the relationship type is an arbitrary String, so a relationship type of "Check-In" could be arbitrarily defined by the developer)

## Geospatial Fields
Field | Data Type | Required | Can Update | Serialization Level | Default Value
------------ | ------------- | ------------ | ------------ | ------------ | ------------
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
type | String | true | true | Minimum | 

## Geospatial Endpoints

Endpoint | Supported HTTP Methods | Events Generated
------------ | ------------- | ------------
/geospatial | PUT  | FileDefined
/geospatial/{urn} | GET | FileAccessed
/geospatial | POST  | FileDefined
/geospatial?nameLike=foo | GET | FileAccessed