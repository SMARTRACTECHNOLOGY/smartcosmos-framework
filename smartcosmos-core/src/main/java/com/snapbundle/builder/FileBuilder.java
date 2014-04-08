/*
 * SnapBundle SDK
 * (C) Copyright 2013-2014 Tag Dynamics, LLC (http://tagdynamics.com/)
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

package com.snapbundle.builder;

import com.google.common.base.Preconditions;
import com.snapbundle.model.context.IFile;
import com.snapbundle.pojo.context.File;

/**
 * Convenience Builder pattern class for creating new {@link com.snapbundle.model.context.IFile} instances.
 * <p/>
 * The minimum fields required to define a new instance are:
 * <ul>
 * <li>{@link com.snapbundle.Field#ENTITY_REFERENCE_TYPE}</li>
 * <li>{@link com.snapbundle.Field#REFERENCE_URN_FIELD}</li>
 * <li>{@link com.snapbundle.Field#MIME_TYPE_FIELD}</li>
 * </ul>
 */
public final class FileBuilder extends AbstractReferentialBuilder<IFile, FileBuilder>
{
    public FileBuilder(String mimeType)
    {
        super(new File());
        instance.setMimeType(mimeType);
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getReferenceUrn(), "Reference Urn must not be null");
        Preconditions.checkNotNull(instance.getEntityReferenceType(), "Entity Reference Type must not be null");
        Preconditions.checkNotNull(instance.getMimeType(), "MIME type must not be null");
    }
}
