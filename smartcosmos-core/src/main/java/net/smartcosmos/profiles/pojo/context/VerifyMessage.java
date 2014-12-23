package net.smartcosmos.profiles.pojo.context;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.base.Preconditions;
import net.smartcosmos.profiles.model.context.IVerifyMessage;
import net.smartcosmos.util.json.JsonGenerationView;

public class VerifyMessage implements IVerifyMessage
{
    @JsonView(JsonGenerationView.Minimum.class)
    private String verificationType;

    @JsonView(JsonGenerationView.Minimum.class)
    private int verificationState = 0;

    @Override
    public String getVerificationType()
    {
        return verificationType;
    }

    @Override
    public void setVerificationType(String verificationType)
    {
        Preconditions.checkNotNull(verificationType);
        this.verificationType = verificationType;
    }

    @Override
    public int getVerificationState()
    {
        return verificationState;
    }

    @Override
    public void setVerificationState(int verificationState)
    {
        Preconditions.checkArgument(verificationState >= 0);
        this.verificationState = verificationState;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VerifyMessage that = (VerifyMessage) o;

        if (verificationState != that.verificationState) return false;
        if (!verificationType.equals(that.verificationType)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = verificationType.hashCode();
        result = 31 * result + verificationState;
        return result;
    }
}
