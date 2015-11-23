package net.smartcosmos.platform.jpa.integrator;

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

import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.MergeEvent;
import org.hibernate.event.spi.MergeEventListener;
import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PostLoadEventListener;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.event.spi.SaveOrUpdateEventListener;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.metamodel.source.MetadataImplementor;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

import java.util.Map;

public class PlatformHibernateIntegrator implements Integrator
{
    public static class PostLoadListener implements PostLoadEventListener
    {
        private static final long serialVersionUID = -3699868455240790100L;

        @Override
        public void onPostLoad(PostLoadEvent event)
        {
            IPostLoadHandler handler = IPostLoadHandler.class.cast(event.getEntity());
            handler.onPostLoad();
        }
    }

    public static class MergeListener implements MergeEventListener
    {
        private static final long serialVersionUID = -6207950826353768020L;

        @Override
        public void onMerge(MergeEvent event)
        {
            IPreUpdateHandler handler = IPreUpdateHandler.class.cast(event.getOriginal());
            handler.onPreUpdate();
        }

        @Override
        public void onMerge(MergeEvent event, Map copiedAlready)
        {
            IPreUpdateHandler handler = IPreUpdateHandler.class.cast(event.getOriginal());
            handler.onPreUpdate();
        }
    }

    public static class SaveOrUpdateListener implements SaveOrUpdateEventListener
    {
        private static final long serialVersionUID = 2199319029711279160L;

        @Override
        public void onSaveOrUpdate(SaveOrUpdateEvent event)
        {
            IPrePersistHandler handler = IPrePersistHandler.class.cast(event.getObject());
            if (handler.getUrn() == null)
            {
                handler.onPrePersist();
            } else
            {
                IPreUpdateHandler preUpdateHandler = IPreUpdateHandler.class.cast(event.getObject());
                preUpdateHandler.onPreUpdate();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void integrate(
            Configuration configuration,
            SessionFactoryImplementor sessionFactory,
            SessionFactoryServiceRegistry serviceRegistry)
    {
        configuration.setProperty(Environment.USE_QUERY_CACHE, Boolean.FALSE.toString());
        configuration.setProperty(Environment.USE_SECOND_LEVEL_CACHE, Boolean.FALSE.toString());
        configuration.setProperty(Environment.CACHE_REGION_FACTORY,
                org.hibernate.cache.internal.NoCachingRegionFactory.class.getName());
//        configuration.setProperty(Environment.CACHE_PROVIDER,org.hibernate.cache.NoCacheProvider.class.getName());

        // As you might expect, an EventListenerRegistry is the thing with which event listeners are registered  It is a
        // service so we look it up using the service registry
        final EventListenerRegistry eventListenerRegistry = serviceRegistry.getService(EventListenerRegistry.class);

        // If you wish to have custom determination and handling of "duplicate" listeners, you would have to add an
        // implementation of the org.hibernate.event.service.spi.DuplicationStrategy contract like this
//        eventListenerRegistry.addDuplicationStrategy(myDuplicationStrategy);


        eventListenerRegistry.prependListeners(EventType.SAVE_UPDATE, SaveOrUpdateListener.class);
        eventListenerRegistry.prependListeners(EventType.POST_LOAD, PostLoadListener.class);
        eventListenerRegistry.prependListeners(EventType.MERGE, MergeListener.class);


//        eventListenerRegistry.setListeners(EventType.PRE_INSERT, PreInsertListener.class);
//        // EventListenerRegistry defines 3 ways to register listeners:
//        //     1) This form overrides any existing registrations with
//        eventListenerRegistry.setListeners(EventType.AUTO_FLUSH, myCompleteSetOfListeners);
//        //     2) This form adds the specified listener(s) to the beginning of the listener chain
//        eventListenerRegistry.prependListeners(EventType.AUTO_FLUSH, myListenersToBeCalledFirst);
//        //     3) This form adds the specified listener(s) to the end of the listener chain
//        eventListenerRegistry.appendListeners(EventType.AUTO_FLUSH, myListenersToBeCalledLast);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void integrate(MetadataImplementor metadata,
                          SessionFactoryImplementor sessionFactory,
                          SessionFactoryServiceRegistry serviceRegistry)
    {
        // no-op
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry)
    {
        // no-op
    }

}
