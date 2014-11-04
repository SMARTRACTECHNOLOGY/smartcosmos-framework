package net.smartcosmos.am.builder;

import com.stormpath.sdk.group.GroupStatus;

import net.smartcosmos.am.model.context.IGroup;
import net.smartcosmos.am.pojo.context.Group;
import net.smartcosmos.builder.AbstractNamedObjectBuilder;

public class GroupBuilder  extends
AbstractNamedObjectBuilder<IGroup, GroupBuilder> {

	public GroupBuilder() {
		super(new Group());
	}
	
	public GroupBuilder setName(String name) {
		instance.setName(name);
		return this;
	}
	
	public GroupBuilder setDescription(String description) {
		instance.setDescription(description);
		return this;
	}
	
	public GroupBuilder setHref(String href) {
		instance.setHref(href);
		return this;
	}
	
	public GroupBuilder setStatus(GroupStatus status) {
		instance.setStatus(status);
		return this;
	}
	

	public GroupBuilder setType(String type) {
		instance.setType(type);
		return this;
	}



}
