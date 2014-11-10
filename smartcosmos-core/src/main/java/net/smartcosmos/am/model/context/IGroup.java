package net.smartcosmos.am.model.context;

import net.smartcosmos.am.pojo.context.Group;
import net.smartcosmos.model.base.INamedObject;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@JsonDeserialize(as=Group.class)
@JsonSerialize(as=Group.class)
public interface IGroup extends
INamedObject<IGroup>{
	public String getName();

	public void setName(String name);

	public String getDescription();

	public void setDescription(String description);
	
}
