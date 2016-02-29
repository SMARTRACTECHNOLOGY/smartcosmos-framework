/**
 * REST resources and request handlers.
 *
 * IMPORTANT NOTE: In SMARTCOSMOS server extensions, as in the SMARTCOSMOS core,
 * public (i.e., no authentication required) REST resources are in the
 * YOUR.PACKAGE.HERE...resource.pub package, and private (authentication required)
 * REST resources are in the YOUR.PACKAGE.HERE...resource.secure package.
 *
 * The packaging, though, is not what controls the authentication behavior. That is
 * controlled for a particular endpoint by annotating the AuthenticatedUser input
 * parameter thusly:
 *
 * @Auth (required = false) AuthenticatedUser authenticatedUser
 *
 * The default (authentication required) case looks like:
 *
 * @Auth AuthenticatedUser authenticatedUser
 *
 * Look at the "public Response ..." method calls in ExampleExtensionResource and
 * MoreInterestingExampleResource for examples. In these files, all the endpoints handled
 * in ExampleExtensionResource are public, and in MoreInterestingExampleExtensionResource
 * they're all private.
 *
 */
package ${package}.resource;