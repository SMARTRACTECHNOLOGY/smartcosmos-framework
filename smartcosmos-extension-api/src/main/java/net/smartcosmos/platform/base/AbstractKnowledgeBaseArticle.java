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

import com.google.common.base.Preconditions;
import freemarker.template.Template;
import net.smartcosmos.platform.api.ICosmosContext;
import net.smartcosmos.platform.api.kb.IKnowledgeBaseArticle;

public abstract class AbstractKnowledgeBaseArticle<T extends ICosmosContext> implements IKnowledgeBaseArticle<T>
{
    private String id;

    protected String emailTemplatePath = null;

    protected T context;

    protected AbstractKnowledgeBaseArticle(String id)
    {
        this(id, null);
    }

    @Override
    public void setContext(T context)
    {
        this.context = context;
    }

    protected AbstractKnowledgeBaseArticle(String id, String emailTemplatePath)
    {
        Preconditions.checkArgument(id != null, "id must not be null");
        this.id = id;
        this.emailTemplatePath = emailTemplatePath;
    }

    @Override
    public String getId()
    {
        return id;
    }

    @Override
    public boolean hasEmailTemplate()
    {
        return (emailTemplatePath != null);
    }

    @Override
    public Template getTemplate()
    {
        //TODO: - Load Freemarker Template
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractKnowledgeBaseArticle that = (AbstractKnowledgeBaseArticle) o;

        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return id.hashCode();
    }

    @Override
    public String toString()
    {
        return "AbstractKnowledgeBaseArticle{" +
                "id='" + id + '\'' +
                "title='" + getTitle() + '\'' +
                "hasEmailTemplate='" + hasEmailTemplate() + '\'' +
                '}';
    }

    @Override
    public int compareTo(IKnowledgeBaseArticle o)
    {
        return id.compareTo(o.getId());
    }
}
