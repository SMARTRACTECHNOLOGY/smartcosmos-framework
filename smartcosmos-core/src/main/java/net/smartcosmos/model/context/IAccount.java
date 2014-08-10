/*
 * SMART COSMOS SDK
 * (C) Copyright 2013-2014, Smartrac Technology Fletcher, Inc.
 * 267 Cane Creek Rd, Fletcher, NC, 28732, USA
 * All Rights Reserved.
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

package net.smartcosmos.model.context;

import net.smartcosmos.model.base.IDomainResource;
import net.smartcosmos.model.base.INamedObject;

/**
 * Collection of foundational attributes that define a platform subscription. Each {@link IAccount} will have a unique
 * public-private key generated at creation time. The private key is securely managed by the platform and is
 * inaccessible to all. The public key is accessible to account administrators, and may be used to verify digitally
 * signed data downloaded from platform to confirm it hasn't been tampered with after download.
 */
public interface IAccount extends IDomainResource<IAccount>, INamedObject<IAccount>
{
}
