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

package com.snapbundle.model.context;

import com.snapbundle.model.base.IAccountDomainResource;
import com.snapbundle.model.base.IReferentialObject;

/**
 * Captures a {@link com.snapbundle.model.context.ITag} assignment with a specific
 * {@link com.snapbundle.model.base.IReferentialObject} within a specific account context.
 */
public interface ITagAssignment extends IAccountDomainResource<ITagAssignment>, IReferentialObject
{
    ITag getTag();

    void setTag(ITag tag);
}
