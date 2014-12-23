package net.smartcosmos.catalogs.pojo.context;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;
import net.smartcosmos.catalogs.model.context.ILibrary;
import net.smartcosmos.catalogs.model.context.IShelf;
import net.smartcosmos.pojo.base.AccountTypedNamedObject;
import net.smartcosmos.util.json.JsonGenerationView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Library extends AccountTypedNamedObject<ILibrary> implements ILibrary
{
    @JsonView(JsonGenerationView.Minimum.class)
    @JsonDeserialize(contentAs = Shelf.class)
    protected Collection<IShelf> shelves = new ArrayList<>();

    @Override
    public ILibrary addShelf(IShelf shelf)
    {
        Preconditions.checkNotNull(shelf, "shelf must not be null");
        shelves.add(shelf);
        return this;
    }

    @Override
    public Collection<IShelf> getShelves()
    {
        return Collections.unmodifiableCollection(shelves);
    }
}
