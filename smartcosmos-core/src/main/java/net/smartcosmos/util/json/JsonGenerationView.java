

package net.smartcosmos.util.json;

/**
 * Defines a view hierarchy for SMART COSMOS JSON serialization that selectively includes (or excludes)
 * certain verbose fields when perform a JSON conversion operation.
 *
 * @see JsonUtil
 * @see ViewType
 */
public class JsonGenerationView
{
    public static class Published
    {

    }

    public static class Minimum extends Published
    {
    }

    public static class Standard extends Minimum
    {
    }

    public static class Full extends Standard
    {
    }

    public static class Restricted extends Full
    {
    }
}
