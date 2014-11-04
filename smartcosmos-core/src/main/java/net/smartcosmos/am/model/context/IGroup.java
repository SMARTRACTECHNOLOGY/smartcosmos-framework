package net.smartcosmos.am.model.context;

import net.smartcosmos.am.pojo.context.Group;
import net.smartcosmos.model.base.IAccountDomainResource;
import net.smartcosmos.model.base.INamedObject;
import net.smartcosmos.model.base.ITypedObject;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.stormpath.sdk.group.GroupStatus;


@JsonDeserialize(as=Group.class)
@JsonSerialize(as=Group.class)
public interface IGroup extends
IAccountDomainResource<IGroup>,
INamedObject<IGroup>, ITypedObject {
	public String getName();

	public void setName(String name);

	public String getDescription();

	public void setDescription(String description);
	
	public String getHref() ;

	public void setHref(String href) ;

	public GroupStatus getStatus();

	public void setStatus(GroupStatus status);
}
