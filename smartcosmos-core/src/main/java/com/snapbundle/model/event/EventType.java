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

package com.snapbundle.model.event;

public enum EventType
{
    RegistrationRequest,

    RealmDefined,

    AccountDefined,
    AccountConfirmed,
    AccountUpdated,

    UserDefined,
    UserUpdated,
    UserAccessed,
    UserLoginSuccess,
    UserLoginFailure,

    DeviceDefined,
    DeviceUpdated,
    DeviceAccessed,

    ObjectDefined,
    ObjectUpdated,

    ObjectInteraction,

    FileDefined,
    FileUploaded,
    FileAccessed,
    FileRetrieval,
    FileDeleted,

    UserPasswordReset,
    UserPasswordChanged,

    ObjectBatchStart,
    ObjectBatchStop,

    UserBatchStart,
    UserBatchStop,

    DeviceBatchStart,
    DeviceBatchStop,


    ObjectDeactivated,

    PermanentObjectAddressDefined,
    PermanentObjectAddressUpdated,
    LastKnownObjectAddressDefined,
    PermanentObjectAddressDeleted,


    InteractionSessionStart,
    InteractionSessionStop,

    NotificationEnroll,
    NotificationSubscribe,
    NotificationSubscriptionConfirmed,
    NotificationBroadcast,
    NotificationUnsubscribe,
    NotificationWithdrawn,

    IntegrationEndpointEnrollmentPending,
    IntegrationEndpointEnrolled,
    IntegrationEndpointWithdrawn,

    SetDeveloperLicense,
    UnsetDeveloperLicense,

    MetadataUpserted,
    MetadataDeleted,
    MetadataAccessed,

    TagUpserted,
    TagDeleted,

    TagAssigned,
    TagRevoked,
    TagAccessed,

    RelationshipDefined,
    RelationshipDeleted,

    ExtensionDefined,
    ExtensionUpdated,
    ExtensionActivated,
    ExtensionDeactivated,
    ExtensionAccessed,
    ExtensionDeleted,
    ExtensionScreenshotDefined,
    ExtensionScreenshotDeleted,
    ExtensionPermissionDefined,
    ExtensionPermissionDeleted,

    OAuthRequest,
    OAuthAuthorizationCodeIssued,
    OAuthRequestDenied,
    OAuthTokenIssued,
    OAuthTokenScopeRequest,
    OAuthLoginSuccess,
    OAuthLoginFailure,
    OAuthTokenRefreshed,
    TokenRevocation,

    TimelineEntryDefined,
    TimelineEntryUpdated,
    TimelineEntryDeleted,
    TimelineEntryHidden,
    TimelineEntryShown,

    ObjectAccessed,
    ObjectAddressAccessed,
    ObjectInteractionAccessed,
    ObjectInteractionSessionAccessed,
    RelationshipAccessed,
    TimelineEntryAccessed,

    GeospatialEntryAccessed,
    GeospatialEntryDefined,
    GeospatialEntryUpdated,
    GeospatialEntryDeleted
}
