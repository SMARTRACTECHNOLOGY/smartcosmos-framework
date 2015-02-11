/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Core SDK
 * ===============================================================================
 * Copyright (C) 2013 - 2014 SMARTRAC Technology Fletcher, Inc.
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

package net.smartcosmos.model.event;

/**
 * Collection of SMART COSMOS events that capture virtually all interactions between a user and the platform. In
 * many ways, the event mechanism that powers the integration features also indirectly provides a complete
 * audit of user activities.
 */
public enum EventType
{
    RegistrationRequest,

    RealmDefined,

    AccountDefined,
    AccountAccessed,
    AccountConfirmed,
    AccountUpdated,

    EventQuery,

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

    ObjectAddressDefined,
    ObjectAddressUpdated,
    ObjectAddressDeleted,

    InteractionSessionStart,
    InteractionSessionStop,

    NotificationEnroll,
    NotificationSubscribe,
    NotificationDefined,

    NotificationSubscriptionConfirmed,

    NotificationUnsubscribe,
    NotificationWithdrawn,
    NotificationDeleted,

    NotificationAccessed,

    NotificationBroadcast,

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

    OAuthRequest,
    OAuthAuthorizationCodeIssued,
    OAuthRequestDenied,
    OAuthTokenIssued,
    OAuthTokenScopeRequest,
    OAuthLoginSuccess,
    OAuthLoginFailure,
    OAuthLoginFailureDisabledUser,
    OAuthLoginFailureStaleBearerAccessToken,
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
    GeospatialEntryDeleted,

    BatchTransmissionRequest,
    BatchTransmissionReceipt,
    BatchTransmissionQueueSuccess,
    BatchTransmissionQueueError,
    BatchStatusReportAccessed,

    BusinessRuleDefined,
    BusinessRuleUpdated,
    BusinessRuleDeleted,
    BusinessRulesAccessed,
    BusinessRulesStatusChanged,
    BusinessRuleTriggered,
    BusinessRulesExported,

    FlowsEventDefined,
    FlowsEventUpdated,
    FlowsDeviceDefined,
    FlowsDeviceUpdated,

    EmailProvidersAccessed,
    EmailSent,
    DatabaseTypesAccessed

}
