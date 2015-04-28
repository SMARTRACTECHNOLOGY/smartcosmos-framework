package net.smartcosmos.platform.api.batch;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Server API
 * ===============================================================================
 * Copyright (C) 2014 SMARTRAC Technology Fletcher, Inc.
 * ===============================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#
 */

/**
 * Represents the JSON defined in the message body of the {@link net.smartcosmos.platform.api.service.IQueueService}
 * that completely defines the batch file uploaded.
 */
public interface IBatchPackageDefinition
{
    /**
     * Size (in bytes) of the file being transmitted.
     *
     * @return file size, in bytes
     */
    int getFileContentLength();

    /**
     * Size (in bytes) of the file being transmitted.
     *
     * @param fileContentLength file size, in bytes
     */
    void setFileContentLength(int fileContentLength);

    /**
     * Base64 encoded, 128-bit MD5 digest of the file per RFC1864.
     *
     * @return Base64 encoded 128-bit MD5 digest of the file being transmitted
     */
    String getFileMd5Checksum();

    /**
     * Base64 encoded, 128-bit MD5 digest of the file per RFC1864.
     *
     * @param md5Checksum Base64 encoded 128-bit MD5 digest of the file being transmitted
     */
    void setFileMd5Checksum(String md5Checksum);

    /**
     * MIME type describing the format of the file being uploaded, e.g. {@code text/xml}.
     *
     * @return standard MIME type of the file being uploaded
     */
    String getMimeType();

    /**
     * MIME type describing the format of the file being uploaded, e.g. {@code text/xml}.
     *
     * @param mimeType standard MIME type of the file being uploaded
     */
    void setMimeType(String mimeType);

    /**
     * Identifies an internal, previously configured, routing URN within the server. Routing URNs identify a controller
     * class that has <i>a priori</i> knowledge of how to process and handle the data contained within the file being
     * uploaded.
     *
     * @return Previously configured routing URN
     */
    String getRoutingUrn();

    /**
     * Identifies an internal, previously configured, routing URN within the server. Routing URNs identify a controller
     * class that has <i>a priori</i> knowledge of how to process and handle the data contained within the file being
     * uploaded.
     *
     * @param routingUrn Previously configured routing URN
     */
    void setRoutingUrn(String routingUrn);

    /**
     * System assigned name that the file must be given during transmission, assuring no name collisions will result if
     * the same file is transmitted multiple times (intentionally or on accident).
     *
     * @return System assigned name that the file must use during transmission
     */
    String getTransmissionUrn();

    void setTransmissionUrn(String transmissionUrn);

    /**
     * Name of the bucket where the batch file was uploaded.
     *
     * @return bucket name
     */
    String getBucketName();

    void setBucketName(String bucketName);

    /**
     * Name of the file inside of the bucket where the batch file was uploaded.
     *
     * @return object key
     */
    String getObjectKey();

    void setObjectKey(String objectKey);
}
