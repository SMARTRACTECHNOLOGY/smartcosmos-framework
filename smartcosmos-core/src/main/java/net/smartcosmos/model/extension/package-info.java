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

/**
 * <b>EXPERIMENTAL PACKAGE - NO PRODUCTION SUPPORT</b>
 *
 * Defines a plugin metaphor whereby 3rd party developers can tap into an
 * account's contextual data in order to perform advanced computations that
 * are not natively supported by the SMART COSMOS Objects platform. The primary means
 * of near real-time integration with these external components is via an
 * {@link net.smartcosmos.model.event.EventType} notification delivered to
 * an {@link net.smartcosmos.model.integration.INotificationEndpoint} managed
 * by the extension.
 */
package net.smartcosmos.model.extension;