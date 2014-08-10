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

package net.smartcosmos.objects.model.context;

import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.model.base.IAccountDomainResource;
import net.smartcosmos.model.base.IReferentialObject;
import net.smartcosmos.model.base.ITypedObject;

/**
 * Captures binary or true-false conditions between objects. A relationship could capture
 * that Jason "owns" a bank account, whereas an {@link IObjectInteraction} would
 * be used to capture every deposit or withdraw from the account.
 * <p/>
 * If you are looking to capture a recurring pattern of interactivity, the recommended construct is
 * to use an {@link IObjectInteraction} instead of a relationship.
 */
public interface IRelationship extends IAccountDomainResource<IRelationship>, IReferentialObject, ITypedObject
{
    String getRelatedReferenceUrn();

    void setRelatedReferenceUrn(String urn);

    EntityReferenceType getRelatedEntityReferenceType();

    void setRelatedEntityReferenceType(EntityReferenceType entityReferenceType);
}
