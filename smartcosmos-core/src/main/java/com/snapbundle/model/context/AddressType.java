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

/**
 * Objects may be associated with 0, 1, or many different addresses. If a {@link IObject} defines a
 * {@link #Permanent} address, then it must only define a single address, and that address will support an update
 * operation. If the {@link IObject} defines a {@link #LastKnown} address, then the address is immediately immutable
 * and cannot be updated. Unlike a {@link IObject} with a {@link #Permanent} address, any {@link IObject} using a
 * {@link #LastKnown} address strategy may have an unlimited number of immutable last known address entries.
 * <p/>
 * The {@link #Permanent} address is typically assigned to items that are fixed in position, such as a building or
 * a permanently installed piece of equipment like a large printing press. The {@link #LastKnown} address strategy is
 * typically used for tracking items that move around frequently. For example, a test tube used in a pharmaceutical
 * trial may be collected in one location, analyzed in another, and stored for a period of time in several other
 * locations.
 */
public enum AddressType
{
    Permanent,
    LastKnown,
    Unknown
}
