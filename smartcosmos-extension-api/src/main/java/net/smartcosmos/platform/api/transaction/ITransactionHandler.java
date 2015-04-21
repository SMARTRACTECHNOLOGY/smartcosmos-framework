package net.smartcosmos.platform.api.transaction;

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

import net.smartcosmos.platform.api.ICosmosContext;
import net.smartcosmos.platform.api.IService;
import net.smartcosmos.platform.api.authentication.IAuthenticatedUser;
import net.smartcosmos.platform.util.TransactionException;

/**
 * Transaction handlers are a simple and effective way to process "fat JSON files" in order to reduce the number of
 * REST calls over the wire. For example, if you need to create 7 different Object instances, with REST you could call
 * the PUT endpoint 7 separate times, incurring network latency and such, or you could design a server-side
 * "transaction handler" that guarantees that all the records are processed under a single unit of work with only
 * a single network round-trip operation.
 *
 * @param <T> Platform Context type
 */
public interface ITransactionHandler<T extends ICosmosContext> extends IService<T>
{
    /**
     * Processes the "fat JSON" that was submitted, automatically wrapped inside of a single unit-of-work.
     * <p/>
     * Throw any exception from this method to trigger a rollback of the <i>entire</i> operation.
     *
     * @param fatJson raw JSON document submitted by the caller
     */
    void run(String fatJson, IAuthenticatedUser authenticatedUser) throws TransactionException;
}
