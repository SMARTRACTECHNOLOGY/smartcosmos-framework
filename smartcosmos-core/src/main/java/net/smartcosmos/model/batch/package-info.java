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
 * SMART COSMOS batch processing capabilities.
 *
 * An <code>IFileTransmissionRequest</code> is sent to the server, registering a request to upload a file for
 * asynchronous batch processing. The server will then respond with a <code>IFileTransmissionResponse</code> that
 * provides transmission guidance, specifically the endpoint URI where the file should be sent and a <b>Transmission
 * URN</b> that identifies the name that the file must be transmitted under, thus ensuring there are never any
 * name collisions.
 *
 * After the file has been successfully transmitted, a <code>IFileTransmissionReceipt</code> <b>must</b> be sent to the
 * server to indicate the outcome of the transmission. Until the server receive a transmission receipt, <i>the batch
 * is not processed and is in a state of limbo</i>.
 */
package net.smartcosmos.model.batch;