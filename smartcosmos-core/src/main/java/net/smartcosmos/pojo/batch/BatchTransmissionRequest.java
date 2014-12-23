package net.smartcosmos.pojo.batch;

import com.fasterxml.jackson.annotation.JsonView;
import net.smartcosmos.model.batch.IBatchTransmissionRequest;
import net.smartcosmos.pojo.base.DomainResource;
import net.smartcosmos.util.json.JsonGenerationView;

public class BatchTransmissionRequest extends DomainResource<IBatchTransmissionRequest> implements IBatchTransmissionRequest
{
    @JsonView(JsonGenerationView.Minimum.class)
    private String md5Checksum;

    @JsonView(JsonGenerationView.Minimum.class)
    private String mimeType;

    @JsonView(JsonGenerationView.Minimum.class)
    private String routingUrn;

    @JsonView(JsonGenerationView.Minimum.class)
    private int contentLength;

    @Override
    public int getFileContentLength()
    {
        return contentLength;
    }

    @Override
    public void setFileContentLength(int contentLength)
    {
        this.contentLength = contentLength;
    }

    @Override
    public String getFileMd5Checksum()
    {
        return md5Checksum;
    }

    @Override
    public void setFileMd5Checksum(String md5Checksum)
    {
        this.md5Checksum = md5Checksum;
    }

    @Override
    public String getMimeType()
    {
        return mimeType;
    }

    @Override
    public void setMimeType(String mimeType)
    {
        this.mimeType = mimeType;
    }

    @Override
    public String getRoutingUrn()
    {
        return routingUrn;
    }

    @Override
    public void setRoutingUrn(String routingUrn)
    {
        this.routingUrn = routingUrn;
    }
}
