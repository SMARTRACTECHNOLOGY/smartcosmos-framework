package net.smartcosmos.market;

public enum ExtensionType
{
    /**
     * Server-side SMART COSMOS extension bundle.
     *
     * @see net.smartcosmos.platform.api.ext.ISmartCosmosExtension
     */
    SmartCosmosExtension,

    /**
     * External OAuth 2 authorized extension.
     *
     * @see net.smartcosmos.model.extension.IExtension
     */
    ExternalExtension,

    /**
     * Core SMART COSMOS service.
     *
     * @see net.smartcosmos.platform.api.IService
     * @see net.smartcosmos.platform.api.service.IDirectoryService
     * @see net.smartcosmos.platform.api.service.IEventService
     * @see net.smartcosmos.platform.api.service.IStorageService
     * @see net.smartcosmos.platform.api.service.IEventBroadcastNotificationService
     * @see net.smartcosmos.platform.api.service.INotificationService
     * @see net.smartcosmos.platform.api.service.IDownloadService
     * @see net.smartcosmos.platform.api.service.IEmailService
     * @see net.smartcosmos.platform.api.service.IQueueService
     * @see net.smartcosmos.platform.api.service.ITemplateService
     * @see net.smartcosmos.platform.api.service.IExceptionService
     * @see net.smartcosmos.platform.api.batch.IBatchUploadService
     */
    ServiceExtension,

    /**
     * Quartz Job.
     *
     * @see net.smartcosmos.platform.bundle.quartz.IQuartzJobDefinition
     */
    QuartzJob,

    /**
     * Transaction Handler.
     *
     * @see net.smartcosmos.platform.api.transaction.ITransactionHandler
     */
    TransactionHandler
}
