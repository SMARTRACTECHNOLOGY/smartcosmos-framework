package net.smartcosmos.objects.model.context;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Core SDK
 * ===============================================================================
 * Copyright (C) 2013 - 2014 SMARTRAC Technology Fletcher, Inc.
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

import net.smartcosmos.model.base.IAccountDomainResource;
import net.smartcosmos.model.base.IReferentialObject;

/**
 * Represents any type of multimedia file that is explicitly associated with a single
 * {@link net.smartcosmos.model.base.IReferentialObject}. If a file needs to be shared
 * with multiple objects, developers should design and implement a novel scheme using
 * {@link IRelationship} definitions (recommended) or
 * {@link net.smartcosmos.model.context.IMetadata} (alternative).
 */
public interface IFile extends IAccountDomainResource<IFile>, IReferentialObject
{
    long getTimestamp();

    void setTimestamp(long timestamp);

    /**
     * Indicates if the file is defined in the data store but not yet physically
     * uploaded to the file store. Extensions can easily determine if the file's actual
     * content is available by watching for {@link net.smartcosmos.model.event.EventType#FileUploaded}
     *
     * @return true, if only the data store record exists and the file contents are not yet available
     * @see net.smartcosmos.model.event.EventType#FileDefined
     * @see net.smartcosmos.model.event.EventType#FileUploaded
     */
    boolean isPending();

    void setPending(boolean flag);

    String getFileName();

    void setFileName(String fileName);

    /**
     * Private, internal, secured URL that callers generally do not have permissions
     * to directly access. The URL stored here is served up indirectly through the appropriate
     * SMART COSMOS Objects file endpoint.
     *
     * @return Internal, private, secure, and typically inaccessible internally managed URL
     * depicting where the file is stored
     */
    String getUrl();

    void setUrl(String url);

    String getMimeType();

    void setMimeType(String mimeType);

    void setDigitalSignature(String contentHash);

    /**
     * JSON structure that represents two distinct digital signatures, a signature of the actual file contents
     * upon submission to the platform and a second signature of the metadata used to describe the file definition
     * (including the digital signature of the file itself).
     * <p/>
     * The JSON digital signature block also includes narrative on how the digital signatures were generated, including
     * algorithm, iterations, and the actual library used. A prototypical example of a digital signature JSON block is
     * below for reference:
     * <p/>
     * <code>{<br/>
     * "algorithm": "SHA-256",<br/>
     * "description": "The contentsSignature is of the file contents exclusively. The root signature is of the
     * signedBody exclusively",<br/>
     * "iterations": 1,<br/>
     * "library": "Apache Shiro (Java)",<br/>
     * "signature": "8ae65ce110ab15fbea8df6e61adedaf9ca36dc02ebcd5d451da0f7d18a790abe",<br/>
     * "signedBody":<br/>
     * &nbsp;&nbsp;{<br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;"accountUrn": "urn:uuid:9728d95e-9ae5-482f-84a7-b8f1fce80389",<br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;"contentsSignature": "8F866F53645218F77957675EC197678F2F9D3155455A01FF6F4E6FF68C5696C7",
     * <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;"entityReferenceType": "ObjectInteraction",<br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;"fileUrn": "urn:uuid:5655cf2d-a843-4d7b-941d-c3abe70a007b",<br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;"referenceUrn": "urn:uuid:6889da4e-7fed-49b5-821b-b5a9f06740d4",<br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;"url": "https://net.smartcosmos.objects.stage.s3.amazonaws.blahblahblahbah/file.mp4"<br/>
     * &nbsp;&nbsp;}<br/>
     * }</code><br/>
     *
     * @return JSON structure describing the digital signature
     */
    String getDigitalSignature();
}
