/*
 * SnapBundle SDK
 * (C) Copyright 2013-2014 Tag Dynamics, LLC (http://tagdynamics.com/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.snapbundle.client.connectivity;

import com.snapbundle.pojo.base.ResponseEntity;
import com.snapbundle.pojo.base.Result;

/**
 * Generalized exception that indicates the underlying platform web service call failed. In most instances, the
 * {@link #getCode()} will map to a enum values found in {@link com.snapbundle.pojo.base.Result}, several examples of
 * which are illustrated below with see links.
 * <p/>
 * Service exceptions <i>may</i> include a well-defined {@link com.snapbundle.pojo.base.Result} error code if and only
 * if the web service itself was actually reached and invoked. In those cases where, for example, an Internet connection
 * wasn't available and the web service call never actually reached the cloud, the {@link #hasCode} will return false
 * and {@link #getCode()} will return null. In these situations, developers must debug the nested exception to determine
 * the exact reason for failure.
 *
 * @see com.snapbundle.pojo.base.Result#ERR_UNKNOWN_TYPE
 * @see com.snapbundle.pojo.base.Result#ERR_UNKNOWN_ENTITY
 * @see com.snapbundle.pojo.base.Result#ERR_FAILURE
 */
public class ServiceException extends Exception
{
    private boolean hasCode = false;

    private Integer code = null;

    public ServiceException(ResponseEntity response)
    {
        this(response.getMessage());
        this.code = response.getCode();
        this.hasCode = true;
    }

    public ServiceException(int code)
    {
        this.code = code;
        this.hasCode = true;
    }

    public ResponseEntity toResponseEntity()
    {
        if (hasCode)
        {
            return new ResponseEntity.Builder(getCode(), getMessage()).build();
        } else
        {
            return new ResponseEntity.Builder(Result.ERR_FAILURE.getCode(), getMessage()).build();
        }
    }

    private ServiceException(String message)
    {
        super(message);
    }

    public ServiceException(Exception e)
    {
        super(e);
    }

    /**
     * Verifies that the web service was actually invoked and resulted in a well-defined error code.
     *
     * @return true, if the web service was able to provide an explicit error code to indicate why the failure occurred
     */
    public boolean hasCode()
    {
        return hasCode;
    }

    /**
     * One of the {@link com.snapbundle.pojo.base.Result} ERR_xxxxx values to indicate why the web service call failed.
     *
     * @return web service assigned error code, if available,
     */
    public Integer getCode()
    {
        return code;
    }
}
