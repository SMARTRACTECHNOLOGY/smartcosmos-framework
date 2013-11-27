package com.snapbundle.pojo.search;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    public String toJson() throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }


}
