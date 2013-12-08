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
    NewAccountDefined,
    AccountConfirmed,
    UserDefined,
    UserLoginSuccess,
    UserLoginFailure,

    DeviceDefined,
    ObjectDefined,
    ObjectInteraction,

    FileDefined,
    FileUploaded,

    UserPasswordReset,
    UserPasswordChanged,

    ObjectBatchStart,
    ObjectBatchStop,

    UserBatchStart,
    UserBatchStop,

    DeviceBatchStart,
    DeviceBatchStop,

    ObjectUpdated,
    DeviceUpdated,
    UserUpdated,
    AccountUpdated,

    ObjectDeactivated,

    PermanentObjectAddressDefined,
    PermanentObjectAddressUpdated,
    LastKnownObjectAddressDefined,
    PermanentObjectAddressDeleted,

    FileRetrieval,

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

    TagUpserted,
    TagDeleted,

    TagAssigned,
    TagRevoked,

    RelationshipDefined,
    RelationshipDeleted,

    ExtensionDefined,
    ExtensionUpdated,
    ExtensionActivated,
    ExtensionDeactivated,
    ExtensionDeleted,
    ExtensionScreenshotDefined,
    ExtensionScreenshotDeleted,
    ExtensionPermissionDefined,
    ExtensionPermissionDeleted,

    OAuthRequest,
    OAuthAuthorizationCodeIssued,
    OAuthRequestDenied,
    OAuthTokenIssued,
    OAuthLoginSuccess,
    OAuthLoginFailure,
    OAuthTokenRefreshed,
    TokenRevocation,

    TimelineEntryDefined,
    TimelineEntryUpdated,
    TimelineEntryDeleted,
    TimelineEntryHidden,
    TimelineEntryShown,

    FileAccessed,
    ObjectAccessed,
    ObjectAddressAccessed,
    ObjectInteractionAccessed,
    ObjectInteractionSessionAccessed,
    RelationshipAccessed,
    TimelineEntryAccessed

}
