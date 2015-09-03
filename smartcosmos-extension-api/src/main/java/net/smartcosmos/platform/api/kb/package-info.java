/**
 * Knowledge Base support is a novel mechanism where a common exception in a web service can be identified and a set of
 * troubleshooting ideas can be presented to the developer in the response message in lieu of an opaque error code.
 * <h3>Knowledge Base Articles</h3>
 * The {@link net.smartcosmos.platform.api.kb.KnowledgeBaseFactory} serves as a repository that maps 0, 1, o many
 * {@link net.smartcosmos.platform.api.kb.IKnowledgeBaseArticle} instances to a specific resource, typically a concrete
 * implementation of {@link net.smartcosmos.platform.resource.AbstractPlatformResource}. When a resource encounters
 * an exception, the developer may opt to include logic that checks the <code>KnowledgeBaseFactory</code> for any
 * <code>IKnowledgeBaseArticles</code> that might help end-developer resolve the exceptional condition.
 * <p>
 * It is up to the individual {@link net.smartcosmos.platform.api.kb.IKnowledgeBaseArticle} to determine if it can
 * contribute content to a specific exception by means of evaluating the exception and its stack trace programmatically
 * in {@link net.smartcosmos.platform.api.kb.IKnowledgeBaseArticle
 * #isApplicable(java.lang.Exception, net.smartcosmos.platform.api.HttpMethodType, java.lang.String)}.
 */
package net.smartcosmos.platform.api.kb;
