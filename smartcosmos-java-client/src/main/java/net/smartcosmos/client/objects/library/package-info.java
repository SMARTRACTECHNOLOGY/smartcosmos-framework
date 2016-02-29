/**
 * <b>GO AWAY</b> non-public implementation package that developers should avoid for all intents and purposes.
 * The package defines a collective set of CRUD interfaces driven off of either an explicit entity instance, e.g. an
 * instance of say {@link net.smartcosmos.objects.model.context.IObject}, or a more fluid and dynamic {@link org.json.JSONObject}
 * instance.
 *
 * Developers do not have any need to narrow and cast a valid client interface down to one of these CRUD interfaces.
 *
 * It also defines several sub-packages, one that contains a collection of abstract base classes used to build the
 * various clients, one that defines explicit endpoints with parameterized URLs, and another that contains a
 * generalized REST web service command structure.
 */
package net.smartcosmos.client.objects.library;
