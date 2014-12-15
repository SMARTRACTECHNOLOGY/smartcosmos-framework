package net.smartcosmos.builder;

import com.google.common.base.Preconditions;
import net.smartcosmos.model.batch.IBatchTransmission;
import net.smartcosmos.model.batch.TransmissionResultType;
import net.smartcosmos.pojo.batch.BatchTransmission;

public final class FileTransmissionReceiptBuilder extends AbstractBuilder<IBatchTransmission>
{
    public FileTransmissionReceiptBuilder(String transmissionUrn)
    {
        super(new BatchTransmission());
        Preconditions.checkNotNull(transmissionUrn, "transmissionUrn must not be null");
        this.instance.setTransmissionUrn(transmissionUrn);
    }

    public FileTransmissionReceiptBuilder set(TransmissionResultType transmissionResultType)
    {
        instance.setTransmissionResult(transmissionResultType);
        return this;
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getTransmissionResult(), "transmissionResultType must not be null");
    }
}

