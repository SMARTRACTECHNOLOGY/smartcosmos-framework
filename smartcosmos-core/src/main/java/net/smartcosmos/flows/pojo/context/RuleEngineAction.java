/*
 * SMART COSMOS SDK
 * (C) Copyright 2013-2014, Smartrac Technology Fletcher, Inc.
 * 267 Cane Creek Rd, Fletcher, NC, 28732, USA
 * All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.smartcosmos.flows.pojo.context;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.base.Preconditions;
import net.smartcosmos.flows.model.EngineActionType;
import net.smartcosmos.flows.model.IRule;
import net.smartcosmos.flows.model.IRuleEngineAction;
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
