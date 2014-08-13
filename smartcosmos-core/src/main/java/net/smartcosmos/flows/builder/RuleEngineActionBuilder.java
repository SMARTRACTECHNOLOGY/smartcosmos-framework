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

package net.smartcosmos.flows.builder;

import com.google.common.base.Preconditions;
import net.smartcosmos.builder.AbstractBuilder;
import net.smartcosmos.flows.model.EngineActionType;
import net.smartcosmos.flows.model.IRule;
import net.smartcosmos.flows.model.IRuleEngineAction;
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
