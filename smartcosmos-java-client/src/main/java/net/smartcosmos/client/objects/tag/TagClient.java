package net.smartcosmos.client.objects.tag;

import com.google.common.base.Preconditions;
import net.smartcosmos.client.connectivity.ServerContext;
import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.impl.base.AbstractUpsertableBaseClient;
import net.smartcosmos.client.impl.command.DeleteCommand;
import net.smartcosmos.client.impl.command.GetCollectionCommand;
import net.smartcosmos.client.impl.command.GetCommand;
import net.smartcosmos.client.impl.command.PutCommand;
import net.smartcosmos.client.impl.endpoint.TagEndpoints;
import net.smartcosmos.Field;
import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.objects.model.context.ITag;
import net.smartcosmos.objects.model.context.ITagAssignment;
import net.smartcosmos.objects.pojo.context.Tag;
import net.smartcosmos.objects.pojo.context.TagAssignment;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.util.json.JsonUtil;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;

class TagClient extends AbstractUpsertableBaseClient<ITag> implements ITagClient
{
    TagClient(ServerContext context)
    {
        super(context);
    }

    @Override
    public ITag findByUrn(String urn, ViewType viewType) throws ServiceException
    {
        return findByUrn(urn, TagEndpoints.findByUrn(urn, viewType), Tag.class);
    }

    @Override
    public ResponseEntity upsert(JSONObject instance) throws ServiceException
    {
        return upsert(instance, TagEndpoints.upsert());
    }

    @Override
    public void delete(ITag instance) throws ServiceException
    {
        try
        {
            JSONObject json = new JSONObject(JsonUtil.toJson(instance, ViewType.Full));
            delete(json);
        } catch (JSONException e)
        {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(JSONObject instance) throws ServiceException
    {
        Preconditions.checkState(instance.has(Field.URN_FIELD));

        try
        {
            DeleteCommand command = new DeleteCommand(context);
            command.call(Object.class, TagEndpoints.delete(instance.getString(Field.URN_FIELD)));
        } catch (JSONException e)
        {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<ResponseEntity> assign(EntityReferenceType entityReferenceType, String referenceUrn, Collection<ITag> tags) throws ServiceException
    {
        JSONArray array = new JSONArray();

        for (ITag tag : tags)
        {
            try
            {
                JSONObject jsonObject = new JSONObject()
                        .put(Field.NAME_FIELD, tag.getName());

                array.put(jsonObject);
            } catch (JSONException e)
            {
                throw new ServiceException(e);
            }
        }

        return assign(entityReferenceType, referenceUrn, array);
    }

    @Override
    public Collection<ResponseEntity> assign(EntityReferenceType entityReferenceType, String referenceUrn, JSONArray jsonArray) throws ServiceException
    {
        // PUT
        PutCommand<ResponseEntity> command = new PutCommand<>(context);
        return command.call(TagEndpoints.assign(entityReferenceType, referenceUrn), jsonArray);
    }

    @Override
    public Collection<ITagAssignment> findEntitiesByTagsAssignedToEntity(EntityReferenceType entityReferenceType, String referenceUrn, ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<ITagAssignment> command = new GetCollectionCommand<>(context);
        return command.call(TagAssignment.class, TagEndpoints.findEntitiesByTagsAssignedToEntity(entityReferenceType, referenceUrn, viewType));
    }

    @Override
    public Collection<ITagAssignment> findEntitiesByTagsAssignedToType(EntityReferenceType entityReferenceType, String tagName, ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<ITagAssignment> command = new GetCollectionCommand<>(context);
        return command.call(TagAssignment.class, TagEndpoints.findEntitiesByTagsAssignedToType(entityReferenceType, tagName, viewType));
    }

    @Override
    public Collection<ITagAssignment> findEntitiesByTagNameLike(String tagName, ViewType viewType) throws ServiceException
    {
        GetCollectionCommand<ITagAssignment> command = new GetCollectionCommand<>(context);
        return command.call(TagAssignment.class, TagEndpoints.findEntitiesByTagNameLike(tagName, viewType));
    }

    @Override
    public ITag findByTag(String tagName, ViewType viewType) throws ServiceException
    {
        GetCommand<ITag> command = new GetCommand<>(context);
        return command.call(Tag.class, TagEndpoints.findByTag(tagName, viewType));
    }

    @Override
    public void revokeAssignment(String tagName, EntityReferenceType entityReferenceType, String referenceUrn) throws ServiceException
    {
        DeleteCommand command = new DeleteCommand(context);
        command.call(Object.class, TagEndpoints.revokeAssignment(entityReferenceType, referenceUrn, tagName));
    }

    @Override
    public Collection<ITagAssignment> findEntitiesByTagsAssignedToType(EntityReferenceType entityReferenceType, String tagName) throws ServiceException
    {
        return findEntitiesByTagsAssignedToType(entityReferenceType, tagName, ViewType.Standard);
    }

    @Override
    public Collection<ITagAssignment> findEntitiesByTagNameLike(String tagName) throws ServiceException
    {
        return findEntitiesByTagNameLike(tagName, ViewType.Standard);
    }

    @Override
    public ITag findByTag(String tagName) throws ServiceException
    {
        return findByTag(tagName, ViewType.Standard);
    }


    @Override
    public Collection<ITagAssignment> findEntitiesByTagsAssignedToEntity(EntityReferenceType entityReferenceType, String referenceUrn) throws ServiceException
    {
        return findEntitiesByTagsAssignedToEntity(entityReferenceType, referenceUrn, ViewType.Standard);
    }
}
