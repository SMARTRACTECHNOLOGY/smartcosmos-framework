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

package com.snapbundle.pojo.extension;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.snapbundle.model.extension.IExtension;
import com.snapbundle.model.extension.IExtensionScreenShot;
import com.snapbundle.pojo.base.DomainResource;
import com.snapbundle.util.json.JsonGenerationView;

public class ExtensionScreenShot extends DomainResource<IExtensionScreenShot> implements IExtensionScreenShot
{
    @JsonDeserialize(as = Extension.class)
    @JsonView(JsonGenerationView.Restricted.class)
    protected IExtension extension;

    @JsonView(JsonGenerationView.Standard.class)
    protected int displaySequence;

    @Override
    public IExtension getExtension()
    {
        return extension;
    }

    @Override
    public void setExtension(IExtension extension)
    {
        this.extension = extension;
    }

    @Override
    public int getDisplaySequence()
    {
        return displaySequence;
    }

    @Override
    public void setDisplaySequence(int sequence)
    {
        this.displaySequence = sequence;
    }

    @Override
    public void copy(IExtensionScreenShot object)
    {
        throw new UnsupportedOperationException("POJO doesn't support copying");
    }
}
