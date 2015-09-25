package ${package}.smartcosmos.extension.server.resource.secure.transaction.handlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.smartcosmos.Field;
import net.smartcosmos.objects.model.context.IObject;
import net.smartcosmos.objects.pojo.context.ObjectImpl;
import net.smartcosmos.platform.api.authentication.IAuthenticatedUser;
import net.smartcosmos.platform.api.dao.IObjectDAO;
import net.smartcosmos.platform.base.AbstractTransactionHandler;
import net.smartcosmos.platform.util.TransactionException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

// Transaction handlers exist for processing large volumes of input data within a single database transaction.
// notes here re: no repsonse is returned from a transaction handler @UnitOfWork annotation
//
public class ExampleTransactionHandler extends AbstractTransactionHandler

{

    private static final Logger LOG = LoggerFactory.getLogger(ExampleTransactionHandler.class);

    public ExampleTransactionHandler()
    {
        // NOTE TO EXTENSION DEVELOPER:
        // The serviceId has to be a unique UUID. Don't use this string!
        // Generate your own with:
        // java.util.UUID.getRandomUUID().toString()
        // and replace this string with that.
        this.name = "Example Transaction Handler";
        this.serviceId = "13106f3b-898c-4b69-a578-f89915e69735";
    }

    @Override
    public void initialize()
    {

    }

    // The SMART COSMOS platform generates the transaction UUID (txUuid), and in the event of a successful transaction
    // being committed, the response JSON will look like:
    //
    //{
    //    "code": 1,
    //    "message": "11e5604e-8ffa-9df2-a141-1df768b92a30"
    //}
    //
    // where the message string it the transaction UUID. For an example of a JSON file that this handler would
    // process, see:
    // http://smartractechnology.atlassian.net/wiki/display/SCKB/Creating+and+Using+Transaction+Handlers+in+SMART+COSMOS
    //
    @Override
    public void run(String fatJson, IAuthenticatedUser authenticatedUser, String txUuid) throws TransactionException
    {
        IObjectDAO objectDao = context.getDAOFactory().getObjectDAO();
        Session currentSession = context.getSessionFactory().getCurrentSession();
        ObjectMapper objectMapper = new ObjectMapper();

        int objectCount = 0;
        LOG.info("starting transaction " + txUuid);

        try
        {
            JsonNode root = objectMapper.readTree(fatJson);
            if (!root.has("objects"))
            {
                throw new TransactionException("root JSON contains no objects element");
            }

            JsonNode objects = root.get("objects");
            if (!objects.isArray())
            {
                throw new TransactionException("root JSON's objects element is not an array");
            }

            int objectsCount = 0;

            for (JsonNode jsonNode: objects)
            {
                objectsCount++;
                IObject daoObject = new ObjectImpl();
                daoObject.setAccount(authenticatedUser.getAccount());
                // active flag defaults to true unless explicitly set otherwise in JSON body
                if (jsonNode.has(Field.ACTIVE_FIELD) && jsonNode.get(Field.ACTIVE_FIELD).asBoolean())
                {
                    daoObject.setActive(false);
                } else
                {
                    daoObject.setActive(true);
                }
                // objectUrn is a required field
                if (jsonNode.has(Field.OBJECT_URN_FIELD))
                {
                    daoObject.setObjectUrn(jsonNode.get(Field.OBJECT_URN_FIELD).asText());
                } else
                {
                    throw new TransactionException(Field.OBJECT_URN_FIELD + " is a required field for objects");
                }
                // type is a required field
                if (jsonNode.has(Field.TYPE_FIELD))
                {
                    daoObject.setType(jsonNode.get(Field.TYPE_FIELD).asText());
                } else
                {
                    throw new TransactionException(Field.TYPE_FIELD + " is a required field for objects");
                }
                // name is a required field
                if (jsonNode.has(Field.NAME_FIELD))
                {
                    daoObject.setName(jsonNode.get(Field.NAME_FIELD).asText());
                } else
                {
                    throw new TransactionException(Field.NAME_FIELD + " is a required field for objects");
                }
                // moniker is not required; if it's set in the incoming JSON we set it, otherwise we leave it alone
                if (jsonNode.has(Field.MONIKER_FIELD))
                {
                    daoObject.setMoniker(jsonNode.get(Field.MONIKER_FIELD).asText());
                }
                // description is not required; if it's set in the incoming JSON we set it, otherwise we leave it alone
                if (jsonNode.has(Field.DESCRIPTION_FIELD))
                {
                    daoObject.setDescription(jsonNode.get(Field.DESCRIPTION_FIELD).asText());
                }
                objectDao.upsert(daoObject);
                objectCount++;
            }
            LOG.info("Transaction " + txUuid + " completed processing of " + objectCount + " objects.");

            currentSession.flush();
            currentSession.clear();

        } catch (IOException | TransactionException  e)
        {
            LOG.error("Transaction " + txUuid + " had an error at object " + objectCount + ".");
            throw new TransactionException("transaction exception resulting from " + e.getMessage());
        }
    }
}
