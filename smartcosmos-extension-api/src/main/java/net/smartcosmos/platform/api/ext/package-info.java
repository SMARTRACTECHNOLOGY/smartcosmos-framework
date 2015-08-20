/**
 * SMART COSMOS server-side extensions are specialized Dropwizard bundles that provide additional wiring so that they
 * seamlessly integrate into the SMART COSMOS platform. There are two critical abstract classes that developers must
 * use when defining a platform extension: {@link net.smartcosmos.platform.base.AbstractServerExtension} must be
 * the base class for all SMART COSMOS bundled extensions, and the configuration for the extension must extend
 * {@link net.smartcosmos.platform.base.AbstractSmartCosmosExtensionConfiguration}.
 * <p/>
 * Every extension must define three things: an Extension ID (generally recommended to be a UUID), a short name, and
 * the extension specific configuration class that extends
 * {@link net.smartcosmos.platform.base.AbstractSmartCosmosExtensionConfiguration}.
 * <p/>
 * The definition of the extension on the server platform requires two entries in the YML file:
 * <ul>
 * <li>serverExtensions:</li>
 * <li>serverExtensionConfigurationPaths</li>
 * </ul>
 * <p/>
 * <b>NOTE:</b>The actual key used under these is arbitrary and irrelevant, but the values <b>must</b> be identical
 * such that if you use "foo" as the key name under the serverExtensions section, then you must use "foo" as the key
 * name under the serverExtensionConfigurationPaths section!
 * <p/>
 * The base class will <i>automatically</i> register your extension in the
 * {@link net.smartcosmos.platform.api.service.IServiceFactory} for easy lookup by other extensions or other
 * {@link io.dropwizard.lifecycle.Managed} classes registered by your extension. The access to the extension means
 * that it is possible to access both the server's primary YML configuration as well as the extension-specific
 * configuration values.
 * <p/>
 * The overall state transition within the extension looks like this:
 * <p/>
 * <ol>
 * <li>Dropwizard's initialize(Bootstrap</li>
 * <li>SMART COSMOS initialize(T extensionConfiguration)</li>
 * <li>If exceptional condition detected, handleInitializationException(Exception e)</li>
 * <li>(optional) getEntities() for Hibernate registration</li>
 * <li>(optional) registerResources(IContext context) for registering new web service endpoints</li>
 * <li>start()</li>
 * <li>stop()</li>
 * </ol>
 * <p/>
 * <b>NOTE:</b> All extensions <b>must</b> honor the mandatory enablement check defined by the enabled property
 * in the YML! During the
 * {@link
 * net.smartcosmos.platform.base.AbstractServerExtension#registerResources(net.smartcosmos.platform.api.IContext)}
 * call, developer must check extensionConfiguration.isEnabled() and smartly decide if the extension specific endpoints
 * should be registered or not. Similarly, if background services are required by the extension, the isEnabled()
 * check should also occur in the start() method to avoid starting any background services unnecessarily.
 *
 * @see net.smartcosmos.platform.base.AbstractServerExtension
 * @see net.smartcosmos.platform.base.AbstractSmartCosmosExtensionConfiguration
 */
package net.smartcosmos.platform.api.ext;
