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

package com.snapbundle.model.base;

/**
 * Arbitrary textual field that is never directly interpreted by the SnapBundle™ platform. Monikers are provided to
 * developers to store additional contextual data outside of the scope defined by the SnapBundle™ platform. A typical
 * usage is to store an external system alias or key, linking the SnapBundle™ object to an object managed and stored
 * outside of the SnapBundle™ platform.
 */
public interface IMoniker
{
    String getMoniker();

    void setMoniker(String moniker);
}
