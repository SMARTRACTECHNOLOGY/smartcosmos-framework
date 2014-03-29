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

/**
 * <b>GO AWAY</b> non-public implementation package that developers should avoid for all intents and purposes.
 * The package defines a collective set of CRUD interfaces driven off of either an explicit entity instance, e.g. an
 * instance of say {@link com.snapbundle.model.context.IObject}, or a more fluid and dynamic {@link org.json.JSONObject}
 * instance.
 *
 * Developers do not have any need to narrow and cast a valid client interface down to one of these CRUD interfaces.
 *
 * It also defines several sub-packages, one that contains a collection of abstract base classes used to build the
 * various clients, one that defines explicit endpoints with parameterized URLs, and another that contains a
 * generalized REST web service command structure.
 */
package com.snapbundle.client.impl;