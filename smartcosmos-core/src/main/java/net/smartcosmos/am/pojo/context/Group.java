package net.smartcosmos.am.pojo.context;

import net.smartcosmos.am.model.context.IGroup;
import net.smartcosmos.pojo.base.NamedObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_EMPTY)
@JsonIgnoreProperties(value={"uniqueId","lastModifiedTimestamp","activeFlag","active"})
public class Group  extends NamedObject<IGroup> implements IGroup{
	private String name;
	private String description;

	public Group() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Group [name=" + name + ", description=" + description + "]";
	}	
	
}
