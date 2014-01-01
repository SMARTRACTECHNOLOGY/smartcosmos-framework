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
    long getTimestamp();

    void setTimestamp(long timestamp);

    boolean isHighlight();

    void setHighlight(boolean highlight);

    boolean isVisible();

    void setVisible(boolean visible);
}