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

import java.util.List;

/**
 * Documents marketing, license, and support related metadata associated with a singular product offering listed in the
 * SMART COSMOS Market. The concept of extension profiles are versioned using a naming convention, where V1 represents
 * the minimal set of extension profile metadata required to list a product in the Market. Future releases that require
 * additional (mandatory) fields will be assigned a name of V2 and extend V1, and so on. In this way legacy product
 * profiles may continue to be supported by the Market.
 */
public interface IExtensionProfileV1 extends IProfile
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

    /**
     * Maximum of 1024 plain text (no markup) characters describing the extension.
     *
     * @return Plain text extensions description.
     */
    String getAboutExtension();

    /**
     * Flag indicating if the extension is open source software.
     *
     * @return true, if the product's source code is available under a popular open source software license
     */
    boolean isOpenSource();

    /**
     * Optional URL that links to a <b>GitHub</b> repository that contains the open source software when
     * {@link #isOpenSource()} is set to <b>true</b>.
     * <p/>
     * <B>NOTE:</B> URL size is limited to 255 characters and must be a fully qualified URL.
     *
     * @return GitHub repository URL, or null if no URL is provided or {@link #isOpenSource()} is set to <b>false</b>
     */
    String getGitHubRepositoryUrl();

    /**
     * Flag that indicates if the extension has a YouTube video introduction that more fully describes the extensions
     * capabilities and limitations.
     *
     * @return true, if the extension offers a YouTube video introduction
     */
    boolean hasYouTubeVideoIntroduction();

    /**
     * Optional URL that links to a <b>YouTube</b> video introduction of the extension.
     * <p/>
     * <B>NOTE:</B> URL size is limited to 255 characters and must be a fully qualified URL.
     *
     * @return YouTube video URL, or null if no URL is provided or {@link #hasYouTubeVideoIntroduction()} is set to
     * <b>false</b>
     */
    String getYouTubeVideoIntroductionUrl();

    /**
     * Flag that indicates if the seller maintains an external site containing elaborated or advance configuration
     * notes related to the extension hosted at an external (to the Market) web site.
     *
     * @return true, if the extension offers elaborated or advanced configuration notes hosted elsewhere
     */
    boolean hasConfigurationNotes();

    /**
     * Optional URL that links to an external web site that contains elaborated or advanced configuration notes for
     * this extension.
     * <p/>
     * <B>NOTE:</B> URL size is limited to 255 characters and must be a fully qualified URL.
     *
     * @return External site URL, or null if no URL is provided or {@link #hasConfigurationNotes()} is set to
     * <b>false</b>
     */
    String getConfigurationNotesUrl();

    /**
     * Flag that indicates if the extension seller offers any type of support model.
     *
     * @return true, if the extension includes some type of support model, false if no support is offered
     */
    boolean hasSupport();

    /**
     * Optional field with a maximum of 1024 characters of plain text (no markup) that describes the type of support
     * model offered by the seller for this specific extension. Support models come in many different forms, including
     * Stack Overflow, email support, telephone support, paid support, etc. and this field provides the developer with
     * the means of describing the boundaries of their specific support offering.
     *
     * @return Support model definition, or null if no support is offered or {@link #hasSupport()} is set to
     * <b>false</b>
     */
    String getSupportDescription();

    /**
     * Optional URL that links to the external web site where support or a support ticket system is located for
     * those using this extension and need additional help.
     * <p/>
     * <B>NOTE:</B> URL size is limited to 255 characters and must be a fully qualified URL.
     *
     * @return External support site URL, or null if no URL is provided or {@link #hasSupport()} is set to <b>false</b>
     */
    String getSupportUrl();

    /**
     * Mandatory field that points to a web page containing the license text associated with this extension.
     * <p/>
     * <B>NOTE:</B> URL size is limited to 255 characters and must be a fully qualified URL.
     *
     * @return non-null URL of the license text associated with this extension
     */
    String getLicenseUrl();

    /**
     * Flag that indicates if the extension offers usage instructions separate from the configuration notes.
     *
     * @return true, if the extension includes some type of usage instructions separate from the configuration notes
     */
    boolean hasUsageInstructions();

    /**
     * Optional field with a maximum of 1024 characters of plain text (no markup) that describes the type of usage
     * instructions offered by the seller for this specific extension. Usage instructions may range from a single page,
     * to a PDF manual, or perhaps even a complete web site. Use this field to accurately describe what the
     * user can expect to see if they visit the {@link #getUsageInstructionsUrl()}.
     *
     * @return Usage instructions overview, or null if no usage instructions are offered or
     * {@link #hasUsageInstructions()} is set to <b>false</b>
     */
    String getUsageInstructionsDescription();

    /**
     * Optional URL that links to the external web site where the usage instructions are located.
     * <p/>
     * <B>NOTE:</B> URL size is limited to 255 characters and must be a fully qualified URL.
     *
     * @return Usage instructions URL, or null if no URL is provided or {@link #hasUsageInstructions()} is set to
     * <b>false</b>
     */
    String getUsageInstructionsUrl();

    /**
     * Flag that indicates if the extension offers a <b>PDF</b> marketing data sheet.
     *
     * @return true, if the extension has a publicly available PDF marketing data sheet
     */
    boolean hasDatasheet();

    /**
     * Optional URL that links to a PDF download containing a marketing data sheet.
     * <p/>
     * <B>NOTE:</B> URL size is limited to 255 characters and must be a fully qualified URL where the MIME type
     * returned is that of a PDF
     *
     * @return Data sheet URL, or null if no URL is provided or {@link #hasDatasheet()} is set to <b>false</b>
     */
    String getDataSheetUrl();

    /**
     * Flag that indicates if the extension offers a marketing web site.
     *
     * @return true, if the extension has a publicly available marketing web site
     */
    boolean hasMarketingWebSite();

    /**
     * Optional URL that links to a marketing web site dedicated to this extension.
     * <p/>
     * <B>NOTE:</B> URL size is limited to 255 characters and must be a fully qualified URL.
     *
     * @return External marketing web site URL, or null if no URL is provided or {@link #hasMarketingWebSite()}
     * is set to <b>false</b>
     */
    String getMarketingWebSiteUrl();

    /**
     * Flag that indicates if a <b>Server Extension</b> offers an API that other extensions may use.
     * <p/>
     * <b>NOTE:</b> When the {@link #getExtensionType()} ()} is not {@link ExtensionType#ServerExtension}, this field
     * is undefined!
     *
     * @return true, if the <b>Server Extension</b> has a publicly available API.
     */
    boolean hasApiDocumentation();

    /**
     * Optional URL that links to a <b>Server Extension</b>'s public API at a location like
     * <a href="Apiary.io">Apiary.io</a>.
     * <p/>
     * <p/>
     * <B>NOTE:</B> URL size is limited to 255 characters and must be a fully qualified URL.
     *
     * @return External API definition URL, or null if no URL is provided or {@link #hasApiDocumentation()} is set to
     * <b>false</b>
     */
    String getApiDocumentationUrl();

    /**
     * Non-null list of simple search terms defined by the developer that assist in locating this plugin. A maximum of
     * <b>5</b> search terms are supported!
     * <p/>
     * <b>NOTE:</b> Search terms should be unique and separate from the seller's profile name, extension type, or other
     * fields included in this interface.
     *
     * @return Non-null, but possibly empty list of no more than 5 search terms to assit in locating this plugin.
     */
    List<String> getSearchTerms();

    /**
     * Non-null list of three short core value propositions, where each proposition is strictly limited to a maximum of
     * 255 characters.
     *
     * @return Non-null, but possibly empty, list of no more than 3 short value propositions.
     */
    List<String> getHighlights();
}
