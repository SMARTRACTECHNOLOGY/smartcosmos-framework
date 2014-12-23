package net.smartcosmos.client.impl.command;

import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.pojo.base.ResponseEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collection;

public interface ICommand<T, E>
{
    T call(Class<? extends E> clazz, String path) throws ServiceException;

    T call(Class<? extends E> clazz, String path, JSONObject inputJson) throws ServiceException;

    Collection<ResponseEntity> call(String path, JSONArray inputJson) throws ServiceException;
}
