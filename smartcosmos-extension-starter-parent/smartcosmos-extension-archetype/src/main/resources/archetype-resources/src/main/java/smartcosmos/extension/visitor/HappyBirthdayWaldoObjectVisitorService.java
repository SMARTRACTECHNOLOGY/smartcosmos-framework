package ${package}.smartcosmos.extension.visitor;

import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.platform.api.visitor.VisitableObject;
import net.smartcosmos.platform.base.AbstractVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class HappyBirthdayWaldoObjectVisitorService extends AbstractVisitor<VisitableObject>
{

    private static final Logger LOG = LoggerFactory.getLogger(HappyBirthdayWaldoObjectVisitorService.class);

    private Boolean onlyWaldoBirthday;

    public HappyBirthdayWaldoObjectVisitorService()
    {
        // NOTE TO EXTENSION DEVELOPER:
        // Notice that the last argument of the super constructor is present in HappyBirthdayFred.java,
        // but is not in HappyBirthdayWaldo. HappyBirthdayWaldo will receive the default priority
        // of 50 (the range for priorities is 0 <= priority <= 100).
        //
        // Note also that the priority stored in the visitors registration table itself will not be this number,
        // but rather a much larger number that preserves your ordering and ensures that no two visitors have the
        // same priority. If you assign two visitors with the same entity type the same priority, the order in which
        // their visit() methods are executed will be indeterminate. If you need visitors to a particular entity type
        // to execute in a particular order, assign them priorites.
        //
        // NOTE TO EXTENSION DEVELOPER:
        // The UUID that constitutes the first argument of this constructor has to be unique. Don't use this string!
        // Generate your own with:
        // java.util.UUID.getRandomUUID().toString()
        // and replace this string with that.
        //
        super("9D87E521-B5A4-4136-B42D-75A19A4C4ABD", "Happy Birthday Waldo Object Visitor Service",
              EntityReferenceType.Object);

        onlyWaldoBirthday = false;

    }

    public void visit(VisitableObject object)
    {

        Calendar today = new GregorianCalendar();

        if (onlyWaldoBirthday && (today.DAY_OF_MONTH != 16 || today.MONTH != Calendar.APRIL))
        {
            LOG.info("No present for Waldo today!");
            return;
        }

        // either it's Waldo' birthday or onlyWaldoBirthday is set to false
        LOG.info("Happy birthday to Waldo from object " + object.getUrn() +
                 " with name " + object.getName() + " and objectUrn " + object.getObjectUrn() +
                 " and type " + object.getType() + " and moniker " + object.getMoniker());
    }

}
