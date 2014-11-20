package net.smartcosmos.am.builder;

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

	public GroupBuilder setUrn(String urn) {
		instance.setUrn(urn);
		return this;
	}	
}
