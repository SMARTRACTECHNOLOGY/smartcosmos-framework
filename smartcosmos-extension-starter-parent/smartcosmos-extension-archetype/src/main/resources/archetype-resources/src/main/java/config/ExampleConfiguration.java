package ${package}.config;

import com.google.common.collect.Maps;
import net.smartcosmos.platform.base.AbstractSmartCosmosExtensionConfiguration;

import javax.validation.constraints.NotNull;
import java.util.Map;

public class ExampleConfiguration
        extends AbstractSmartCosmosExtensionConfiguration
{

    // NOTE TO EXTENSION DEVELOPER:
    // This is where we pick up transaction handler classes specified in the configuration.
    // If you need to pick up other features specific to your extension from the configuration
    // (objects.yml, by default), follow this template.
    //
    @NotNull
    private Map<String, String> transactionHandlerClasses = Maps.newLinkedHashMap();

    public Map<String, String> getTransactionHandlerClasses()
    {
        return transactionHandlerClasses;
    }
}
