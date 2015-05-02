package net.smartcosmos.market;

import net.smartcosmos.model.extension.IExtension;
import net.smartcosmos.platform.api.IService;
import net.smartcosmos.platform.api.ext.ISmartCosmosExtension;

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

    /**
     * Links to either a {@link IService#getServiceId()}, {@link IExtension#getUrn()}, or
     * {@link ISmartCosmosExtension#getExtensionId()} depending on the values of {@link #getExtensionType()} and
     * {@link #getServiceType()}.
     *
     * @return Unique identifier of the related extension
     */
    String getLinkedId();

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
