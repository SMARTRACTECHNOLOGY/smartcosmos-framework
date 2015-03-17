package net.smartcosmos.platform.api.service;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Client
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

/**
 * Event runners are thread-safe handlers for an {@link net.smartcosmos.model.event.IEvent} processing algorithm. The
 * default algorithm is simple database persistence of the event, but other event handlers include analytics, back
 * office integration, and even composite event handlers that can execute multiple event runners in parallel.
 */
public interface IEventRunner extends Runnable
{
    /**
     * Notification flag from the {@link io.dropwizard.lifecycle.Managed}
     * {@link net.smartcosmos.platform.api.service.IEventService} that the server is shutting down and the event
     * runner must immediately terminate its thread, presuming it is operating using some type of a blocking
     * queue that was just interrupted.
     */
    void setTerminateFlag();
}
