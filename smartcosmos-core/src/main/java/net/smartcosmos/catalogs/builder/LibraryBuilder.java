package net.smartcosmos.catalogs.builder;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Core SDK
 * ===============================================================================
 * Copyright (C) 2013 - 2014 SMARTRAC Technology Fletcher, Inc.
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
import net.smartcosmos.builder.AbstractNamedObjectBuilder;
import net.smartcosmos.catalogs.model.context.ILibrary;
import net.smartcosmos.catalogs.model.context.IShelf;
import net.smartcosmos.catalogs.pojo.context.Library;
import net.smartcosmos.model.context.IAccount;

import java.util.ArrayList;
import java.util.Collection;

public class LibraryBuilder extends AbstractNamedObjectBuilder<ILibrary, LibraryBuilder>
{
    private Collection<ShelfBuilder> builders = new ArrayList<>();

    public LibraryBuilder(String name)
    {
        super(new Library());

        Preconditions.checkNotNull(name);
        instance.setName(name);
    }

    public LibraryBuilder setAccount(IAccount account)
    {
        instance.setAccount(account);
        return this;
    }

    public LibraryBuilder setType(String type)
    {
        instance.setType(type);
        return this;
    }

    public LibraryBuilder addShelf(ShelfBuilder shelfBuilder)
    {
        Preconditions.checkNotNull(shelfBuilder);
        builders.add(shelfBuilder);
        return this;
    }

    public LibraryBuilder addShelf(IShelf shelf)
    {
        instance.addShelf(shelf);
        return this;
    }

    @Override
    protected void onInject()
    {
        for (ShelfBuilder builder : builders)
        {
            builder.setLibrary(instance);
            addShelf(builder.build());
        }
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getType(), "type must not be null");
    }

}
