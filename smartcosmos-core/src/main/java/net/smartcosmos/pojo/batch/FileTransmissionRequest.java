package net.smartcosmos.pojo.batch;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.base.Preconditions;
import net.smartcosmos.model.batch.IFileTransmissionRequest;
import net.smartcosmos.pojo.base.DomainResource;
import net.smartcosmos.util.json.JsonGenerationView;

public class FileTransmissionRequest extends DomainResource<IFileTransmissionRequest> implements IFileTransmissionRequest
{
    @JsonView(JsonGenerationView.Minimum.class)
    private String md5Checksum;

    @JsonView(JsonGenerationView.Minimum.class)
    private String contentType;

    @JsonView(JsonGenerationView.Minimum.class)
    private String routingUrn;

    @JsonView(JsonGenerationView.Minimum.class)
    private long contentLength;

    @Override
    public long getFileContentLength()
    {
        return contentLength;
    }

    @Override
    public void setFileContentLength(long contentLength)
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
        Preconditions.checkNotNull(routingUrn, "md5Checksum must not be null");
        this.md5Checksum = md5Checksum;
    }

    @Override
    public String getFileContentType()
    {
        return contentType;
    }

    @Override
    public void setFileContentType(String contentType)
    {
        Preconditions.checkNotNull(routingUrn, "contentType must not be null");
        this.contentType = contentType;
    }

    @Override
    public String getRoutingUrn()
    {
        return routingUrn;
    }

    @Override
    public void setRoutingUrn(String routingUrn)
    {
        Preconditions.checkNotNull(routingUrn, "routingUrn must not be null");
        this.routingUrn = routingUrn;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        FileTransmissionRequest that = (FileTransmissionRequest) o;

        if (contentLength != that.contentLength) return false;
        if (contentType != null ? !contentType.equals(that.contentType) : that.contentType != null) return false;
        if (md5Checksum != null ? !md5Checksum.equals(that.md5Checksum) : that.md5Checksum != null) return false;
        if (routingUrn != null ? !routingUrn.equals(that.routingUrn) : that.routingUrn != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + (md5Checksum != null ? md5Checksum.hashCode() : 0);
        result = 31 * result + (contentType != null ? contentType.hashCode() : 0);
        result = 31 * result + (routingUrn != null ? routingUrn.hashCode() : 0);
        result = 31 * result + (int) (contentLength ^ (contentLength >>> 32));
        return result;
    }
}
