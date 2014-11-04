package net.smartcosmos.am.pojo.context;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import net.smartcosmos.am.model.context.ICustomData;
import net.smartcosmos.pojo.base.AccountTypedNamedObject;

@JsonInclude(value=Include.NON_EMPTY)
public class CustomData extends AccountTypedNamedObject<ICustomData>
implements ICustomData{
	
	private String name;
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type=type;
	}
	
	@Override
	public String toString() {
		return "CustomData [name=" + name + ", value=" + value + "]";
	}

	
}
