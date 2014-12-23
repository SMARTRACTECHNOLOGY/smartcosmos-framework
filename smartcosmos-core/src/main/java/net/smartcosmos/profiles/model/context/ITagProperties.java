package net.smartcosmos.profiles.model.context;

import java.util.Collection;

public interface ITagProperties
{
    Collection<String> getTagIdentifiers();

    void setTagIdentifiers(Collection<String> tagIdentifiers);

    Collection<String> getVerificationTypes();

    void setVerificationTypes(Collection<String> verificationTypes);

    Collection<String> getPropertyNames();

    void setPropertyNames(Collection<String> propertyNames);
}
