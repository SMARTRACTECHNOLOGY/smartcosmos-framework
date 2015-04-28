package net.smartcosmos.platform.jpa.base;

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
import net.smartcosmos.model.base.IDomainResource;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.platform.jpa.AccountEntity;
import net.smartcosmos.util.json.JsonGenerationView;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class DomainResourceAccountEntity<T extends IDomainResource> extends DomainResourceEntity<T>
{
    @JsonView(JsonGenerationView.Full.class)
    @ManyToOne(targetEntity = AccountEntity.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "accountId", updatable = false, referencedColumnName = "uniqueId", nullable = false)
    protected IAccount account;

    public IAccount getAccount()
    {
        return account;
    }

    public void setAccount(IAccount account)
    {
        this.account = account;
    }
}
