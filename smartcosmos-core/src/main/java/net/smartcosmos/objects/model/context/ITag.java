package net.smartcosmos.objects.model.context;

/*
 * *#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*
 * SMART COSMOS Platform Core SDK
 * ===============================================================================
 * Copyright (C) 2013 - 2014 SMARTRAC Technology Fletcher, Inc.
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

import net.smartcosmos.model.base.IAccountContext;
import net.smartcosmos.model.base.INamedObject;

/**
 * Defines a word or phrase useful for searching across related objects. Whereas
 * the {@link net.smartcosmos.model.base.ITypedObject} captures ontological categories
 * similar to a table of contents, tags are used to capture data more commonly thought
 * of as being found in the index of a book, not a table of contents.
 * <p/>
 * Tags are unique within a given account context.
 * <p/>
 * An Object might be assigned a type of "Bank Account" and certain Object Interactions
 * related to the register of that bank account might be tagged as "High Value Transactions" if
 * the dollar amount is over a certain threshold. This would permit a data analyst to
 * query the system for all "High Value" transactions against "Bank Account" records, eliminating
 * "High Value" transactions against "Retail Account" objects.
 */
public interface ITag extends INamedObject<ITag>, IAccountContext
{
}
