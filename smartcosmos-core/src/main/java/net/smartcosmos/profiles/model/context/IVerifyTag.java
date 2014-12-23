

package net.smartcosmos.profiles.model.context;

import java.util.Collection;

public interface IVerifyTag
{

    Collection<String> getTagIdentifiers();

    void setTagIdentifiers(Collection<String> tagIdentifiers);

    String getVerificationType();

    void setVerificationType(String verificationType);
}
