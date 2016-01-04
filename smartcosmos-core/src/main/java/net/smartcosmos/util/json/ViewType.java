package net.smartcosmos.util.json;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Core SDK
 * ===============================================================================
 * Copyright (C) 2013 - 2015 SMARTRAC Technology Fletcher, Inc.
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
 * Defines a view hierarchy for SMART COSMOS JSON serialization as enums for easy switching
 * and rapid interpretation from Stringified view names.
 *
 * @see JsonUtil
 * @see JsonGenerationView
 */
public enum ViewType
{
    Full(JsonGenerationView.Full.class),
    Standard(JsonGenerationView.Standard.class),
    Minimum(JsonGenerationView.Minimum.class),
    Published(JsonGenerationView.Published.class);

    private final Class<? extends JsonGenerationView.Published> viewClass;

    ViewType(Class<? extends JsonGenerationView.Published> viewClass)
    {
        this.viewClass = viewClass;
    }

    public Class<? extends JsonGenerationView.Published> getViewClass()
    {
        return viewClass;
    }

    /**
     * Provides a safe parsing method to get the ViewType from the "view" query parameter.
     *
     * @param s : requested ViewType as String
     * @return parsed ViewType enum or ViewType.Standard in case of error
     */
    public static ViewType parseViewType(String s)
    {
        try
        {
            return valueOf(ViewType.class, s);
        } catch (IllegalArgumentException | NullPointerException e)
        {
            return ViewType.Standard;
        }
    }
}

