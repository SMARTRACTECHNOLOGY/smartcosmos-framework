package com.snapbundle.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.snapbundle.model.base.EntityReferenceType;
import com.snapbundle.pojo.search.OrderBy;
import com.snapbundle.pojo.search.SearchClause;
import com.snapbundle.pojo.search.SearchCriteria;
import com.snapbundle.pojo.search.SearchField;
import com.snapbundle.pojo.search.SearchPredicate;
import org.testng.annotations.Test;

public class SearchCriteriaTest
{
    @Test
    public void testValidEntities()
    {
        SearchCriteria.newInstance(EntityReferenceType.Event);
        SearchCriteria.newInstance(EntityReferenceType.Object);
        SearchCriteria.newInstance(EntityReferenceType.ObjectInteraction);
        SearchCriteria.newInstance(EntityReferenceType.User);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testInvalidEntities()
    {
        SearchCriteria.newInstance(EntityReferenceType.Extension);
    }

    @Test
    public void testCleanEventQuery() throws JsonProcessingException
    {
        String json = SearchCriteria.newInstance(EntityReferenceType.Event)
                .addCriteria(EntityReferenceType.User,
                        SearchClause.newInstance()
                                .field(SearchField.EmailAddress)
                                .is(SearchPredicate.STARTS_WITH)
                                .value("foo"))
                .setOrderBy(OrderBy.newInstance()
                        .field(SearchField.EmailAddress)
                        .asc())
                .setLimit(5)
                .toJson();

        System.out.println(json);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testBadEventQuery() throws JsonProcessingException
    {
        String json = SearchCriteria.newInstance(EntityReferenceType.Event)
                .addCriteria(EntityReferenceType.Object,
                        SearchClause.newInstance()
                                .field(SearchField.EmailAddress)
                                .is(SearchPredicate.STARTS_WITH)
                                .value("foo"))
                .setLimit(5)
                .toJson();

        System.out.println(json);
    }

}
