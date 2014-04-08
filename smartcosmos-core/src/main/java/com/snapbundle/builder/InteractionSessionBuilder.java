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
import com.snapbundle.model.context.IObjectInteractionSession;
import com.snapbundle.pojo.context.ObjectInteractionSession;

/**
 * Convenience Builder pattern class for creating new {@link com.snapbundle.model.context.IObjectInteractionSession}
 * instances.
 * <p/>
 * The minimum fields required to define a new instance are:
 * <ul>
 * <li>{@link com.snapbundle.Field#NAME_FIELD}</li>
 * <li>{@link com.snapbundle.Field#TYPE_FIELD}</li>
 * <li>{@link com.snapbundle.Field#START_TIMESTAMP_FIELD}</li>
 * </ul>
 */
public class InteractionSessionBuilder extends AbstractNamedObjectBuilder<IObjectInteractionSession, InteractionSessionBuilder>
{
    public InteractionSessionBuilder(long startTimestamp)
    {
        super(new ObjectInteractionSession());
        instance.setStartTimestamp(startTimestamp);
    }

    public InteractionSessionBuilder setType(String type)
    {
        instance.setType(type);
        return this;
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getType(), "type must not be null");
        Preconditions.checkNotNull(instance.getName(), "name must not be null");
        Preconditions.checkState(instance.getStartTimestamp() > 0, "start timestamp must be a positive value");
    }
}

