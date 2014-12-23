

package net.smartcosmos.flows.pojo.context;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.base.Preconditions;
import net.smartcosmos.flows.model.context.EngineActionType;
import net.smartcosmos.flows.model.context.IRule;
import net.smartcosmos.flows.model.context.IRuleEngineAction;
import net.smartcosmos.util.json.JsonGenerationView;

public class RuleEngineAction implements IRuleEngineAction
{
    @JsonView(JsonGenerationView.Minimum.class)
    protected IRule rule;

    @JsonView(JsonGenerationView.Minimum.class)
    protected EngineActionType engineActionType;

    @Override
    public IRule getRule()
    {
        return rule;
    }

    @Override
    public void setRule(IRule rule)
    {
        Preconditions.checkNotNull(rule, "rule must not be null");
        this.rule = rule;
    }

    @Override
    public EngineActionType getEngineAction()
    {
        return engineActionType;
    }

    @Override
    public void setEngineAction(EngineActionType engineActionType)
    {
        Preconditions.checkNotNull(engineActionType, "engine action must not be null");
        this.engineActionType = engineActionType;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RuleEngineAction that = (RuleEngineAction) o;

        if (engineActionType != that.engineActionType) return false;
        if (!rule.equals(that.rule)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = rule.hashCode();
        result = 31 * result + engineActionType.hashCode();
        return result;
    }
}
