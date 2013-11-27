/*
 * SnapBundle™ SDK
 * (C) Copyright 2013 Tag Dynamics, LLC (http://tagdynamics.com/)
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

package com.snapbundle.model.base;

/**
 * Captures a precise geographic location, typically gathered from an embedded or attached GPS device. In order
 * to account for an unknown location, developers must check the {@link #hasGeoLocation()} flag.
 */
public interface IGeoLocation
{
    boolean hasGeoLocation();

    void setLat(double lat);

    void setLon(double lon);

    void setAlt(double alt);

    double getLat();

    double getLon();

    double getAlt();
}
