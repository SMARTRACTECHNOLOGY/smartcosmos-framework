package net.smartcosmos.client.objects.relationship;

import net.smartcosmos.client.connectivity.ServerContext;

/**
 * Relationship Client Factory
 */
public final class RelationshipFactory
{
    private RelationshipFactory()
    {
    }

    /**
     * Creates a new instance of a relationship client that will work with objects at the specified server context.
     *
     * @param context Server connection information
     * @return New relationship client instance
     */
    public static IRelationshipClient createClient(ServerContext context)
    {
        return new RelationshipClient(context);
    }

}
