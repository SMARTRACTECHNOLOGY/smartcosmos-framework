package ${package}.visitor;

import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.objects.model.context.IObject;
import net.smartcosmos.platform.api.dao.IObjectDAO;
import net.smartcosmos.platform.api.visitor.VisitableMetadata;
import net.smartcosmos.platform.base.AbstractVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class HappyNewYearMetadataVisitorService extends AbstractVisitor<VisitableMetadata>
{

    private static final Logger LOG = LoggerFactory.getLogger(HappyNewYearMetadataVisitorService.class);

    private Boolean onlyNewYear;

    private Boolean contextInitialized = false;

    private IObjectDAO objectDAO;

    public HappyNewYearMetadataVisitorService()
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
        super("3D734CAE-933B-457A-AF90-203605619579", "Happy New Year Metadata Visitor Service",
              EntityReferenceType.Metadata);

        onlyNewYear = false;

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

        if (onlyNewYear && (today.DAY_OF_MONTH != 1 || today.MONTH != Calendar.JANUARY))
        {
            LOG.info("No champagne for you today!");
            return;
        }
        // either it's New Year's Day, or onlyNewYear is set to false
        LOG.info("Happy New Year from metadata item " + metadata.getUrn() +
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
