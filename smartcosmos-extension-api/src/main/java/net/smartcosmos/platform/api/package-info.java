/**
 * SMART COSMOS Server API interfaces and enums.
 * <h3>Runtime Context</h3>
 * The most critical server-side interface is {@link net.smartcosmos.platform.api.IContext}, an injected interface into
 * services, extensions, etc. that provides access to the complete runtime execution environment.
 * <h3>Server Side Extensions</h3>
 * Developers looking to build marketable server-side extensions should focus on the
 * {@link net.smartcosmos.platform.api.ext.IServerExtension} interace, the entry point for all server-side platform
 * extensions. Every server-side platform extension is afforded the opportunity to dynamically register new JPA-
 * annotated classes for persistence. Additionally, server-side platform extensions can dynamically register their own
 * custom DAO extensions in
 * {@link net.smartcosmos.platform.api.dao.IDAOFactory#register(java.lang.String, java.lang.Object)} and custom
 * services in
 * {@link net.smartcosmos.platform.api.service.IServiceFactory#register(java.lang.String, java.lang.Object)}.
 */
package net.smartcosmos.platform.api;
