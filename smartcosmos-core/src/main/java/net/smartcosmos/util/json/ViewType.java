package net.smartcosmos.util.json;

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
}

