package net.smartcosmos.platform.api.kb;

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

import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public final class KnowledgeBaseFactory
{
    private static Map<Class<?>, Set<IKnowledgeBaseArticle>> kbMap = new HashMap<>();

    public synchronized void registerKnowledgeBaseArticle(Class<?> resource, IKnowledgeBaseArticle article)
    {
        Preconditions.checkArgument(resource != null, "Resource must not be null");
        Preconditions.checkArgument(article != null, "KB Article must not be null");

        Set<IKnowledgeBaseArticle> kbArticles;
        if (kbMap.containsKey(resource))
        {
            kbArticles = kbMap.get(resource);
        } else
        {
            kbArticles = new TreeSet<>();
        }

        kbArticles.add(article);
    }

    public Set<IKnowledgeBaseArticle> getKnowledgeBaseArticles(Class<?> resource)
    {
        Set<IKnowledgeBaseArticle> kbArticles = new TreeSet<>();

        if (kbMap.containsKey(resource))
        {
            kbArticles.addAll(kbMap.get(resource));
        }

        return kbArticles;
    }
}
