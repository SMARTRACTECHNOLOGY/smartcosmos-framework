/*
 * SnapBundle™ SDK
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
import com.snapbundle.model.base.INamedObject;
import com.snapbundle.model.base.ITypedObject;

/**
 * Simple construct used to establish a logical albeit arbitrarily defined peer relationship between
 * a collection of {@link com.snapbundle.model.context.IObjectInteraction} instances.
 * <p/>
 * The duration of an interaction session is arbitrary; it may be seconds, hours, days, or any
 * time period defined by the application data being captured.
 */
public interface IObjectInteractionSession extends IAccountDomainResource<IObjectInteractionSession>, INamedObject<IObjectInteractionSession>, ITypedObject
{
    /**
     * System-assigned timestamp established when the session is opened.
     *
     * @return Session start timestamp
     */
    long getStartTimestamp();

    void setStartTimestamp(long timestamp);

    /**
     * System-assigned timestamp defined when the session is closed.
     *
     * @return Session stop timestamp
     */
    long getStopTimestamp();

    void setStopTimestamp(long timestamp);
}
