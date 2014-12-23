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
