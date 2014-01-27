/*
 * SnapBundle™ SDK
 * (C) Copyright 2013-2014 Tag Dynamics, LLC (http://tagdynamics.com/)
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

package com.snapbundle.model.context;

import com.snapbundle.model.base.IAccountDomainResource;
import com.snapbundle.model.base.INamedObject;
import com.snapbundle.model.base.IReferentialObject;

/**
 * Defines a historically significant event identified by name and a specific timestamp, optionally clarified by an
 * optional description. TimelineEntry entries may be flagged as a highlight (arbitrarily interpreted
 * by the developer). The intent of a highlight is to let some timeline entries stand out over
 * their peers.
 * <p/>
 * TimelineEntry entries may also be flagged as invisible, preserving the entry but
 * filtering it out automatically from the default timeline retrieval operations. Visibility is
 * a separate property than the {@link com.snapbundle.model.base.INamedObject#isActive()} property by
 * design. TimelineEntry entries that are flagged as inactive are never returned in a general
 * timeline query regardless of the visibility request. Setting a timeline entry to inactive
 * effectively hides the timeline forever unless a specific update is issued against the
 * that timeline entry to re-activate it.
 */
public interface ITimelineEntry extends IAccountDomainResource<ITimelineEntry>, INamedObject<ITimelineEntry>, IReferentialObject
{
    long getRecordedTimestamp();

    void setRecordedTimestamp(long recordedTimestamp);

    boolean isHighlight();

    void setHighlight(boolean highlight);

    boolean isVisible();

    void setVisible(boolean visible);
}