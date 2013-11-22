package com.snapbundle.client;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import java.io.IOException;

public class AbstractClient
{
    protected ClientResource createClient(String path)
    {
        return createClient(path, false);
    }

    protected ClientResource createClient(String path, boolean authenticatedFlag)
    {
        ClientResource service = new ClientResource(assembleEndpoint(path));

        if (authenticatedFlag)
        {
            ChallengeScheme scheme = ChallengeScheme.HTTP_BASIC;

            ChallengeResponse authentication = new ChallengeResponse(scheme,
                    SnapConfiguration.getEmailAddress(),
                    SnapConfiguration.getCredentials());

            service.setChallengeResponse(authentication);
        }

        return service;
    }

    private String assembleEndpoint(String path)
    {
        return SnapConfiguration.SERVER_BASE_URL().concat(path);
    }

    protected JSONObject toJsonObject(Representation representation) throws IOException, JSONException
    {
        return new JsonRepresentation(representation).getJsonObject();
    }

    protected JSONArray toJsonArray(Representation representation) throws IOException, JSONException
    {
        return new JsonRepresentation(representation).getJsonArray();
    }
}
