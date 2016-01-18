/*
 * Copyright (C> 2013 - 2015, Smartrac Technology Fletcher, Inc.
 * 267 Cane Creek Rd, Fletcher, NC, 28732, USA
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Smartrac Technology Fletcher, Inc. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Smartrac Technology Fletcher, Inc.
 */

package net.smartcosmos.platform.jpa.base;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Objects JPA Mappings
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
import net.smartcosmos.model.base.INamedObject;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.platform.jpa.AccountEntity;
import net.smartcosmos.util.json.JsonGenerationView;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class DomainResourceAccountNamedObjectEntity<T extends INamedObject<T>>
        extends DomainResourceNamedObjectEntity<T>
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JsonView(JsonGenerationView.Full.class)
    @ManyToOne(targetEntity = AccountEntity.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "accountUuid", updatable = false, referencedColumnName = "systemUuid", nullable = false)
    private IAccount account;

    public IAccount getAccount()
    {
        return account;
    }

    public void setAccount(final IAccount account)
    {
        this.account = account;
    }

    /**
     * Just a reminder that the super type doesn't copy the account.
     * 
     * @param account
     *            account from target.
     */
    protected abstract void copyDomainResourceAccountNamedObjectEntity(IAccount account);
}
