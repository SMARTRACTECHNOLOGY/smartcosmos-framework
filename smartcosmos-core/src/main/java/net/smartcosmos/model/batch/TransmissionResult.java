package net.smartcosmos.model.batch;

public enum TransmissionResult
{
    /**
     * File has been successfully transmitted to the S3 bucket.
     */
    UploadComplete,

    /**
     * There was an unexpected error uploading the file. The client may decide to retry the transmission at a
     * future date and time; transmission URN should remain open.
     */
    Error,

    /**
     * Client decided to abort the file transmission; no retry will be attempted; transmission URN should be
     * permanently closed to prevent any further upload attempts.
     */
    Aborted
}
