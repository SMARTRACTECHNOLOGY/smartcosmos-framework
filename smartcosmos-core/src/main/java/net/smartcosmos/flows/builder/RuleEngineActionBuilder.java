package net.smartcosmos.flows.builder;

import com.google.common.base.Preconditions;
import net.smartcosmos.builder.AbstractBuilder;
import net.smartcosmos.flows.model.context.EngineActionType;
import net.smartcosmos.flows.model.context.IRule;
import net.smartcosmos.flows.model.context.IRuleEngineAction;
import net.smartcosmos.flows.pojo.context.RuleEngineAction;

public final class RuleEngineActionBuilder extends AbstractBuilder<IRuleEngineAction>
{
    public RuleEngineActionBuilder(IRule rule)
    {
        super(new RuleEngineAction());

        Preconditions.checkNotNull(rule);
        instance.setRule(rule);
    }

    public RuleEngineActionBuilder setEngineAction(EngineActionType engineActionType)
    {
        instance.setEngineAction(engineActionType);
        return this;
    }

    @Override
    protected void onValidate()
    {
        Preconditions.checkNotNull(instance.getEngineAction(), "engine action must not be null");
    }
}
