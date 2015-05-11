/**
 * Batch processing provides a means of performing operations against a large set of data without incurring the
 * overhead of REST / JSON translations. For example, if a developer wants to import or export a large amount of data,
 * architecturally speaking it may make sense to perform the operation with a batch and directly accessing the DAOs of
 * the classes involved without having to make hundreds or thousands of REST API calls.
 * <h3>Purpose of Batch</h3>
 * To be clear, the purpose of the Batch processing framework is to <b>get the file to the server for processing</b>
 * and put an entry into a queue to signify that the file is ready for processing.
 * <h3>Batch as a Finite State Machine</h3>
 * Please review the developer documentation to understand the finite state machine properties of the Batch processing
 * framework.
 */
package net.smartcosmos.platform.api.batch;
