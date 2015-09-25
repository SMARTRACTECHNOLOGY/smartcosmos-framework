package ${package}.smartcosmos.extension.visitor;

import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.platform.api.visitor.VisitableObject;
import net.smartcosmos.platform.base.AbstractVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class HappyBirthdayRandyWestonObjectVisitorService extends AbstractVisitor<VisitableObject>
{

    private static final Logger LOG = LoggerFactory.getLogger(HappyBirthdayRandyWestonObjectVisitorService.class);

    private Boolean onlyRandysBirthday;

    public HappyBirthdayRandyWestonObjectVisitorService()
    {
        // NOTE TO EXTENSION DEVELOPER:
        // Notice that the last argument of the super constructor is present in HappyBirthdayKeithRichards,
        // but is not in HappyBirthdayRandyWeston. HappyBirthdayRandyWeston will receive the default priority
        // of 50 (the range for priorities is 0 <= priority <= 100).
        //
        // Note also that the priority stored in the visitors registration table itself will not be this number,
        // but rather a much larger number that preserves your ordering and ensures that no two visitors have the
        // same priority. If you assign two visitor with the same entity type the same priority, the order in which
        // their visit() methods are executed will be random. If you need visitors to a particular entity type to
        // execute in a particular order, assign them priorites.
        //
        // NOTE TO EXTENSION DEVELOPER:
        // The UUID that constitutes the first argument of this constructor has to be unique. Don't use this string!
        // Generate your own with:
        // java.util.UUID.getRandomUUID().toString()
        // and replace this string with that.
        //
        super("9D87E521-B5A4-4136-B42D-75A19A4C4ABD", "Happy Birthday Randy Weston Object Visitor Service",
              EntityReferenceType.Object, 90);

        onlyRandysBirthday = false;

    }

    public void visit(VisitableObject object)
    {

        Calendar today = new GregorianCalendar();

        if (onlyRandysBirthday && (today.DAY_OF_MONTH != 18 || today.MONTH != Calendar.DECEMBER))
        {
            LOG.info("No present for Randy Weston today!");
            return;
        }

        // either it's Keith's birthday or onlyKeefsBirthday is set to false
        LOG.info("Happy birthday to Randy Weston from object " + object.getUrn() +
                 " with name " + object.getName() + " and objectUrn " + object.getObjectUrn() +
                 " and type " + object.getType() + " and moniker " + object.getMoniker());
    }

}
