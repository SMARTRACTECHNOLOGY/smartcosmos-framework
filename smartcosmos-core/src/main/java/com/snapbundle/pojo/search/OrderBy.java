package com.snapbundle.pojo.search;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = {"searchField", "direction"})
public class OrderBy
{
    protected SearchField searchField;

    protected Direction direction;

    public static OrderBy newInstance()
    {
        return new OrderBy();
    }

    public OrderBy field(SearchField searchField)
    {
        this.searchField = searchField;
        return this;
    }

    public OrderBy asc()
    {
        this.direction = Direction.Ascending;
        return this;
    }

    public OrderBy desc()
    {
        this.direction = Direction.Descending;
        return this;
    }
}
