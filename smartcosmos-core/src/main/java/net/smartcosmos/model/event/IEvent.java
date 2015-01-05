package net.smartcosmos.model.event;

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

import net.smartcosmos.model.base.IDomainResource;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.model.context.IUser;

/**
 * Flexible mechanism for capturing user interactions with the platform. The source
 * field contains JSON values that correlate with the specific {@link EventType}. For
 * example, if the event is {@link EventType#ObjectAccessed}, then the {@link #getSource()} will
 * contain the {@link net.smartcosmos.util.json.JsonGenerationView.Standard} serialization of
 * the {@link net.smartcosmos.objects.model.context.IObject} that was accessed.
 */
public interface IEvent extends IDomainResource<IEvent>
{
    EventType getEventType();

    void setEventType(EventType eventType);

    IAccount getAccount();

    void setAccount(IAccount account);

    IUser getUser();

    void setUser(IUser user);

    String getSource();

    void setSource(String source);

    boolean suppressBroadcast();

    void setSuppressBroadcast(boolean flag);
}
