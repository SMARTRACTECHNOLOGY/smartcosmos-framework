package net.smartcosmos.builder;

import com.google.common.base.Preconditions;
import net.smartcosmos.model.batch.IFileTransmissionRequest;
import net.smartcosmos.pojo.batch.FileTransmissionRequest;

public class FileTransmissionRequestBuilder extends AbstractBuilder<IFileTransmissionRequest>
{
    public FileTransmissionRequestBuilder(String routingUrn)
    {
        super(new FileTransmissionRequest());
        Preconditions.checkNotNull(routingUrn, "routingUrn must not be null");
        this.instance.setRoutingUrn(routingUrn);
    }

    public FileTransmissionRequestBuilder setFileContentType(String contentType)
    {
        instance.setFileContentType(contentType);
        return this;
    }

    public FileTransmissionRequestBuilder setFileContentLength(Long contentLength)
    {
        instance.setFileContentLength(contentLength);
        return this;
    }

    public FileTransmissionRequestBuilder setFileMd5Checksum(String md5Checksum)
    {
        instance.setFileMd5Checksum(md5Checksum);
        return this;
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getFileContentLength(), "file content length must not be null");
        Preconditions.checkArgument(instance.getFileContentLength() > 0, "file content length must be > 0");
        Preconditions.checkNotNull(instance.getFileContentType(), "file content type must not be null");
        Preconditions.checkNotNull(instance.getFileMd5Checksum(), "MD5 checksum must not be null");
    }
}
