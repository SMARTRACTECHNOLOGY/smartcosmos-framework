package net.smartcosmos.model.base;

/**
 * Account scoped domain resource. SMART COSMOS is a multi-tenant system and relies
 * on this interface to ensure that all web service calls are appropriately
 * sandboxed within the sole account of the authenticated user.
 *
 * @param <T>
 */
public interface IAccountDomainResource<T> extends IDomainResource<T>, IAccountContext
{
}
