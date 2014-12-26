/*
 * SMART COSMOS SDK
 * (C) Copyright 2013-2014, Smartrac Technology Fletcher, Inc.
 * 267 Cane Creek Rd, Fletcher, NC, 28732, USA
 * All Rights Reserved.
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

package net.smartcosmos.flows.model.context;

import net.smartcosmos.model.base.IAccountDomainResource;
import net.smartcosmos.model.base.INamedObject;

/**
 * @author robertopalacios
 *
 */
public interface IFlowsDevice extends IAccountDomainResource<IFlowsDevice>, INamedObject<IFlowsDevice>
{
	/**
	 * @return The device IP address
	 */
	public String getIp();

	/**
	 * @param The device IP address
	 */
	public void setIp(String ip);

	/**
	 * @return The device communication port
	 */
	public String getPort();

	/**
	 * @param The device communication port
	 */
	public void setPort(String port);

	/**
	 * @return The device type id
	 */
	public String getTypeID();

	/**
	 * @param The device type id
	 */
	public void setTypeID(String typeID);

	/**
	 * @return The device content URL
	 */
	public String getUrl();

	/**
	 * @param The device content URL
	 */
	public void setUrl(String url);

	/**
	 * @return Is a mobile device
	 */
	public boolean isMobile();

	/**
	 * @param Is a mobile device
	 */
	public void setMobile(boolean mobile);

	/**
	 * @return The device is always on
	 */
	public boolean isAlwaysOn();

	/**
	 * @param The device is always on
	 */
	public void setAlwaysOn(boolean alwaysOn);
}
