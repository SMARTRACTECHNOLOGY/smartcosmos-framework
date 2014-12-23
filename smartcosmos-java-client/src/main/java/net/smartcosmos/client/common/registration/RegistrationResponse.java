package net.smartcosmos.client.common.registration;

import net.smartcosmos.Field;
import org.json.JSONException;
import org.json.JSONObject;

public class RegistrationResponse
{
    private final String urn;

    private final String realm;

    private final String emailAddress;

    private final long lastModifiedTimestamp;

    public RegistrationResponse(JSONObject jsonObject) throws JSONException
    {
        this.urn = jsonObject.getString(Field.URN_FIELD);
        this.realm = jsonObject.getString(Field.REALM_FIELD);
        this.emailAddress = jsonObject.getString(Field.EMAIL_ADDRESS_FIELD);
        this.lastModifiedTimestamp = jsonObject.getLong(Field.LAST_MODIFIED_TIMESTAMP_FIELD);
    }

    public String getUrn()
    {
        return urn;
    }

    public String getRealm()
    {
        return realm;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public long getLastModifiedTimestamp()
    {
        return lastModifiedTimestamp;
    }
}
