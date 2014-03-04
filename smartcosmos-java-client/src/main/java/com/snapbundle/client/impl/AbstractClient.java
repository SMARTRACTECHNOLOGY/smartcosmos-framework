/*
 * SnapBundle SDK
 * (C) Copyright 2013-2014 Tag Dynamics, LLC (http://tagdynamics.com/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.snapbundle.client.impl;

import com.snapbundle.client.ServerContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import java.io.IOException;

public abstract class AbstractClient
{
    private ServerContext context;

    protected ClientResource createClient(String path)
    {
        return createClient(path, false, new ServerContext());
    }

    protected ClientResource createClient(String path, boolean authenticatedFlag, ServerContext context)
    {
        this.context = context;
        ClientResource service = new ClientResource(assembleEndpoint(path));

        if (authenticatedFlag)
        {
            ChallengeScheme scheme = ChallengeScheme.HTTP_BASIC;

            ChallengeResponse authentication = new ChallengeResponse(scheme,
                    context.getEmailAddress(),
                    context.getCredentials());

            service.setChallengeResponse(authentication);
        }

        return service;
    }

    private String assembleEndpoint(String path)
    {
        return context.getServerUrl().concat(path);
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
