package net.smartcosmos.platform.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.internal.http.URIBuilder;

import net.smartcosmos.Field;
import net.smartcosmos.client.common.account.AccountFactory;
import net.smartcosmos.client.common.account.IAccountClient;
import net.smartcosmos.client.common.registration.IRegistrationClient;
import net.smartcosmos.client.common.registration.RegistrationFactory;
import net.smartcosmos.client.connectivity.ServerContext;
import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.model.context.IAccount;

public final class IntegrationTestUtil
{

    /**
     * Utility class.
     */
    private IntegrationTestUtil()
    {
    }

    private static final String SERVER_ADDRESS = System.getProperty("e2eServerAddress", "http://localhost:8080");

    private static final String REALM = System.getProperty("e2eAccountRealm", "smart-cosmos4.com");

    private static final String USERNAME = System.getProperty("e2eAccountEmail", "api@smart-cosmos4.com");

    private static final String PASSWORD = System.getProperty("e2eAccountPassword", "1QXFBDRCf1?6");

    private static final Logger LOG = LoggerFactory.getLogger(IntegrationTestUtil.class);

    public static ServerContext createUser()
    {
        return createUser(SERVER_ADDRESS);
    }

    public static ServerContext createUser(final String server)
    {
        return createUser(REALM, USERNAME, PASSWORD, server);
    }

    public static ServerContext createUser(final String realm, final String username, final String password,
            final String serverAddress)
    {
        try
        {
            final ObjectMapper mapper = new ObjectMapper();
            IRegistrationClient registrationClient = RegistrationFactory.createClient(serverAddress);
            final boolean realmAvailable = registrationClient.isRealmAvailable(realm);
            LOG.info("Realm is avaialble? {}", realmAvailable);
            if (realmAvailable)
            {

                JSONObject jsonObject = new JSONObject()
                        .put(Field.EMAIL_ADDRESS_FIELD, username);

                ClientResource service = new ClientResource(serverAddress.concat("/rest/registration/register"));

                Representation response = service.post(new JsonRepresentation(jsonObject));

                JsonNode responseJson = mapper.readValue(response.getText(), JsonNode.class);

                final String emailVerificationToken = responseJson.get("emailVerificationToken").asText();

                final Representation confirmResponse = new ClientResource(
                        serverAddress.concat("/rest/registration/confirm/")
                                .concat(emailVerificationToken)
                                .concat("/").concat(URIBuilder.encode(username, null))).get();

                JsonNode confirmJson = mapper.readValue(confirmResponse.getText(), JsonNode.class);

                final String tempPassword = confirmJson.get("message").asText();

                LOG.info("Password is {}", tempPassword);

                final IAccountClient accountClient = AccountFactory
                        .createClient(new ServerContext(username, tempPassword, serverAddress));

                if (accountClient.changePassword(tempPassword, password))
                {
                    LOG.info("Changed password, now it's {}", password);
                }
            } else
            {
                LOG.info("Account might already exist.");
                final IAccountClient accountClient = AccountFactory
                        .createClient(new ServerContext(username, password, serverAddress));

                final IAccount account = accountClient.fetch();

                assertNotNull(account);
                assertEquals(realm, account.getName());

            }

            return new ServerContext(username, password, serverAddress);
        } catch (ResourceException | ServiceException | JSONException | IOException e)
        {
            LOG.error("Failed to create user: {}", e.getMessage());
            LOG.debug(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
