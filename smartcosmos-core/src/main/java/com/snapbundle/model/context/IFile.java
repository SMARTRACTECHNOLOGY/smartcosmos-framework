/*
 * SnapBundle™ SDK
 * (C) Copyright 2013 Tag Dynamics, LLC (http://tagdynamics.com/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.snapbundle.model.context;

import com.snapbundle.model.base.IAccountDomainResource;
import com.snapbundle.model.base.IReferentialObject;

public interface IFile extends IAccountDomainResource<IFile>, IReferentialObject
{
    IUser getUser();

    void setUser(IUser user);

    long getTimestamp();

    void setTimestamp(long timestamp);

    boolean isPending();

    void setPending(boolean flag);

    String getFileName();

    void setFileName(String fileName);

    String getUrl();

    void setUrl(String url);

    String getMimeType();

    void setMimeType(String mimeType);

    void setContentHash(String contentHash);

    String getContentHash();

    void copy(IFile file);
}
