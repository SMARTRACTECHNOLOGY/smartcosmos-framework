package net.smartcosmos.market;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Server API
 * ===============================================================================
 * Copyright (C) 2013 - 2015 Smartrac Technology Fletcher, Inc.
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

import net.smartcosmos.platform.api.ext.IServerExtension;

import java.util.List;

public interface IExtensionProfile extends IProfile
{
    /**
     * Defines the type of extension.
     *
     * @return Extension type
     */
    ExtensionType getExtensionType();

    /**
     * Only applicable when {@link #getExtensionType()} is {@link ExtensionType#ServiceExtension}.
     *
     * @return Core service type
     */
    ServiceType getServiceType();

    String getAboutExtension();

    boolean isOpenSource();

    String getGitHubRepositoryUrl();

    boolean hasVideoDefinition();

    String getVideoDefinitionUrl();

    boolean hasConfigurationNotes();

    String getConfigurationNotesUrl();

    boolean hasSupport();

    String getSupportDescription();

    String getSupportUrl();

    String getEulaUrl();

    boolean hasUsageInstructions();

    String getUsageInstructionsDescription();

    String getUsageInstructionsUrl();

    boolean hasDatasheet();

    String getDataSheetUrl();

    boolean hasMarketingWebSite();

    String getMarketingWebSiteUrl();

    boolean hasApiDocumentation();

    String getApiDocumentationUrl();

    List<String> getSearchTerms();

    /**
     * Intended to be a maximum of three short (100 characters or less) core value propositions.
     *
     * @return Maximum of three short value propositions
     */
    List<String> getHighlights();
}
