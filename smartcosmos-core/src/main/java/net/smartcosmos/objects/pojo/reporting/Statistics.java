package net.smartcosmos.objects.pojo.reporting;

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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import net.smartcosmos.Field;
import net.smartcosmos.util.json.JsonGenerationView;

public class Statistics
{
    private int relationshipsCount;

    private int addressesCount;

    private int filesCount;

    private int timelinesCount;

    private int geospatialCount;

    private int metadataCount;

    private int tagsCount;

    private int interactionsCount;

    private int librariesCount;

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonProperty(Field.STATS_RELATIONSHIPS)
    public int getRelationshipsCount()
    {
        return relationshipsCount;
    }

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonProperty(Field.STATS_RELATIONSHIPS)
    public Statistics setRelationshipsCount(int relationshipsCount)
    {
        this.relationshipsCount = relationshipsCount;
        return this;
    }

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonProperty(Field.STATS_ADDRESSES)
    public int getAddressesCount()
    {
        return addressesCount;
    }

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonProperty(Field.STATS_ADDRESSES)
    public Statistics setAddressesCount(int addressesCount)
    {
        this.addressesCount = addressesCount;
        return this;
    }

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonProperty(Field.STATS_FILES)
    public int getFilesCount()
    {
        return filesCount;
    }

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonProperty(Field.STATS_FILES)
    public Statistics setFilesCount(int filesCount)
    {
        this.filesCount = filesCount;
        return this;
    }

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonProperty(Field.STATS_TIMELINES)
    public int getTimelinesCount()
    {
        return timelinesCount;
    }

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonProperty(Field.STATS_TIMELINES)
    public Statistics setTimelinesCount(int timelinesCount)
    {
        this.timelinesCount = timelinesCount;
        return this;
    }

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonProperty(Field.STATS_GEOSPATIAL)
    public int getGeospatialCount()
    {
        return geospatialCount;
    }

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonProperty(Field.STATS_GEOSPATIAL)
    public Statistics setGeospatialCount(int geospatialCount)
    {
        this.geospatialCount = geospatialCount;
        return this;
    }

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonProperty(Field.STATS_METADATA)
    public int getMetadataCount()
    {
        return metadataCount;
    }

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonProperty(Field.STATS_METADATA)
    public Statistics setMetadataCount(int metadataCount)
    {
        this.metadataCount = metadataCount;
        return this;
    }

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonProperty(Field.STATS_TAGS)
    public int getTagsCount()
    {
        return tagsCount;
    }

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonProperty(Field.STATS_TAGS)
    public Statistics setTagsCount(int tagsCount)
    {
        this.tagsCount = tagsCount;
        return this;
    }

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonProperty(Field.STATS_INTERACTIONS)
    public int getInteractionsCount()
    {
        return interactionsCount;
    }

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonProperty(Field.STATS_INTERACTIONS)
    public Statistics setInteractionsCount(int interactionsCount)
    {
        this.interactionsCount = interactionsCount;
        return this;
    }

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonProperty(Field.STATS_LIBRARIES)
    public int getLibrariesCount()
    {
        return librariesCount;
    }

    @JsonView(JsonGenerationView.Minimum.class)
    @JsonProperty(Field.STATS_LIBRARIES)
    public Statistics setLibrariesCount(int librariesCount)
    {
        this.librariesCount = librariesCount;
        return this;
    }
}
