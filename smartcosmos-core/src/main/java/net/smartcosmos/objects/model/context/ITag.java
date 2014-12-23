package net.smartcosmos.objects.model.context;

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
