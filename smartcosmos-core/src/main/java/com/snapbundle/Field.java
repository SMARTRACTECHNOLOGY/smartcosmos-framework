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

package com.snapbundle;

public final class Field extends VersionData
{
    public static final String HTTP_HEADER_EVENT = "SnapBundle-Event";

    public static final String HTTP_HEADER_VIEW = "SnapBundle-View";

    public static final String HTTP_HEADER_START_TIMESTAMP = "SnapBundle-Session-Start";

    public static final String HTTP_HEADER_STOP_TIMESTAMP = "SnapBundle-Session-Stop";

    /**
     * Use this value to re-assign the null value to a moniker field after it was
     * initially assigned with a value.
     */
    public static final String NULL_MONIKER = "4028E4F642B838590142B8659F390002";

    public static final String EMAIL_VERIFICATION_TOKEN_FIELD = "emailVerificationToken";

    public static final String EMAIL_ADDRESS_FIELD = "emailAddress";

    public static final String GIVEN_NAME_FIELD = "givenName";

    public static final String SURNAME_FIELD = "surname";

    public static final String REALM_FIELD = "realm";

    public static final String EVENT_TYPE = "eventType";

    public static final String CODE_FIELD = "code";

    public static final String MESSAGE_FIELD = "message";

    public static final String UNIQUE_ID_FIELD = "uniqueId";

    public static final String URN_FIELD = "urn";

    public static final String MONIKER_FIELD = "moniker";

    public static final String LINE_1_FIELD = "line1";

    public static final String LINE_2_FIELD = "line2";

    public static final String CITY_FIELD = "city";

    public static final String STATE_PROVINCE_FIELD = "stateProvince";

    public static final String POSTAL_CODE_FIELD = "postalCode";

    public static final String COUNTRY_ABBREVIATION_FIELD = "countryAbbreviation";

    public static final String NAME_FIELD = "name";

    public static final String DESCRIPTION_FIELD = "description";

    public static final String ACTIVE_FIELD = "activeFlag";

    public static final String OBJECT_FIELD = "object";

    public static final String OBJECT_URN_FIELD = "objectUrn";

    public static final String OBJECT_INTERACTION_URN_FIELD = "objectInteractionUrn";

    public static final String OBJECT_INTERACTION_SESSION_FIELD = "objectInteractionSession";

    public static final String START_TIMESTAMP_FIELD = "startTimestamp";

    public static final String STOP_TIMESTAMP_FIELD = "stopTimestamp";

    public static final String NOTIFICATION_ENROLLMENT_CONFIRMATION_TOKEN = "token";

    public static final String NOTIFICATION_ENDPOINT_URL = "endpointUrl";

    public static final String ROLE_TYPE_FIELD = "roleType";

    public static final String DEVICE_IDENTIFICATION_FIELD = "identification";

    public static final String REFERENCE_URN_FIELD = "referenceUrn";

    public static final String ENTITY_REFERENCE_TYPE = "entityReferenceType";

    public static final String RELATED_REFERENCE_URN_FIELD = "relatedReferenceUrn";

    public static final String RELATED_ENTITY_REFERENCE_TYPE = "relatedEntityReferenceType";

    public static final String TYPE_FIELD = "type";

    public static final String MIME_TYPE_FIELD = "mimeType";

    public static final String CONTENT_URL_FIELD = "contentUrl";

    public static final String KEY_FIELD = "key";

    public static final String RAW_VALUE_FIELD = "rawValue";

    public static final String DECODED_VALUE_FIELD = "decodedValue";

    public static final String DATA_TYPE_FIELD = "dataType";

    public static final String FLAG_FIELD = "flag";

    public static final String RECORDED_TIMESTAMP_FIELD = "recordedTimestamp";

    public static final String OLD_PASSWORD_FIELD = "oldPassword";

    public static final String NEW_PASSWORD_FIELD = "newPassword";

    //
    // Timeline Specific Fields
    //

    public static final String TIMESTAMP_FIELD = "timestamp";

    public static final String VISIBLE_FIELD = "visible";

    public static final String HIGHLIGHT_FIELD = "highlight";

    //
    // Spatial Fields
    //
    public static final String GEOMETRIC_SHAPE_FIELD = "geometricShape";

    //
    // Extension Fields
    //
    public static final String APP_CATALOG_URL_FIELD = "appCatalogUrl";

    public static final String SUPPORT_EMAIL_FIELD = "supportEmail";

    public static final String WEB_SITE_FIELD = "webSiteUrl";

    public static final String REDIRECT_URL_FIELD = "redirectUrl";

    public static final String EXTENSION_PERMISSION_FIELD = "permissions";

    public static final String LONG_DESCRIPTION_FIELD = "longDescription";

    public static final String EXTENSION_TYPE_FIELD = "extensionType";

    public static final String DISPLAY_SEQUENCE_FIELD = "displaySequence";

    public static final String PERMISSION_TYPE_FIELD = "permissionType";

    public static final String STATE_FIELD = "state";

    private Field()
    {
    }
}
