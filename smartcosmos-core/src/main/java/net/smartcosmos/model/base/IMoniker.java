package net.smartcosmos.model.base;

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

/**
 * Arbitrary textual field that is guaranteed to <b>never</b> be directly interpreted by the platform. Monikers are
 * provided to developers to store additional contextual data outside of the scope defined by the platform. A typical
 * usage is to store an external system alias or key, linking the SMART COSMOS entity to an entity managed and stored
 * outside of the platform.
 * <p/>
 * If a moniker value is assigned, it can be reset to its original (default) null value by passing in the special
 * {@link net.smartcosmos.Field#NULL_MONIKER} value. This special value is needed because the various levels of
 * {@link net.smartcosmos.util.json.ViewType} may preclude an instance from having the actual moniker value, and if that
 * instance were submitted during an update operation it wouldn't be clear if the value was omitted in order to reduce
 * network bandwidth consumption or if it was omitted because the developer actually wanted null assigned. The use of
 * the special NULL_MONIKER solves this problem.
 */
public interface IMoniker
{
    String getMoniker();

    void setMoniker(String moniker);
}
