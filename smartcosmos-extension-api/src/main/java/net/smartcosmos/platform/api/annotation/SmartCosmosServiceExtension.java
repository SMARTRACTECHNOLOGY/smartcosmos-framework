/*
 *  SMART COSMOS Marketplace
 *
 *  Copyright (c) 2015, Smartrac Technology Fletcher, Inc.
 *  267 Cane Creek Rd, Fletcher, NC, 28732, USA
 *  All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of
 *  Smartrac Technology Fletcher, Inc. ("Confidential Information").
 *  You shall not disclose such Confidential Information and shall
 *  use it only in accordance with the terms of the license agreement
 *  you entered into with Smartrac Technology Fletcher, Inc.
 */

package net.smartcosmos.platform.api.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The interface that defines a SmartCosmosServiceExtension.
 * <p>
 *
 * All service extensions for SmartCosmos must create a concrete class that implements this interface.
 */
@Documented
@Target(TYPE)
@Retention(RUNTIME)
public @interface SmartCosmosServiceExtension
{
}
