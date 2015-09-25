package net.smartcosmos.platform.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.internal.http.URIBuilder;

import net.smartcosmos.Field;
import net.smartcosmos.builder.MetadataBuilder;
import net.smartcosmos.client.common.account.AccountFactory;
import net.smartcosmos.client.common.account.IAccountClient;
import net.smartcosmos.client.common.metadata.IMetadataClient;
import net.smartcosmos.client.common.metadata.MetadataFactory;
import net.smartcosmos.client.common.registration.IRegistrationClient;
import net.smartcosmos.client.common.registration.RegistrationFactory;
import net.smartcosmos.client.connectivity.ServerContext;
import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.objects.interaction.IInteractionClient;
import net.smartcosmos.client.objects.interaction.InteractionFactory;
import net.smartcosmos.client.objects.object.IObjectClient;
import net.smartcosmos.client.objects.object.ObjectFactory;
import net.smartcosmos.client.objects.relationship.IRelationshipClient;
import net.smartcosmos.client.objects.relationship.RelationshipFactory;
import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.model.context.IMetadata;
import net.smartcosmos.model.context.MetadataDataType;
import net.smartcosmos.objects.builder.ObjectBuilder;
import net.smartcosmos.objects.builder.RelationshipBuilder;
import net.smartcosmos.objects.model.context.IObject;
import net.smartcosmos.objects.model.context.IRelationship;
import net.smartcosmos.pojo.base.ResponseEntity;

/**
 * This isn't really a test, it's a means to populate the database with fake data. If you're running the DevKit, then
 * this will use an embedded database, enabling you to populate Objects and start using it right away!
 */
public class PopulateObjectsServer
{

    private static final Logger LOG = LoggerFactory.getLogger(PopulateObjectsServer.class);

    public static final String PREFIX = "urn:wiskytango:";

    private final String serverAddress = System.getProperty("e2eServerAddress", "http://localhost:8080");

    private final String realm = System.getProperty("e2eAccountRealm", "smart-cosmos4.com");

    private final String username = System.getProperty("e2eAccountEmail", "api@smart-cosmos4.com");

    private final String password = System.getProperty("e2eAccountPassword", "1QXFBDRCf1?6");

    private final int numberStores = Integer.valueOf(System.getProperty("e2eNumStores", "10"));

    private final int numberAntenna = 5;
    private final int numberTags = 10;

    private final boolean createUser = true;

    private final ObjectMapper mapper = new ObjectMapper();

    private ServerContext context;

    private final List<String> storeUrns = new ArrayList<>();

    private final Map<String, List<String>> antennaUrns = new HashMap<>();
    private final List<String> tagObjectUrns = new ArrayList<>();

    IObjectClient client;

    IMetadataClient metadataClient;

    IRelationshipClient relationshipClient;

    IInteractionClient interactionClient;

    private int createdStores = 0;
    private int createdAntenna = 0;
    private int createdNumber = 0;
    private int createdLocation = 0;
    private int createdRelationship = 0;
    private int createdTags = 0;

    private void createStores()
    {
        try
        {
            for (String storeObjectUrn : storeUrns)
            {
                final int storeNumber = storeUrns.indexOf(storeObjectUrn);

                final IObject preCreatedStore = new ObjectBuilder(storeObjectUrn)
                        .setName("Store Number " + storeNumber)
                        .setType("store")
                        .build();

                try
                {
                    client.create(preCreatedStore).log();
                    LOG.info("Created new store, " + preCreatedStore.toString());
                    createdStores++;
                } catch (ServiceException e)
                {
                    LOG.debug(e.getMessage(), e);
                    LOG.info("Store already exists, attempting to create the antenna.");
                }

                final IObject storeObject = client.findByExactObjectUrn(storeObjectUrn);
                LOG.info("Retrieved new store {}", storeObject.toString());

                // Add a couple antennas to the store.
                for (String antennaUrn : antennaUrns.get(storeObjectUrn))
                {

                    final int antennaNumber = antennaUrns.get(storeObjectUrn).indexOf(antennaUrn);

                    final IObject preCreatedAntenna = new ObjectBuilder(antennaUrn)
                            .setName("antenna " + antennaNumber + " for store " + storeNumber)
                            .setType("antenna")
                            .build();
                    try
                    {
                        client.create(preCreatedAntenna).log();
                        createdAntenna++;
                    } catch (ServiceException e)
                    {
                        LOG.debug(e.getMessage(), e);
                        LOG.info("Antenna already exists, attempting to add metadata.");
                    }

                    LOG.info("Looking for {}", antennaUrn);
                    final IObject antennaObject = client.findByExactObjectUrn(antennaUrn);

                    // Every atennas has two metada values: number (Integer) and store (String)

                    final IMetadata numberMeta = new MetadataBuilder(MetadataDataType.IntegerType)
                            .setReferenceUrn(antennaObject.getUrn())
                            .setEntityReferenceType(EntityReferenceType.Object)
                            .setKey("number")
                            .setValue(String.valueOf(antennaNumber))
                            .build();
                    try
                    {
                        metadataClient.upsert(numberMeta).log();
                        LOG.info("Inserted new number: {} to antenna {}",
                                String.valueOf(antennaNumber),
                                antennaObject.getUrn());
                        createdNumber++;
                    } catch (ServiceException e)
                    {
                        LOG.debug(e.getMessage(), e);
                        LOG.info("Failed to INSERT, might have already existed.");
                    }

                    final IMetadata storeMeta = new MetadataBuilder(MetadataDataType.StringType)
                            .setReferenceUrn(antennaObject.getUrn())
                            .setEntityReferenceType(EntityReferenceType.Object)
                            .setKey("location")
                            .setValue("Location Number " + antennaNumber)
                            .build();
                    try
                    {
                        metadataClient.upsert(storeMeta).log();
                        LOG.info("Inserted new location: {} to antenna {}",
                                "Location Number " + antennaNumber,
                                antennaObject.getUrn());
                        createdLocation++;
                    } catch (ServiceException e)
                    {
                        LOG.debug(e.getMessage(), e);
                        LOG.info("Failed to INSERT, might have already existed.");
                    }

                    // Every antenna "belongs to" a store.
                    IRelationship relationship = new RelationshipBuilder(EntityReferenceType.Object,
                            storeObject.getUrn())
                                    .setRelatedEntityReferenceType(EntityReferenceType.Object)
                                    .setRelatedReferenceUrn(antennaObject.getUrn())
                                    .setType("belongs_to")
                                    .build();
                    try
                    {
                        relationshipClient.upsert(relationship).log();
                        LOG.info("Inserted new relationship: antenna {} belongs to store {}",
                                antennaObject.getUrn(), storeObject.getUrn());
                        createdRelationship++;
                    } catch (ServiceException e)
                    {
                        LOG.debug(e.getMessage(), e);
                        LOG.info("Failed to INSERT, might have already existed.");
                    }
                }

            }
        } catch (ServiceException e)
        {
            LOG.debug(e.getMessage(), e);
            LOG.info(
                    "Failed to find one of the exact object URNs, current progress was: stores: {}, antenna:" +
                            " {}, numbers: {}, locations: {}, relationships: {}",
                    createdStores, createdAntenna, createdNumber, createdLocation, createdRelationship);
            fail("Creation of stores failed.");
        }
    }

    private void createTags()
    {

        for (String tagObjectUrn : tagObjectUrns)
        {

            final IObject entity = new ObjectBuilder(tagObjectUrn)
                    .setName("Tag Number " + tagObjectUrns.indexOf(tagObjectUrn))
                    .setType("tag")
                    .build();

            try
            {
                client.create(entity).log();
                createdTags++;
            } catch (ServiceException e)
            {
                LOG.debug(e.getMessage(), e);
                LOG.info("Failed to create a tag, specifically: {}", tagObjectUrn);
            }
        }

        LOG.info("Created {} tags, out of {}", createdTags, tagObjectUrns.size());
    }

    @Test
    public void createUser() throws Exception
    {

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

    }

    private void makeContext()
    {
        context = new ServerContext(username, password, serverAddress);

        client = ObjectFactory.createClient(context);

        metadataClient = MetadataFactory.createClient(context);

        relationshipClient = RelationshipFactory.createClient(context);

        interactionClient = InteractionFactory.createClient(context);

        // Create some stores.
        for (int i = 0; i < numberStores; i++)
        {
            final String store = PREFIX.concat("store:" + i);
            storeUrns.add(store);

            antennaUrns.put(store, new ArrayList<>());

            // Add a couple antennas to the store.
            for (int j = 0; j < numberAntenna; j++)
            {
                final String antennaUrn = PREFIX.concat("antenna:" + i + ":" + j);
                antennaUrns.get(store).add(antennaUrn);
            }
        }

        // Create some tags.
        for (int t = 0; t < numberTags; t++)
        {
            tagObjectUrns.add(PREFIX.concat("tag:" + t));
        }

    }

    private void makeInteractions() throws ServiceException, JSONException
    {
        // Now the fun part, create some INTERACTIONS! YAY!
        int interactionsMade = 0;
        int errorCount = 0;
        int totalToMake = tagObjectUrns.size() * antennaUrns.size();

        final String type = "read";

        for (String tagObjectUrn : tagObjectUrns)
        {

            for (Map.Entry<String, List<String>> entry : antennaUrns.entrySet())
            {
                for (String antennaUrn : entry.getValue())
                {
                    LOG.debug("For this interaction, {} was {} by {}", tagObjectUrn, type, antennaUrn);
                    // objectUrn must point to a pre-existing record (element 1)
                    // entityReferenceType is required and constrained to a valid EntityReferenceType value defined in
                    // data
                    // types
                    // referenceUrn must point to a pre-existing record of the given entityReferenceType (element 2)
                    // type is required and constrained to 255 characters
                    // recordedTimestamp is required an must a valid long since the java epoch
                    JSONObject interaction = new JSONObject();
                    interaction.put(Field.OBJECT_URN_FIELD, tagObjectUrn);
                    interaction.put(Field.ENTITY_REFERENCE_TYPE, EntityReferenceType.Object);
                    interaction.put(Field.REFERENCE_URN_FIELD, antennaUrn);
                    interaction.put(Field.TYPE_FIELD, type);
                    interaction.put(Field.RECORDED_TIMESTAMP_FIELD, System.currentTimeMillis());
                    // IObjectInteraction interaction = new InteractionBuilder(System.currentTimeMillis())
                    // .setObjectInteractionSession(null)
                    // .setType("read")
                    // .setEntityReferenceType(EntityReferenceType.Object)
                    // .setReferenceUrn(antennaUrn.toString())
                    // .setObject(new ObjectBuilder(tagObjectUrn).setType("tag").build())
                    // .build();

                    try
                    {
                        final ResponseEntity interactionResponse = interactionClient.create(interaction);
                        interactionResponse.log();

                        final String interactionUrn = interactionResponse.getMessage();
                        LOG.info("New Interaction has URN of {}", interactionUrn);

                        interactionsMade++;
                        LOG.debug("Created a total of {} out of {} interactions!", interactionsMade, totalToMake);

                        // Every interaction has 3 meatadata entries:
                        try
                        {
                            final IMetadata dateTimeMetadata = new MetadataBuilder(MetadataDataType.StringType)
                                    .setReferenceUrn(interactionUrn)
                                    .setEntityReferenceType(EntityReferenceType.ObjectInteraction)
                                    .setKey("date")
                                    .setValue("08-25-2015")
                                    .build();
                            metadataClient.upsert(dateTimeMetadata);
                        } catch (ServiceException e)
                        {
                            LOG.debug(e.getMessage(), e);
                            LOG.info("Failed to INSERT, might have already existed.");
                        }

                        try
                        {
                            final IMetadata hourMetadata = new MetadataBuilder(MetadataDataType.IntegerType)
                                    .setReferenceUrn(interactionUrn)
                                    .setEntityReferenceType(EntityReferenceType.ObjectInteraction)
                                    .setKey("hour")
                                    .setValue("5")
                                    .build();

                            metadataClient.upsert(hourMetadata);
                        } catch (ServiceException e)
                        {
                            LOG.debug(e.getMessage(), e);
                            LOG.info("Failed to INSERT, might have already existed.");
                        }

                        try
                        {
                            final IMetadata minuteMetadata = new MetadataBuilder(MetadataDataType.IntegerType)
                                    .setReferenceUrn(interactionUrn)
                                    .setEntityReferenceType(EntityReferenceType.ObjectInteraction)
                                    .setKey("minute")
                                    .setValue(
                                            "30")
                                    .build();
                            metadataClient.upsert(minuteMetadata);
                        } catch (ServiceException e)
                        {
                            LOG.debug(e.getMessage(), e);
                            LOG.info("Failed to INSERT, might have already existed.");
                        }
                    } catch (Exception e)
                    {
                        LOG.error("Error creating interaction: {}", e.getMessage());
                        LOG.debug(e.getMessage(), e);
                        errorCount++;
                    }

                }
            }
        }
        LOG.info("Completed making a total of {} stores, " +
                "{} antennas per store, {} tags, and {} interactions.  {} errors occurred during the process.",
                createdStores, createdAntenna, createdTags, interactionsMade, errorCount);
    }

    @Test
    public void populateStore() throws Exception
    {

        if (createUser)
        {
            createUser();
        }
        makeContext();
        createStores();

        createTags();

        makeInteractions();

    }

}
