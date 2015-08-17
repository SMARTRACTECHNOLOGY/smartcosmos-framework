package net.smartcosmos.platform.jpa;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Server API
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

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.model.context.IUser;
import net.smartcosmos.model.event.EventType;
import net.smartcosmos.model.event.IEvent;
import net.smartcosmos.platform.jpa.base.DomainResourceEntity;
import net.smartcosmos.pojo.context.Account;
import net.smartcosmos.pojo.context.User;
import net.smartcosmos.util.json.JsonGenerationView;
import net.smartcosmos.util.json.JsonUtil;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity(name = "event")
public class EventEntity extends DomainResourceEntity<IEvent> implements IEvent
{
    @JsonView(JsonGenerationView.Minimum.class)
    @Enumerated(EnumType.STRING)
    protected EventType eventType;

    @JsonView(JsonGenerationView.Minimum.class)
    @Column(length = 8192, nullable = true, updatable = false)
    protected String jsonUserDefinition;

    @JsonView(JsonGenerationView.Restricted.class)
    @JsonDeserialize(as = Account.class)
    @ManyToOne(targetEntity = AccountEntity.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "accountUuid", updatable = false, referencedColumnName = "systemUuid", nullable = true)
    protected IAccount account;

    @JsonView(JsonGenerationView.Minimum.class)
    @Column(length = 8192, nullable = true)
    protected String source;

    @JsonView(JsonGenerationView.Minimum.class)
    @Transient
    protected IUser reportedUser;

    @JsonView(JsonGenerationView.Minimum.class)
    @Basic
    protected boolean suppressBroadcast;

    @Override
    public void onPostLoad()
    {
        super.onPostLoad();
        if (jsonUserDefinition != null)
        {
            reportedUser = JsonUtil.fromJson(jsonUserDefinition, User.class);
        }
    }

    @Override
    public void onPrePersist()
    {
        super.onPrePersist();
        if (reportedUser != null)
        {
            this.jsonUserDefinition = JsonUtil.toJson(reportedUser, JsonGenerationView.Standard.class);
        }
    }

    public EventEntity()
    {

    }

    @Override
    public void copy(IEvent target)
    {
        super.copy(target);

        this.eventType = target.getEventType();

        this.reportedUser = target.getUser();
        this.source = target.getSource();
        this.account = target.getAccount();

        this.suppressBroadcast = target.suppressBroadcast();
    }

    @Override
    public boolean suppressBroadcast()
    {
        return suppressBroadcast;
    }

    @Override
    public void setSuppressBroadcast(boolean suppressBroadcast)
    {
        this.suppressBroadcast = suppressBroadcast;
    }

    @Override
    public IAccount getAccount()
    {
        return account;
    }

    @Override
    public void setAccount(IAccount account)
    {
        this.account = account;
    }

    @Override
    public EventType getEventType()
    {
        return eventType;
    }

    @Override
    public void setEventType(EventType eventType)
    {
        this.eventType = eventType;
    }

    @Override
    public IUser getUser()
    {
        return reportedUser;
    }

    @Override
    public void setUser(IUser user)
    {
        this.reportedUser = user;
    }

    @Override
    public String getSource()
    {
        return source;
    }

    @Override
    public void setSource(String source)
    {
        this.source = source;
    }
}

