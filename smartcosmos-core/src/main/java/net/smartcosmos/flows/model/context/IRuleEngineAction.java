package net.smartcosmos.flows.model.context;

public interface IRuleEngineAction
{
    IRule getRule();

    void setRule(IRule rule);

    EngineActionType getEngineAction();

    void setEngineAction(EngineActionType engineActionType);
}
