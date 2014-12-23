package net.smartcosmos.builder;

import com.google.common.base.Preconditions;
import net.smartcosmos.model.batch.IBatchTransmissionRequest;
import net.smartcosmos.pojo.batch.BatchTransmissionRequest;

public class BatchTransmissionRequestBuilder extends AbstractBuilder<IBatchTransmissionRequest>
{
    public BatchTransmissionRequestBuilder(String routingUrn)
    {
        super(new BatchTransmissionRequest());
        Preconditions.checkNotNull(routingUrn, "routingUrn must not be null");
        this.instance.setRoutingUrn(routingUrn);
    }

    public BatchTransmissionRequestBuilder setFileContentType(String contentType)
    {
        instance.setMimeType(contentType);
        return this;
    }

    public BatchTransmissionRequestBuilder setFileContentLength(int contentLength)
    {
        instance.setFileContentLength(contentLength);
        return this;
    }

    public BatchTransmissionRequestBuilder setFileMd5Checksum(String md5Checksum)
    {
        instance.setFileMd5Checksum(md5Checksum);
        return this;
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getFileContentLength(), "file content length must not be null");
        Preconditions.checkArgument(instance.getFileContentLength() > 0, "file content length must be > 0");
        Preconditions.checkNotNull(instance.getMimeType(), "file content type must not be null");
        Preconditions.checkNotNull(instance.getFileMd5Checksum(), "MD5 checksum must not be null");
    }
}
