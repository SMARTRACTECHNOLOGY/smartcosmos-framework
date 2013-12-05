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
import com.snapbundle.model.base.IDomainResource;
import com.snapbundle.model.base.INamedObject;

public interface IDevice extends IAccountDomainResource<IDevice>, INamedObject<IDevice>
{
    DeviceType getDeviceType();

    void setDeviceType(DeviceType deviceType);

    String getIdentification();

    void setIdentification(String identification);
}
