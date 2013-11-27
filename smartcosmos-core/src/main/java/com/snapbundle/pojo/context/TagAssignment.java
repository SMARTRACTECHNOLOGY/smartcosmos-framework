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

package com.snapbundle.pojo.context;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.snapbundle.model.context.ITag;
import com.snapbundle.model.context.ITagAssignment;
import com.snapbundle.pojo.base.ReferentialObject;
import com.snapbundle.util.JsonGenerationView;

import java.io.IOException;

public class TagAssignment extends ReferentialObject<ITagAssignment> implements ITagAssignment
{
    @JsonDeserialize(as = Tag.class)
    @JsonView(JsonGenerationView.Minimum.class)
    protected ITag tag;

    public static ITagAssignment fromJson(String json) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, TagAssignment.class);
    }

    @Override
    public void copy(ITagAssignment object)
    {
        throw new UnsupportedOperationException("POJO doesn't support copying");
    }

    @Override
    public ITag getTag()
    {
        return tag;
    }

    @Override
    public void setTag(ITag tag)
    {
        this.tag = tag;
    }
}


