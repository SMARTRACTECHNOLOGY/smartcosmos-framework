

package net.smartcosmos.profiles.builder;

import com.google.common.base.Preconditions;
import net.smartcosmos.builder.AbstractBuilder;
import net.smartcosmos.profiles.model.context.IVerifyMessage;
import net.smartcosmos.profiles.pojo.context.VerifyMessage;

public class VerifyMessageBuilder extends AbstractBuilder<IVerifyMessage>
{
    public VerifyMessageBuilder(String verificationType)
    {
        super(new VerifyMessage());

        Preconditions.checkNotNull(verificationType);
        instance.setVerificationType(verificationType);
    }

    VerifyMessageBuilder setVerificationState(int verificationState)
    {
        Preconditions.checkArgument(verificationState >= 0, "verification state must be >= 0");
        instance.setVerificationState(verificationState);
        return this;
    }
}

