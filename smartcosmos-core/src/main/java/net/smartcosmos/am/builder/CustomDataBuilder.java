package net.smartcosmos.am.builder;

import net.smartcosmos.am.model.context.ICustomData;
import net.smartcosmos.am.pojo.context.CustomData;
import net.smartcosmos.builder.AbstractNamedObjectBuilder;

public class CustomDataBuilder extends
AbstractNamedObjectBuilder<ICustomData, CustomDataBuilder> {

	public CustomDataBuilder() {
		super(new CustomData());
	}
	public CustomDataBuilder setName(String name) {
		instance.setName(name);
		return this;
	}
	public CustomDataBuilder setValue(String value) {
		instance.setValue(value);
		return this;  
	}
	public CustomDataBuilder setType(String type) {
		instance.setType(type);
		return this;  
	}
}
