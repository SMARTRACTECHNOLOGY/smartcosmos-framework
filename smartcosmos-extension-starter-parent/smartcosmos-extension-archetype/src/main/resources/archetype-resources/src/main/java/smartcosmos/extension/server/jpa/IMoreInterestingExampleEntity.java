package ${package}.smartcosmos.extension.server.jpa;

import net.smartcosmos.model.base.IAccountDomainResource;
import net.smartcosmos.model.base.IReferentialObject;

// NOTE TO EXTENSION DEVELOPER:
// Observe that the only difference between this interface and the IExampleEntity interface
// are the interfaces being extended.
//
// The resulting behavioral differences, though, are significant: with ExampleEntity you get
// two strings and (from the parent classes) a unique ID, and account, and a moniker field,
// but it's just another thing in the database. With MoreInterestingExampleEntity, you can
// expressly relate this thing in the database to any other entity in the system via the
// referenceUrn and entityReferenceType field.
//
public interface IMoreInterestingExampleEntity
        extends IAccountDomainResource<IMoreInterestingExampleEntity>, IReferentialObject
{

    String getFirstString();
    void setFirstString(String string);

    String getSecondString();
    void setSecondString(String string);
}
