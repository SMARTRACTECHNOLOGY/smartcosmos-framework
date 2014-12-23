package net.smartcosmos.objects.model.context;

import net.smartcosmos.model.base.IAccountDomainResource;
import net.smartcosmos.model.base.ITypedObject;

/**
 * Captures a political address for a specific {@link IObject}. The
 * addresses differ from {@link net.smartcosmos.model.geo.IGeospatialEntry} in that these
 * addresses are in the context of street, city, state, country and not latitude and longitude.
 */
public interface IObjectAddress extends IAccountDomainResource<IObjectAddress>, ITypedObject
{
    IObject getObject();

    void setObject(IObject object);

    String getLine1();

    void setLine1(String line1);

    String getLine2();

    void setLine2(String line2);

    String getCity();

    void setCity(String city);

    String getStateProvince();

    void setStateProvince(String stateProvince);

    String getPostalCode();

    void setPostalCode(String postalCode);

    String getCountryAbbreviation();

    void setCountryAbbreviation(String countryAbbreviation);

    long getTimestamp();

    void setTimestamp(long timestamp);
}
