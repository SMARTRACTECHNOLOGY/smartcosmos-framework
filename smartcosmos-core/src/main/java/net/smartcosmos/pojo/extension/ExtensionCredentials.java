package net.smartcosmos.pojo.extension;

public final class ExtensionCredentials
{
    private String urn;

    private String clientId;

    private String clientSecret;

    private String redirectUri;

    public ExtensionCredentials(String urn, String clientId, String clientSecret, String redirectUri)
    {
        this.urn = urn;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
    }

    public ExtensionCredentials()
    {

    }

    public String getRedirectUri()
    {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri)
    {
        this.redirectUri = redirectUri;
    }

    public String getUrn()
    {
        return urn;
    }

    public String getClientId()
    {
        return clientId;
    }

    public String getClientSecret()
    {
        return clientSecret;
    }
}
