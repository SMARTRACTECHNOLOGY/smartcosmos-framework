package net.smartcosmos.am.builder;

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

import net.smartcosmos.am.model.context.IGroup;
import net.smartcosmos.am.pojo.context.Group;
import net.smartcosmos.builder.AbstractNamedObjectBuilder;

public class GroupBuilder extends AbstractNamedObjectBuilder<IGroup, GroupBuilder>
{

    public GroupBuilder()
    {
        super(new Group());
    }

    public GroupBuilder setName(String name)
    {
        instance.setName(name);
        return this;
    }

    public GroupBuilder setDescription(String description)
    {
        instance.setDescription(description);
        return this;
    }

    public GroupBuilder setUrn(String urn)
    {
        instance.setUrn(urn);
        return this;
    }
}
