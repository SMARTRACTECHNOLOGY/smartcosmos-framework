package net.smartcosmos.platform.base;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Common Server Framework
 * ===============================================================================
 * Copyright (C) 2013 - 2014 Smartrac Technology Fletcher, Inc.
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

import net.smartcosmos.platform.api.dao.IAccountDAO;
import net.smartcosmos.platform.api.dao.IAccountDirectoryDAO;
import net.smartcosmos.platform.api.dao.IOAuthTokenPermissionDAO;
import net.smartcosmos.platform.api.dao.IOAuthTokenRegistryDAO;
import net.smartcosmos.platform.api.dao.IOAuthTokenTransactionDAO;
import net.smartcosmos.platform.api.dao.ICosmosDAOFactory;
import net.smartcosmos.platform.api.dao.IRegistrationDAO;
import net.smartcosmos.platform.api.dao.IUserDAO;
import org.hibernate.SessionFactory;

public abstract class AbstractCosmosDAOFactory implements ICosmosDAOFactory
{
    protected final SessionFactory sessionFactory;

    private final IUserDAO userDAO;

    private final IAccountDAO accountDAO;

    private final IAccountDirectoryDAO accountDirectoryDAO;

    private final IRegistrationDAO registrationDAO;

    private final IOAuthTokenTransactionDAO oAuthTokenTransactionDAO;

    private final IOAuthTokenPermissionDAO oAuthTokenPermissionDAO;

    private final IOAuthTokenRegistryDAO oAuthTokenRegistryDAO;

    protected AbstractCosmosDAOFactory(PlatformDAOFactoryBuilder builder)
    {
        this.sessionFactory = builder.sessionFactory;
        this.userDAO = builder.userDAO;
        this.accountDAO = builder.accountDAO;
        this.accountDirectoryDAO = builder.accountDirectoryDAO;
        this.registrationDAO = builder.registrationDAO;
        this.oAuthTokenTransactionDAO = builder.oAuthTokenTransactionDAO;
        this.oAuthTokenPermissionDAO = builder.oAuthTokenPermissionDAO;
        this.oAuthTokenRegistryDAO = builder.oAuthTokenRegistryDAO;
    }

    protected static class PlatformDAOFactoryBuilder
    {
        private SessionFactory sessionFactory;

        private IUserDAO userDAO;

        private IAccountDAO accountDAO;

        private IAccountDirectoryDAO accountDirectoryDAO;

        private IRegistrationDAO registrationDAO;

        private IOAuthTokenTransactionDAO oAuthTokenTransactionDAO;

        private IOAuthTokenPermissionDAO oAuthTokenPermissionDAO;

        private IOAuthTokenRegistryDAO oAuthTokenRegistryDAO;

        public PlatformDAOFactoryBuilder(SessionFactory sessionFactory)
        {
            this.sessionFactory = sessionFactory;
        }

        public PlatformDAOFactoryBuilder setOAuthTokenTransactionDAO(IOAuthTokenTransactionDAO dao)
        {
            this.oAuthTokenTransactionDAO = dao;
            return this;
        }

        public PlatformDAOFactoryBuilder setOAuthTokenPermissionDAO(IOAuthTokenPermissionDAO dao)
        {
            this.oAuthTokenPermissionDAO = dao;
            return this;
        }

        public PlatformDAOFactoryBuilder setOAuthTokenRegistryDAO(IOAuthTokenRegistryDAO dao)
        {
            this.oAuthTokenRegistryDAO = dao;
            return this;
        }

        public PlatformDAOFactoryBuilder setUserDAO(IUserDAO dao)
        {
            this.userDAO = dao;
            return this;
        }

        public PlatformDAOFactoryBuilder setAccountDAO(IAccountDAO dao)
        {
            this.accountDAO = dao;
            return this;
        }

        public PlatformDAOFactoryBuilder setAccountDirectoryDAO(IAccountDirectoryDAO dao)
        {
            this.accountDirectoryDAO = dao;
            return this;
        }

        public PlatformDAOFactoryBuilder setRegistrationDAO(IRegistrationDAO dao)
        {
            this.registrationDAO = dao;
            return this;
        }
    }

    @Override
    public IAccountDAO getAccountDAO()
    {
        return accountDAO;
    }

    @Override
    public IRegistrationDAO getRegistrationDAO()
    {
        return registrationDAO;
    }

    @Override
    public IUserDAO getUserDAO()
    {
        return userDAO;
    }

    @Override
    public IAccountDirectoryDAO getAccountDirectoryDAO()
    {
        return accountDirectoryDAO;
    }

    @Override
    public IOAuthTokenTransactionDAO getOAuthTokenTransactionDAO()
    {
        return oAuthTokenTransactionDAO;
    }

    @Override
    public IOAuthTokenRegistryDAO getOAuthTokenRegistryDAO()
    {
        return oAuthTokenRegistryDAO;
    }

    @Override
    public IOAuthTokenPermissionDAO getOAuthTokenPermissionDAO()
    {
        return oAuthTokenPermissionDAO;
    }

}
