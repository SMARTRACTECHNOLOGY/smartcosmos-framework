package ${package}.jpa;

import net.smartcosmos.model.base.IDomainResource;

// NOTE TO EXTENSION DEVELOPER:
// Observe that the only difference between this interface and the IMoreInterestingExampleEntity
// interface are the interfaces being extended.
//
// The resulting behavioral differences, though, are significant: with ExampleEntity you get
// two strings and (from the parent classes) a unique ID, and account, and a moniker field,
// but it's just another thing in the database. With MoreInterestingExampleEntity, you can
// expressly relate this thing in the database to any other entity in the system via the
// referenceUrn and entityReferenceType field.
//
public interface IExampleEntity extends IDomainResource<IExampleEntity>
{

    String getFirstString();
    void setFirstString(String firstString);

    String getSecondString();
    void setSecondString(String secondString);
}
