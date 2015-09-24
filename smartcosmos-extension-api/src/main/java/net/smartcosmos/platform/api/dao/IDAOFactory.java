package net.smartcosmos.platform.api.dao;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Server API
 * ===============================================================================
 * Copyright (C) 2013 - 2015 Smartrac Technology Fletcher, Inc.
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

import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.platform.api.ext.IExtendable;

public interface IDAOFactory extends IExtendable
{
    IMonikerSearchDAO getMonikerSearchDAO(EntityReferenceType entityReferenceType);

    INamedObjectSearchDAO getNamedObjectSearchDAO(EntityReferenceType entityReferenceType);

    IDeviceDAO getDeviceDAO();

    IGeospatialDAO getGeospatialDAO();

    IObjectDAO getObjectDAO();

    IObjectInteractionDAO getObjectInteractionDAO();

    ITimelineDAO getTimelineDAO();

    IFileDAO getFileDAO();

    IMetadataDAO getMetadataDAO();

    ITagDAO getTagDAO();

    ITagAssignmentDAO getTagAssignmentDAO();

    IObjectAddressDAO getObjectAddressDAO();

    IObjectInteractionSessionDAO getObjectInteractiveSessionDAO();

    IExtensionDAO getExtensionDAO();

    IRelationshipDAO getRelationshipDAO();

    ILibraryElementDAO getLibraryElementDAO();

    IAccountDAO getAccountDAO();

    IAccountDirectoryDAO getAccountDirectoryDAO();

    IOAuthTokenTransactionDAO getOAuthTokenTransactionDAO();

    IOAuthTokenPermissionDAO getOAuthTokenPermissionDAO();

    IOAuthTokenRegistryDAO getOAuthTokenRegistryDAO();

    IRegistrationDAO getRegistrationDAO();

    IUserDAO getUserDAO();

    IEventDAO getEventDAO();

    INotificationEndpointDAO getNotificationEndpointDAO();

    IBatchTransmissionDAO getBatchTransmissionDAO();
}
