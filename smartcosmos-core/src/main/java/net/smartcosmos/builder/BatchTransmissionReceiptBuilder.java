package net.smartcosmos.builder;

import com.google.common.base.Preconditions;
import net.smartcosmos.model.batch.IBatchTransmissionReceipt;
import net.smartcosmos.model.batch.TransmissionResultType;
import net.smartcosmos.pojo.batch.BatchTransmissionReceipt;

public final class BatchTransmissionReceiptBuilder extends AbstractBuilder<IBatchTransmissionReceipt>
{
    public BatchTransmissionReceiptBuilder(String transmissionUrn)
    {
        super(new BatchTransmissionReceipt());
        Preconditions.checkNotNull(transmissionUrn, "transmissionUrn must not be null");
        this.instance.setTransmissionUrn(transmissionUrn);
    }

    public BatchTransmissionReceiptBuilder setTransmissionResult(TransmissionResultType transmissionResultType)
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

