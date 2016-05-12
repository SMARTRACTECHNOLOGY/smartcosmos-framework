package net.smartcosmos.platform.constraint.validator;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Extension API
 * ===============================================================================
 * Copyright (C) 2013 - 2016 Smartrac Technology Fletcher, Inc.
 * ===============================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#
 */

import net.smartcosmos.platform.configuration.SmartCosmosMetricsFactory;
import net.smartcosmos.platform.constraint.annotation.CredentialsNotNullIfAuthEnabled;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Checks that username and password are set if metrics endpoint is enabled and basicAuth for metrics is set to {@code true}.
 */
public class MetricsBasicAuthValidator implements ConstraintValidator<CredentialsNotNullIfAuthEnabled, SmartCosmosMetricsFactory>
{
    @Override
    public void initialize(CredentialsNotNullIfAuthEnabled notNullIfAuthEnabled)
    {

    }

    @Override
    public boolean isValid(SmartCosmosMetricsFactory value, ConstraintValidatorContext context)
    {
        return (!value.isAuthenticationEnabled() ||
                (value.isAuthenticationEnabled() && value.getUsername() != null && !value.getUsername().isEmpty() &&
                        value.getPassword() != null && !value.getPassword().isEmpty()));
    }
}
