package ${package}.smartcosmos.extension.visitor;

import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.objects.model.context.IObject;
import net.smartcosmos.platform.api.dao.IObjectDAO;
import net.smartcosmos.platform.api.visitor.VisitableMetadata;
import net.smartcosmos.platform.base.AbstractVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MerryChristmasMetadataVisitorService extends AbstractVisitor<VisitableMetadata>
{

    private static final Logger LOG = LoggerFactory.getLogger(MerryChristmasMetadataVisitorService.class);

    private Boolean onlyChristmas;

    private Boolean contextInitialized = false;

    private IObjectDAO objectDAO;

    public MerryChristmasMetadataVisitorService()
    {
        // NOTE TO EXTENSION DEVELOPER:
        // Note the absence of a priority argument in the super constructor. The priority for this
        // visitor will be set to 50, i.e.,
        // net.smartcosmos.platform.api.visitor.DEFAULT_PRIORITY
        //
        // See the two ObjectVisitorService.java files in this directory for more on priorities.
        //

        // NOTE TO EXTENSION DEVELOPER:
        // The UUID that constitutes the first argument of this constructor has to be unique. Don't use this string!
        // Generate your own with:
        // java.util.UUID.getRandomUUID().toString()
        // and replace this string with that.
        super("3D734CAE-933B-457A-AF90-203605619579", "Merry Christmas Metadata Visitor Service",
              EntityReferenceType.Metadata);

        onlyChristmas = false;

    }

    @Override
    public void visit(VisitableMetadata metadata)
    {
        // NOTE TO EXTENSION DEVELOPER:
        // I do this here because I don't have the application context until after the super constructor is called
        //
        if (!contextInitialized)
        {
            initializeContext();
        }

        Calendar today = new GregorianCalendar();

        if (onlyChristmas && (today.DAY_OF_MONTH != 25 || today.MONTH != Calendar.DECEMBER))
        {
            LOG.info("No present for you today!");
            return;
        }
        // either it's Christmas, or onlyChristmas is set to false
        LOG.info("Merry Christmas from metadata item " + metadata.getUrn() +
                 " with key " + metadata.getKey() +
                 " and raw value " + metadata.getRawValue() +
                 ", associated with reference entity: " + metadata.getReferenceUrn() +
                 " of type " + metadata.getEntityReferenceType().name());

        // NOTE TO EXTENSION DEVELOPER:
        // If I need to find related entities (as in this example, where, if the reference entity is an object,
        // I want the reference entity itself, so I can display some info about it), I can:
        //
        // 1. get the necessary DAOs from the context
        // 2. get the account from the entity passed into the visit() method we're in now
        // 3. exercise whatever functionality the DAO offers
        //
        if (metadata.getEntityReferenceType() == EntityReferenceType.Object)
        {
            IObject referenceObject = objectDAO.findByUrn(IObject.class, metadata.getReferenceUrn(),
                                                          metadata.getAccount());
            LOG.info("More on the reference entity for this metadata item: it's an Object with" +
                     " name " + referenceObject.getName() + " and objectUrn " + referenceObject.getObjectUrn() +
                     " and type " + referenceObject.getType());
        }

    }

    private void initializeContext()
    {
        objectDAO = context.getDAOFactory().getObjectDAO();
        contextInitialized = true;
    }
}
